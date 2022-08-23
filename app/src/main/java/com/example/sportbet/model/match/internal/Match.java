package com.example.sportbet.model.match.internal;

import com.example.sportbet.InternalConstants;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;

import lombok.Data;

@Data
public class Match {
    private int matchId;
    private Team team1;
    private Team team2;
    private List<Goal> goalsTeam1;
    private List<Goal> goalsTeam2;
    private String location;
    private MatchGroup group;
    private boolean matchIsFinished = false;
    //Format "2021-07-11T21:00:00"
    //Can not use LocalDateTime because of Api level < 26
    //SimpleDateTimeFormatter I get only Date
    private String matchTime;

    public String getResult() {
        if (matchIsFinished) {
            return goalsTeam1.size() + " : " + goalsTeam2.size();
        } else {
            return "  :  ";
        }
    }
}
