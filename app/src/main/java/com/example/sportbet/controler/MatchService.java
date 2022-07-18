package com.example.sportbet.controler;

import com.example.sportbet.controler.exception.InvalidResourcesException;
import com.example.sportbet.controler.mapper.JsonToMatchListMap;
import com.example.sportbet.controler.reader.MyHtmlReader;
import com.example.sportbet.model.match.Match;

import java.util.ArrayList;
import java.util.HashMap;

//TODO: Tests
public class MatchService {

    private static final HashMap<String, ArrayList<Match>> leagueYearMatchList = new HashMap<>();

    public static void generateMatchesForLeagueAndYear(String leagueShortCut, String year) {
        callXmlParser(leagueShortCut, year);
    }

    public static ArrayList<Match> getMatchesForLeagueAndYear(String leagueShortCut, String year) {
        return leagueYearMatchList.get(leagueShortCut + year);
    }

    private static void callXmlParser(String leagueShortCut, String year) {
        try {
            String jsonStr = new MyHtmlReader().execute().get();
            ArrayList<Match> matchList = JsonToMatchListMap.mapJsonToMatchList(jsonStr);
            leagueYearMatchList.put(leagueShortCut + year, matchList);
        } catch (Exception | InvalidResourcesException e) {
            e.printStackTrace();
        }
    }
}
