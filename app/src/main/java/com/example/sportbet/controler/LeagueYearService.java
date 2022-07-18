package com.example.sportbet.controler;


import com.example.sportbet.controler.exception.InvalidResourcesException;
import com.example.sportbet.controler.exception.InvalidYearException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Objects;

public class LeagueYearService {

    private static final String leagueSeparator = ";";
    private static final String yearSeparator = ",";
    private static HashMap<String, String> leagueShortCutMap;

    public static LinkedHashMap<String, ArrayList<String>> provideLeagueAndYear(String[] stringArray) {
        LinkedHashMap<String, ArrayList<String>> leagueYearsList = new LinkedHashMap<>();
        if (stringArray == null) {
            return leagueYearsList;
        }
        Arrays.sort(stringArray);
        for (String item : stringArray) {
            String key = getKey(item);
            List<String> values = getValues(item);
            if (key == null || values == null) {
                continue;
            }
            if (!leagueYearsList.containsKey(key)) {
                leagueYearsList.put(key, new ArrayList<>());
            }
            Objects.requireNonNull(leagueYearsList.get(key)).addAll(values);
        }
        return leagueYearsList;
    }

    public static String getLeagueShortCut(String[] stringArray, String league) throws InvalidResourcesException {
        if (stringArray == null) {
            throw new InvalidResourcesException("Resource league_shortcut_array is not defined");
        }
        if (leagueShortCutMap == null) {
            leagueShortCutMap = getLeagueShortCutMap(stringArray);
        }
        String leagueShortCut = leagueShortCutMap.get(league);
        if (leagueShortCut == null) {
            throw new InvalidResourcesException("In Resource league_shortcut_array league " + league + " not found");
        }
        return leagueShortCut;
    }

    public static String getYearShortCut(String year) throws InvalidYearException {
        if (year == null || year.length() < 4) {
            throw new InvalidYearException("Year has to be formatted as 'yyyy' but instead was '" + year + "'");
        }
        return year.substring(0, 4);
    }

    private static HashMap<String, String> getLeagueShortCutMap(String[] stringArray) {
        HashMap<String, String> map = new HashMap<>();
        for (String entry : stringArray) {
            String[] values = entry.split(leagueSeparator);
            map.put(values[0], values[1]);
        }
        return map;
    }

    private static List<String> getValues(String item) {
        String[] values = item.split(leagueSeparator);
        return (values.length == 2) ?
                Arrays.asList(item.split(leagueSeparator)[1].split(yearSeparator)) : null;
    }

    private static String getKey(String item) {
        return item.split(leagueSeparator)[0];
    }
}
