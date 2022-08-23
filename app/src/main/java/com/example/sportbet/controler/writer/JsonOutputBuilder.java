package com.example.sportbet.controler.writer;

import com.example.sportbet.InternalConstants;
import com.example.sportbet.model.bet.MatchBet;
import com.example.sportbet.model.player.Player;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

//TODO: Tests
public class JsonOutputBuilder {
    public static String getJsonString(List<Player> tipperListForJson) {
        try {
            JSONArray jsonPlayerArr = new JSONArray();
            for (Player player : tipperListForJson) {
                JSONArray jsonMatchBetArr = new JSONArray();

                for (MatchBet matchBet : player.getGameBets()) {
                    JSONObject jsonMatchBet = getJsonBet(matchBet);
                    jsonMatchBetArr.put(jsonMatchBet);
                }
                jsonPlayerArr.put(getJsonPlayer(player, jsonMatchBetArr));
            }
            JSONObject jsonPlayer = getJsonPlayer(jsonPlayerArr);

            return jsonPlayer.toString();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return InternalConstants.EmptyStr;
    }

    private static JSONObject getJsonPlayer(JSONArray jsonPlayerArr) throws JSONException {
        JSONObject jsonPlayer = new JSONObject();
        jsonPlayer.put(InternalConstants.Player, jsonPlayerArr);
        return jsonPlayer;
    }

    private static JSONObject getJsonBet(MatchBet matchBet) throws JSONException {
        JSONObject jsonBet = new JSONObject();
        jsonBet.put(InternalConstants.PlayerGameId, matchBet.getMatchId());
        jsonBet.put(InternalConstants.PlayerGameBet, matchBet.getResultAsString());
        jsonBet.put(InternalConstants.PlayerGamePoints, matchBet.getBetPointsForMatch());
        jsonBet.put(InternalConstants.PlayerGameIsEvaluated, matchBet.isEvaluated());
        return jsonBet;
    }

    private static JSONObject getJsonPlayer(Player tipper, JSONArray jsonBet) throws JSONException {
        JSONObject jsonTipperObject = new JSONObject();
        jsonTipperObject.put(InternalConstants.PlayerId, tipper.getPlayerId());
        jsonTipperObject.put(InternalConstants.PlayerName, tipper.getName());
        jsonTipperObject.put(InternalConstants.PlayerPoints, tipper.getPoints());
        jsonTipperObject.put(InternalConstants.PlayerGame, jsonBet);
        return jsonTipperObject;
    }

}
