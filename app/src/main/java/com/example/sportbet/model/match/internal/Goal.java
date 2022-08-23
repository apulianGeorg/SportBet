package com.example.sportbet.model.match.internal;

import lombok.Data;

@Data
public class Goal {

    private Integer minute;
    private String scorerName;
    private boolean isOwnGoal;
    private boolean isPenalty;
}
