package com.example.sportbet.controler;

import com.example.sportbet.controler.mapper.JsonToMatchListMap;
import com.example.sportbet.controler.reader.HtmlClient;
import com.example.sportbet.model.match.internal.Match;

import java.util.ArrayList;
import java.util.HashMap;

public class MatchService {

    private static final HashMap<String, ArrayList<Match>> leagueYearMatchList = new HashMap<>();
    private static final TeamService teamService = new TeamService();
    private static final JsonToMatchListMap jsonToMatchListMap = new JsonToMatchListMap(teamService);

    public static void generateMatchesForLeagueAndYear(String leagueShortCut, String year) {
        callXmlParser(leagueShortCut, year);
    }

    public static ArrayList<Match> getMatchesForLeagueAndYear(String leagueShortCut, String year) {
        if (leagueShortCut == null || year == null) {
            return new ArrayList<>();
        }
        return leagueYearMatchList.get(leagueShortCut + year);
    }

    private static void callXmlParser(String leagueShortCut, String year) {
        try {
            var future = new HtmlClient().ReadHtmlPageAsString(UrlConstants.UrlPrefix + leagueShortCut + "/" + year);
            var matchList = jsonToMatchListMap.mapJsonToMatchList(future);
            leagueYearMatchList.put(leagueShortCut + year, matchList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
