package com.example.sportbet.model.match.external;

import lombok.Data;

@Data
public class JsonMatchResult {
    private Integer resultID;
    private String resultName;
    private Integer pointsTeam1;
    private Integer pointsTeam2;
    private Integer resultOrderID;
    private Integer resultTypeID;
    private String resultDescription;
}
