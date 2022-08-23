package com.example.sportbet.view;

import android.content.Intent;
import android.widget.Button;

import com.example.sportbet.R;


public class ShowBetHighScoreActivity extends OnCreateClass {
    @Override
    protected void onSpecificCreate() {

        String leagueShortCut = getIntent().getStringExtra("leagueShortCut");
        String year = getIntent().getStringExtra("year");
        setContentView(R.layout.show_bet_highscore);

        Button buttonShowGames = findViewById(R.id.show_game_button);
        buttonShowGames.setOnClickListener(v -> showGames(leagueShortCut, year));
        Button buttonBetGames = findViewById(R.id.bet_game_button);
        buttonBetGames.setOnClickListener(v -> betGames(leagueShortCut, year));
        Button buttonHighScore = findViewById(R.id.highScore_button);
        buttonHighScore.setOnClickListener(v -> showHighScores());
    }

    private void showGames(String leagueShortCut, String year) {
        Intent intent = new Intent(this, ShowGamesActivity.class);
        intent.putExtra("leagueShortCut", leagueShortCut);
        intent.putExtra("year", year);
        startActivity(intent);
    }

    private void betGames(String leagueShortCut, String year) {
        Intent intent = new Intent(this, BetPlayerActivity.class);
        intent.putExtra("leagueShortCut", leagueShortCut);
        intent.putExtra("year", year);
        startActivity(intent);
    }

    private void showHighScores() {
        Intent intent = new Intent(this, ShowHighScoreActivity.class);
        startActivity(intent);
    }
}
