package com.example.sportbet.view;

import android.content.Intent;
import android.widget.Button;

import com.example.sportbet.R;

public class BetPlayerActivity extends OnCreateClass {
    @Override
    protected void onSpecificCreate() {

        setContentView(R.layout.bet_player);

        Button buttonPlayerSelected = findViewById(R.id.ok_button);
        buttonPlayerSelected.setOnClickListener(v -> okClicked());
    }

    private void okClicked() {
        //TODO: Prüfe, dass Player gesetzt oder ausgewählt wurde
        Intent intent = new Intent(this, BetGamesActivity.class);
        startActivity(intent);
    }
}
