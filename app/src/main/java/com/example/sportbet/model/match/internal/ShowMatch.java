package com.example.sportbet.model.match.internal;

import java.util.List;

import lombok.Data;

@Data
public class ShowMatch {
    private int matchId;
    private Team team1;
    private Team team2;
    private List<Goal> goalsTeam1;
    private List<Goal> goalsTeam2;
    private String location;
    private MatchGroup group;
    private boolean matchIsFinished = false;
    private String matchTime;
    private String result;
    private boolean isHeader;

    public void setMatch(Match match) {
        matchId = match.getMatchId();
        team1 = match.getTeam1();
        team2 = match.getTeam2();
        goalsTeam1 = match.getGoalsTeam1();
        goalsTeam2 = match.getGoalsTeam2();
        location = match.getLocation();
        group = match.getGroup();
        matchIsFinished = match.isMatchIsFinished();
        matchTime = match.getMatchTime();
        result = match.getResult();
        isHeader = false;
    }
}
