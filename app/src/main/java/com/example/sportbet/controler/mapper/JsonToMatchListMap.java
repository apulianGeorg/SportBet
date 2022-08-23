package com.example.sportbet.controler.mapper;

import com.example.sportbet.controler.TeamService;
import com.example.sportbet.model.match.external.JsonGoal;
import com.example.sportbet.model.match.external.JsonGroup;
import com.example.sportbet.model.match.external.JsonMatch;
import com.example.sportbet.model.match.external.JsonMatchLocation;
import com.example.sportbet.model.match.external.JsonTeam;
import com.example.sportbet.model.match.internal.Match;
import com.example.sportbet.model.match.internal.MatchGroup;
import com.example.sportbet.model.match.internal.Team;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class JsonToMatchListMap {

    private final TeamService teamService;

    public JsonToMatchListMap(TeamService teamService) {
        this.teamService = teamService;
    }

    public ArrayList<Match> mapJsonToMatchList(Future<String> future) throws ExecutionException, InterruptedException {
        ObjectMapper mapper = new ObjectMapper()
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);

        JsonMatch[] jsonMatches = new JsonMatch[0];
        try {
            jsonMatches = mapper.readValue(future.get(), JsonMatch[].class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        var matches = new ArrayList<Match>();

        for (var jsonMatch : jsonMatches) {
            var match = mapToInternalMatch(jsonMatch);
            matches.add(match);
        }
        return matches;
    }

    private Match mapToInternalMatch(JsonMatch jsonMatch) throws ExecutionException, InterruptedException {
        var match = new Match();
        match.setGroup(getJsonGroup(jsonMatch.getGroup()));
        match.setMatchId(jsonMatch.getMatchID());
        match.setMatchIsFinished(jsonMatch.getMatchIsFinished());
        match.setLocation(getLocation(jsonMatch.getLocation()));
        setGoals(jsonMatch.getGoals(), match);
        match.setTeam1(getTeam(jsonMatch.getTeam1()));
        match.setTeam2(getTeam(jsonMatch.getTeam2()));
        match.setMatchTime(jsonMatch.getMatchDateTime());
        return match;
    }

    private MatchGroup getJsonGroup(JsonGroup group) {
        var matchGroup = new MatchGroup();
        matchGroup.setGroupName(group.getGroupName());
        matchGroup.setGroupOrderID(group.getGroupOrderID());
        return matchGroup;
    }

    private String getLocation(JsonMatchLocation locationCity) {
        return (locationCity == null) ? "" : locationCity.getLocationCity();
    }

    private Team getTeam(JsonTeam jsonTeam) throws ExecutionException, InterruptedException {
        return teamService.getTeam(jsonTeam.getTeamId(), jsonTeam.getTeamName(), jsonTeam.getTeamIconUrl());
    }

    private void setGoals(List<JsonGoal> jsonGoals, Match match) {
        var goalsTeam1 = new ArrayList<com.example.sportbet.model.match.internal.Goal>();
        var goalsTeam2 = new ArrayList<com.example.sportbet.model.match.internal.Goal>();
        var numberOfGoalsTeam1 = 0;
        var numberOfGoalsTeam2 = 0;

        for (var jsonGoal : jsonGoals) {
            if (isValidGoal(jsonGoal, numberOfGoalsTeam1, numberOfGoalsTeam2)) {
                if (hasScoredTeam1(numberOfGoalsTeam1, jsonGoal)) {
                    numberOfGoalsTeam1++;
                    goalsTeam1.add(getGoal(jsonGoal));
                } else {
                    numberOfGoalsTeam2++;
                    goalsTeam2.add(getGoal(jsonGoal));
                }
            }
        }
        match.setGoalsTeam1(goalsTeam1);
        match.setGoalsTeam2(goalsTeam2);
    }

    private boolean isValidGoal(JsonGoal jsonGoal, int numberOfGoalsTeam1, int numberOfGoalsTeam2) {
        //Some goals are in JSON but not really goals
        return jsonGoal.getGoalGetterName() != null && jsonGoal.getGoalID() != null &&
                jsonGoal.getGoalGetterID() != null && jsonGoal.getMatchMinute() != null &&
                (jsonGoal.getScoreTeam1() > numberOfGoalsTeam1 || jsonGoal.getScoreTeam2() > numberOfGoalsTeam2);
    }

    private com.example.sportbet.model.match.internal.Goal getGoal(JsonGoal jsonGoal) {
        var goal = new com.example.sportbet.model.match.internal.Goal();
        goal.setOwnGoal(jsonGoal.getIsOwnGoal());
        goal.setPenalty(jsonGoal.getIsPenalty());
        goal.setMinute(jsonGoal.getMatchMinute());
        goal.setScorerName(jsonGoal.getGoalGetterName());
        return goal;
    }

    private boolean hasScoredTeam1(int numberOfGoalsTeam1, JsonGoal jsonGoal) {
        return (numberOfGoalsTeam1 != jsonGoal.getScoreTeam1());
    }
}
