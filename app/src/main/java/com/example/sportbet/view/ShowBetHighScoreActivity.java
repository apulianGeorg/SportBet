package com.example.sportbet.view;

import android.content.Intent;
import android.widget.Button;

import com.example.sportbet.R;


public class ShowBetHighScoreActivity extends OnCreateClass {
    @Override
    protected void onSpecificCreate() {

        String selectedLeague = getIntent().getStringExtra("selectedLeague");
        String selectedYear = getIntent().getStringExtra("selectedYear");
        setContentView(R.layout.show_bet_highscore);

        Button buttonShowGames = findViewById(R.id.show_game_button);
        buttonShowGames.setOnClickListener(v -> showGames());
        Button buttonBetGames = findViewById(R.id.bet_game_button);
        buttonBetGames.setOnClickListener(v -> betGames());
        Button buttonHighScore = findViewById(R.id.highScore_button);
        buttonHighScore.setOnClickListener(v -> showHighScores());
    }

    private void showGames() {
        Intent intent = new Intent(this, ShowGamesActivity.class);
        startActivity(intent);
    }

    private void betGames() {
        Intent intent = new Intent(this, BetPlayerActivity.class);
        startActivity(intent);
    }

    private void showHighScores() {
        Intent intent = new Intent(this, ShowHighScoreActivity.class);
        startActivity(intent);
    }
}
