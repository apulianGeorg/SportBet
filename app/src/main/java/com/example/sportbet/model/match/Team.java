package com.example.sportbet.model.match;

import android.graphics.drawable.Drawable;

import lombok.AllArgsConstructor;
import lombok.Data;

//TODO Test
@Data
@AllArgsConstructor
public class Team {
    private final int teamId;
    private final String teamName;
    private final Drawable teamIcon;
}
