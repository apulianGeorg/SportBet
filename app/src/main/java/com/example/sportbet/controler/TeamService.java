package com.example.sportbet.controler;

import android.graphics.drawable.Drawable;

import com.example.sportbet.model.match.internal.Team;

import java.util.HashMap;
import java.util.concurrent.ExecutionException;

public class TeamService {
    private final HashMap<Integer, Team> teamMap;
    private final TeamIconService teamIconService;

    public TeamService(){
        teamMap = new HashMap<>();
        teamIconService = new TeamIconService();
    }

    public Team getTeam(int teamId, String teamName, String teamIconUrl) throws ExecutionException, InterruptedException {
        Team team = teamMap.get(teamId);
        if (team == null || !team.getTeamName().equals(teamName)) {
            var future = teamIconService.getTeamIcon(teamIconUrl);
            team = new Team(teamId, teamName, future);
            teamMap.put(teamId, team);
        }
        return team;
    }
}
