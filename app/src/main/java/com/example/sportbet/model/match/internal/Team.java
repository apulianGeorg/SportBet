package com.example.sportbet.model.match.internal;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;

import java.util.concurrent.Future;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Team {
    private final int teamId;
    private final String teamName;
    private final Future<Drawable> teamIcon;
}
