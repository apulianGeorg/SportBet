package com.example.sportbet.view;

import android.widget.ListView;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sportbet.R;
import com.example.sportbet.controler.MatchService;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.List;

public class ShowGamesActivity extends OnCreateClass {

    RecyclerView recycler_game;
    LinearLayoutManager linearLayoutManager;

    @Override
    protected void onSpecificCreate() {

        setContentView(R.layout.game_list_new);
        String leagueShortCut = getIntent().getStringExtra("leagueShortCut");
        String year = getIntent().getStringExtra("year");
        try {
            bindAdapterToListViewNew(leagueShortCut, year);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    private void bindAdapterToListView(String leagueShortCut, String year) throws JsonProcessingException {
        var matches = MatchService.getMatchesForLeagueAndYear(leagueShortCut, year);
        RowGameAdapter adapter =
                new RowGameAdapter(this, R.layout.list_row_games, matches);

        ListView listView = findViewById(R.id.listview_game);
        listView.setAdapter(adapter);
    }

    private void bindAdapterToListViewNew(String leagueShortCut, String year) throws JsonProcessingException {
        var matches = MatchService.getMatchesForLeagueAndYear(leagueShortCut, year);

        //vgl. https://dev.to/theplebdev/recyclerview-activity-implementation-13mg
        recycler_game = (RecyclerView) findViewById(R.id.example_view);
        recycler_game.setLayoutManager(new LinearLayoutManager(this));
        recycler_game.setAdapter(new RowGameAdapterNew(matches, this));
    }
}
