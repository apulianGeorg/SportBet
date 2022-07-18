package com.example.sportbet.model.match;

import lombok.Data;

@Data
public class MatchBet {
    private boolean isEvaluated;
    private int matchId;
    private Integer goalsTeam1;
    private Integer goalsTeam2;
    private int betPointsForMatch;

    public String getResultAsString() {
        return goalsTeam1 + " : " + goalsTeam2;
    }

    public void setGoals(String result) throws InvalidBetException {
        if (result == null) {
            throw new NullPointerException("Result of the Bet must be set");
        }

        String resultWithGeneralSeparator = result.trim()
                .replace("-", ":")
                .replace(" ", ":");
        String[] arr = resultWithGeneralSeparator.split(":");
        if (arr.length != 2) {
            throw new InvalidBetException("The Bet must be in the format 'number [ ,:,-] number");
        }
        goalsTeam1 = Integer.valueOf(arr[0]);
        goalsTeam2 = Integer.valueOf(arr[1]);
    }
}
