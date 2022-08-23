package com.example.sportbet.view;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sportbet.R;
import com.example.sportbet.controler.MatchService;
import com.fasterxml.jackson.core.JsonProcessingException;

public class ShowGamesActivity extends OnCreateClass {

    RecyclerView recycler_game;
    LinearLayoutManager linearLayoutManager;

    @Override
    protected void onSpecificCreate() {

        setContentView(R.layout.game_list);
        String leagueShortCut = getIntent().getStringExtra("leagueShortCut");
        String year = getIntent().getStringExtra("year");
        try {
            bindAdapterToListViewNew(leagueShortCut, year);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    private void bindAdapterToListViewNew(String leagueShortCut, String year) throws JsonProcessingException {
        var matches = MatchService.getMatchesForLeagueAndYear(leagueShortCut, year);

        recycler_game = findViewById(R.id.example_view);
        recycler_game.setLayoutManager(new LinearLayoutManager(this));
        recycler_game.setAdapter(new RowGameAdapter(matches, this));
    }
}
