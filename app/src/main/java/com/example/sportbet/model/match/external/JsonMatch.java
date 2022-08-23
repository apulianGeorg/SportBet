package com.example.sportbet.model.match.external;

import java.util.List;

import lombok.Data;

@Data
public class JsonMatch {

    private Integer matchID;
    private String matchDateTime;
    private String timeZoneID;
    private Integer leagueId;
    private String leagueName;
    private Integer leagueSeason;
    private String leagueShortcut;
    private String matchDateTimeUTC;
    private JsonGroup group;
    private JsonTeam team1;
    private JsonTeam team2;
    private String lastUpdateDateTime;
    private Boolean matchIsFinished;
    private List<JsonMatchResult> matchResults = null;
    private List<JsonGoal> goals = null;
    private JsonMatchLocation location;
    private Integer numberOfViewers;
}
