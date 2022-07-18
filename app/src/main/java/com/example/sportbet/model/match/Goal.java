package com.example.sportbet.model.match;

import lombok.Data;

@Data
public class Goal {

    //TODO: TEST
    private Integer minute;
    private String scorerName;
    private boolean isOwnGoal;
    private boolean isPenalty;
}
