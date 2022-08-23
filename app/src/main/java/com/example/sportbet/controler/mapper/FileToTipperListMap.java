package com.example.sportbet.controler.mapper;

import com.example.sportbet.InternalConstants;
import com.example.sportbet.model.bet.InvalidBetException;
import com.example.sportbet.model.bet.MatchBet;
import com.example.sportbet.model.player.Player;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

//TODO: Brauchen wir das?
public class FileToTipperListMap {
    public static List<Player> mapFileToTipperList(String fileInput) throws JSONException, InvalidBetException {
        List<Player> tipperList = new ArrayList<>();
        JSONObject jsonFileObj = new JSONObject(fileInput);
        JSONArray jsonTippArr = new JSONArray(jsonFileObj.getString(InternalConstants.Player));
        for (int arrIdx = 0; arrIdx < jsonTippArr.length(); arrIdx++) {
            tipperList.add(createPlayer(jsonTippArr.getJSONObject(arrIdx)));
        }
        return tipperList;
    }

    private static Player createPlayer(JSONObject tipperString) throws InvalidBetException, JSONException {
        Player player = new Player();
        player.setName(tipperString.getString(InternalConstants.PlayerName));
        player.setPoints(Integer.parseInt(tipperString.getString(InternalConstants.PlayerPoints)));
        player.setPlayerId(Integer.parseInt(tipperString.getString(InternalConstants.PlayerId)));
        JSONArray betList = new JSONArray(tipperString.getString(InternalConstants.PlayerGame));
        player.setGameBets(getBetList(betList));
        return player;
    }

    private static List<MatchBet> getBetList(JSONArray jsonArray) throws InvalidBetException, JSONException {
        ArrayList<MatchBet> matchBets = new ArrayList<>();
        for (int idx = 0; idx < jsonArray.length(); idx++) {
            MatchBet matchBet = new MatchBet();

            JSONObject jsonGameBet = jsonArray.getJSONObject(idx);
            matchBet.setGoals(jsonGameBet.getString(InternalConstants.PlayerGameBet));
            matchBet.setEvaluated(
                    Boolean.parseBoolean(jsonGameBet.getString(InternalConstants.PlayerGameIsEvaluated)));
            matchBet.setMatchId(
                    Integer.parseInt(jsonGameBet.getString(InternalConstants.PlayerGameId)));
            matchBet.setBetPointsForMatch(
                    Integer.parseInt(jsonGameBet.getString(InternalConstants.PlayerGamePoints)));
            matchBets.add(matchBet);

        }

        return matchBets;
    }

}
