package com.example.sportbet.model.match;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.example.sportbet.InternalConstants;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;

import lombok.Data;

@Data
public class Match {
    public static final String T_00_00_00 = "0000-00-00T00:00:00";
    private int matchId;
    private Team team1;
    private Team team2;
    private List<Goal> goalsTeam1;
    private List<Goal> goalsTeam2;
    private String location;
    private boolean matchIsFinished = false;
    private LocalDateTime matchTime;

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void setMatchTime(String matchTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
        if (Objects.equals(matchTime, InternalConstants.EmptyStr)){
            matchTime = T_00_00_00;
        }
        this.matchTime = LocalDateTime.parse(matchTime, formatter);
    }

    public String getResult() {
        if (matchIsFinished) {
            return goalsTeam1.size() + " : " + goalsTeam2.size();
        } else {
            return "  :  ";
        }
    }
}
