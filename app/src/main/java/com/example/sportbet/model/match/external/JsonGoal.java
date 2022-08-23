package com.example.sportbet.model.match.external;

import lombok.Data;

@Data
public class JsonGoal {
    private Integer goalID;
    private Integer scoreTeam1;
    private Integer scoreTeam2;
    private Integer matchMinute;
    private Integer goalGetterID;
    private String goalGetterName;
    private Boolean isPenalty;
    private Boolean isOwnGoal;
    private Boolean isOvertime;
    private String comment;
}
