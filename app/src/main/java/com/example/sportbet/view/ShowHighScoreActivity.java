package com.example.sportbet.view;

import android.widget.TextView;

import com.example.sportbet.R;

public class ShowHighScoreActivity extends OnCreateClass {

    @Override
    protected void onSpecificCreate() {
        setContentView(R.layout.game_list);
        TextView textView = findViewById(R.id.dummyText);
        textView.setText("ShowHighScore Activity");
    }
}
