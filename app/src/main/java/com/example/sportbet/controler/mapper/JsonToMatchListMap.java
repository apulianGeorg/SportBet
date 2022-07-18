package com.example.sportbet.controler.mapper;

import com.example.sportbet.InternalConstants;
import com.example.sportbet.controler.TeamService;
import com.example.sportbet.controler.UrlConstants;
import com.example.sportbet.controler.exception.InvalidResourcesException;
import com.example.sportbet.model.match.Goal;
import com.example.sportbet.model.match.Match;
import com.example.sportbet.model.match.Team;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//TODO: Tests
public class JsonToMatchListMap {

    private static final TeamService teamService= new TeamService();

    public static ArrayList<Match> mapJsonToMatchList(String jsonStr) throws InvalidResourcesException, JSONException {
        if (jsonStr == null) {
            throw new InvalidResourcesException("No html Input found");
        }
        ArrayList<Match> matchList = new ArrayList<>();
        JSONArray jsonArr = new JSONArray(jsonStr);
        for (int arrIdx = 0; arrIdx < jsonArr.length(); arrIdx++) {
            Match spiel = getMatch(jsonArr.getJSONObject(arrIdx));
            if (spiel != null) {
                matchList.add(spiel);
            }
        }

        return matchList;
    }

    private static Match getMatch(JSONObject matchElement) throws JSONException {
        if (matchElement == null) {
            return null;
        }
        Match match = new Match();
        match.setMatchId(getMatchId(matchElement));
/*        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            match.setMatchTime(getMatchTime(matchElement));
        }*/
        match.setLocation(getLocation(matchElement));
        match.setMatchIsFinished(isMatchIsFinished(matchElement));
        match.setTeam1(getTeam(matchElement, UrlConstants.Team1));
        match.setTeam2(getTeam(matchElement, UrlConstants.Team2));

        Map<Integer, List<Goal>> teamGoalGetterList = getGoalsList(matchElement);
        match.setGoalsTeam1(teamGoalGetterList.get(1));
        match.setGoalsTeam2(teamGoalGetterList.get(2));

        return match;
    }

    private static boolean isMatchIsFinished(JSONObject matchElement) {
        try {
            return Boolean.parseBoolean(matchElement.getString(UrlConstants.MatchIsFinished));
        } catch (JSONException e) {
            e.printStackTrace();
            return false;
        }
    }

    private static String getMatchTime(JSONObject matchElement) {
        String spielZeit;
        try {
            spielZeit = matchElement.getString(UrlConstants.MatchDateTime);
        } catch (JSONException e) {
            e.printStackTrace();
            spielZeit = InternalConstants.EmptyStr;
        }
        return spielZeit;
    }

    private static int getMatchId(JSONObject matchElement) {
        int matchId;
        try {
            matchId = Integer.parseInt(matchElement.getString(UrlConstants.MatchId));
        } catch (JSONException e) {
            e.printStackTrace();
            matchId = 0;
        }
        return matchId;
    }

    private static String getLocation(JSONObject matchElement) {
        try {
            return matchElement.getJSONObject(UrlConstants.Location).getString(UrlConstants.LocationCity);
        } catch (JSONException e) {
            return InternalConstants.EmptyStr;
        }
    }

    private static Team getTeam(JSONObject matchElement, String teamStr) throws JSONException {
        JSONObject jsonTeam = matchElement.getJSONObject(teamStr);
        int teamId = Integer.parseInt(jsonTeam.getString(UrlConstants.TeamId));
        String teamName = jsonTeam.getString(UrlConstants.TeamName);
        String teamIconUrl = jsonTeam.getString(UrlConstants.TeamIconUrl);
        return teamService.getTeam(teamId, teamName, teamIconUrl);
    }

    private static Map<Integer, List<Goal>> getGoalsList(JSONObject matchElement) {
        List<Goal> goalList1 = new ArrayList<>();
        List<Goal> goalList2 = new ArrayList<>();
        Map<Integer, List<Goal>> goalGetterDictionary = new HashMap<>();

        int toreTeam1 = 0;
        try {
            JSONArray goalList = matchElement.getJSONArray(UrlConstants.Goals);
            for (int temp = 0; temp < goalList.length(); temp++) {
                JSONObject jsonGoalGetter = goalList.getJSONObject(temp);
                if (Integer.parseInt(jsonGoalGetter.getString(UrlConstants.ScoreTeam1)) > toreTeam1) {
                    toreTeam1++;
                    goalList1.add(getGoals(jsonGoalGetter));
                } else {
                    goalList2.add(getGoals(jsonGoalGetter));
                }
            }
        } catch (JSONException e) {
            return goalGetterDictionary;
        }

        goalGetterDictionary.put(1, goalList1);
        goalGetterDictionary.put(2, goalList2);
        return goalGetterDictionary;
    }

    private static Goal getGoals(JSONObject jsonGoalGetter) {
        Goal goal = new Goal();
        goal.setScorerName(getScorerName(jsonGoalGetter));
        goal.setMinute(getMatchMinute(jsonGoalGetter));
        goal.setOwnGoal(isOwnGoal(jsonGoalGetter));
        goal.setPenalty(isPenalty(jsonGoalGetter));
        return goal;
    }

    private static String getScorerName(JSONObject jsonGoalGetter) {
        String goalGetterName;
        try {
            goalGetterName = jsonGoalGetter.getString(UrlConstants.GoalGetterName);
        } catch (JSONException e) {
            e.printStackTrace();
            goalGetterName = InternalConstants.EmptyStr;
        }
        return goalGetterName;
    }

    private static Integer getMatchMinute(JSONObject jsonGoalGetter) {
        Integer matchMinute = null;
        try {
            matchMinute = Integer.valueOf(jsonGoalGetter.getString(UrlConstants.MatchMinute));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return matchMinute;
    }

    private static boolean isOwnGoal(JSONObject jsonGoalGetter) {
        boolean isOwnGoal;
        try {
            isOwnGoal = Boolean.parseBoolean(jsonGoalGetter.getString(UrlConstants.IsOwnGoal));
        } catch (JSONException e) {
            e.printStackTrace();
            isOwnGoal = false;
        }
        return isOwnGoal;
    }

    private static boolean isPenalty(JSONObject jsonGoalGetter) {
        boolean isPenalty;
        try {
            isPenalty = Boolean.parseBoolean(jsonGoalGetter.getString(UrlConstants.IsPenalty));
        } catch (JSONException e) {
            e.printStackTrace();
            isPenalty = false;
        }
        return isPenalty;
    }
}
