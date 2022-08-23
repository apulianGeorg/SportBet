package com.example.sportbet.model.match.external;

import lombok.Data;

@Data
public class JsonTeam {
    private Integer teamId;
    private String teamName;
    private String shortName;
    private String teamIconUrl;
    private Object teamGroupName;
}
