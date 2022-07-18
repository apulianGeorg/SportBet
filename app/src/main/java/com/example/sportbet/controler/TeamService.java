package com.example.sportbet.controler;

import android.graphics.drawable.Drawable;

import com.example.sportbet.model.match.Team;

import java.util.HashMap;

//TODO: Test
public class TeamService {
    private final HashMap<Integer, Team> teamMap = new HashMap<>();
    private final TeamIconService teamIconService = new TeamIconService();

    public Team getTeam(int teamId, String teamName, String teamIconUrl) {
        Team team = teamMap.get(teamId);
        if (team == null || !team.getTeamName().equals(teamName)) {
            Drawable drawable = teamIconService.getTeamIcon(teamIconUrl);
            team = new Team(teamId, teamName, drawable);
            teamMap.put(teamId, team);
        }
        return team;
    }
}
