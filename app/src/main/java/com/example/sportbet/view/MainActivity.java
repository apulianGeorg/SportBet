package com.example.sportbet.view;

import android.Manifest;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.core.app.ActivityCompat;

import com.example.sportbet.R;
import com.example.sportbet.controler.LeagueYearService;
import com.example.sportbet.controler.MatchService;
import com.example.sportbet.controler.exception.InvalidResourcesException;
import com.example.sportbet.controler.exception.InvalidYearException;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Objects;

public class MainActivity extends OnCreateClass {
    //TODO: Refactoring MVVM
    //TODO: statische Klassen?
    //TODO: TESTS
    /*
    TODO: Einstellungen um auswählen zu können wieviele Punkte vergeben werden oder neue Ligen anlegen
    Liga und Jahr auswählen
    Spiele anzeigen, Spiele tippen, Highscore
    Spiele tippen: Name auswählen bzw neuen Spieler anlegen
    */

    private String selectedYear;
    private String selectedLeague;

    private Button buttonShowGame;
    private Spinner leagueSpinner;
    private Spinner yearSpinner;

    @Override
    protected void onSpecificCreate() {
        setContentView(R.layout.select_league_year);

        getElementsOfView();
        getReadAndWritePermission();

        LinkedHashMap<String, ArrayList<String>> leagueYearsList = getLeagueYearsList();
        setupLeagueSpinner(leagueYearsList);

        handleEvents(leagueYearsList);
    }

    private void getReadAndWritePermission() {
        ActivityCompat.requestPermissions(MainActivity.this,
                new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                1);

        ActivityCompat.requestPermissions(MainActivity.this,
                new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                1);
    }

    private void getElementsOfView() {
        buttonShowGame = findViewById(R.id.ok_button);
        leagueSpinner = findViewById(R.id.league_select_spinner);
        yearSpinner = findViewById(R.id.year_select_spinner);
    }

    private void handleEvents(LinkedHashMap<String, ArrayList<String>> leagueYearsList) {
        handleSpinnerEvents(leagueYearsList);
        handleButtonEvents();
    }

    private void handleButtonEvents() {
        Button buttonShowGame = findViewById(R.id.ok_button);
        buttonShowGame.setOnClickListener(v -> okClicked());
    }

    private void okClicked() {
        try {
            String leagueShortCut = LeagueYearService
                    .getLeagueShortCut(getResources().getStringArray(R.array.league_shortcut_array), selectedLeague);
            String year = LeagueYearService
                    .getYearShortCut(selectedYear);
            MatchService.generateMatchesForLeagueAndYear(leagueShortCut, year);
            Intent intent = new Intent(this, ShowBetHighScoreActivity.class);
            intent.putExtra("leagueShortCut", leagueShortCut);
            intent.putExtra("year", year);
            startActivity(intent);
        } catch (InvalidResourcesException | InvalidYearException e) {
            TextView textView = findViewById(R.id.error_message);
            textView.setText(e.getMessage());
        }

    }

    private void handleSpinnerEvents(LinkedHashMap<String, ArrayList<String>> leagueYearsList) {
        handleLeagueSpinnerEvents(leagueYearsList);
        handleYearSpinnerEvents();
    }

    private void handleLeagueSpinnerEvents(LinkedHashMap<String, ArrayList<String>> leagueYearsList) {
        leagueSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                selectedLeague = parentView.getItemAtPosition(position).toString();
                setupYearSpinner(selectedLeague, leagueYearsList);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                selectedLeague = "";
                selectedYear = "";
                setupYearSpinner(selectedLeague, leagueYearsList);
                buttonShowGame.setEnabled(false);
            }

        });
    }

    private void handleYearSpinnerEvents() {
        yearSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                selectedYear = parentView.getItemAtPosition(position).toString();
                buttonShowGame.setEnabled(true);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                selectedYear = "";
                buttonShowGame.setEnabled(false);
            }

        });
    }

    private LinkedHashMap<String, ArrayList<String>> getLeagueYearsList() {
        return LeagueYearService.provideLeagueAndYear(
                getResources().getStringArray(R.array.league_year_array));
    }

    private void setupLeagueSpinner(LinkedHashMap<String, ArrayList<String>> leagueYearsList) {
        String[] keys = leagueYearsList.keySet().toArray(new String[0]);
        setupSpinner(leagueSpinner, keys);
    }

    private void setupYearSpinner(String selectedLeague, LinkedHashMap<String, ArrayList<String>> leagueYearsList) {
        String[] years = Objects.requireNonNull(leagueYearsList.get(selectedLeague)).toArray(new String[0]);
        setupSpinner(yearSpinner, years);
    }

    private void setupSpinner(Spinner spinner, String[] items) {
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, items);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);
    }
}