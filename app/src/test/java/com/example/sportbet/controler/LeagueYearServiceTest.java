package com.example.sportbet.controler;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import com.example.sportbet.controler.exception.InvalidResourcesException;
import com.example.sportbet.controler.exception.InvalidYearException;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Objects;

public class LeagueYearServiceTest {

    @Test
    public void inputIsNullTest() {
        HashMap<String, ArrayList<String>> leagueYearsList =
                LeagueYearService.provideLeagueAndYear(null);
        assertTrue(leagueYearsList.isEmpty());
    }

    @Test
    public void oneLeagueWithNullYearTest() {
        String[] input = new String[]{"Bundesliga;"};
        HashMap<String, ArrayList<String>> leagueYearsList =
                LeagueYearService.provideLeagueAndYear(input);
        assertTrue(leagueYearsList.isEmpty());
    }

    @Test
    public void nullLeagueWithOneYearTest() {
        String[] input = new String[]{"2022"};
        HashMap<String, ArrayList<String>> leagueYearsList =
                LeagueYearService.provideLeagueAndYear(input);
        assertTrue(leagueYearsList.isEmpty());
    }

    @Test
    public void manyLeagueWithManyYearTest() {
        String[] input = new String[]{"Bundesliga;2021/2022,2022/2023", "EM;2018,2022", "NFL;2022/2023"};
        HashMap<String, ArrayList<String>> leagueYearsList =
                LeagueYearService.provideLeagueAndYear(input);
        assertEquals(3, leagueYearsList.size());
        assertEquals(2, Objects.requireNonNull(leagueYearsList.get("Bundesliga")).size());
        assertTrue(Objects.requireNonNull(leagueYearsList.get("Bundesliga")).contains("2021/2022"));
        assertTrue(Objects.requireNonNull(leagueYearsList.get("Bundesliga")).contains("2022/2023"));
        assertEquals(2, Objects.requireNonNull(leagueYearsList.get("EM")).size());
        assertTrue(Objects.requireNonNull(leagueYearsList.get("EM")).contains("2018"));
        assertTrue(Objects.requireNonNull(leagueYearsList.get("EM")).contains("2022"));
        assertEquals(1, Objects.requireNonNull(leagueYearsList.get("NFL")).size());
        assertTrue(Objects.requireNonNull(leagueYearsList.get("NFL")).contains("2022/2023"));
    }

    @Test
    public void sortedOrderTest() {
        String[] input = new String[]{"NFL;2021/2022,2022/2023", "Bundesliga;2018/2019", "EM;2022"};
        LinkedHashMap<String, ArrayList<String>> leagueYearsList = LeagueYearService.provideLeagueAndYear(input);
        String[] keys = leagueYearsList.keySet().toArray(new String[0]);
        assertEquals("Bundesliga", keys[0]);
        assertEquals("EM", keys[1]);
        assertEquals("NFL", keys[2]);
    }

    @Test
    public void getYearShortCutNullTest() {
        //noinspection ConstantConditions
        assertThrows(InvalidYearException.class, () -> LeagueYearService.getYearShortCut(null));
    }

    @Test
    public void getYearShortCutYearToShortTest() {
        assertThrows(InvalidYearException.class, () -> LeagueYearService.getYearShortCut("123"));
    }

    @Test
    public void getYearShortTest() throws InvalidYearException {
        String year = LeagueYearService.getYearShortCut("2022");
        String year2 = LeagueYearService.getYearShortCut("2022/2023");
        assertEquals("2022", year);
        assertEquals("2022", year2);
    }

    @Test
    public void getLeagueShortCutNullResourceTest() {
        //noinspection ConstantConditions
        assertThrows(InvalidResourcesException.class, ()
                -> LeagueYearService.getLeagueShortCut(null, ""));
    }

    @Test
    public void getLeagueShortCutLeagueNotFoundTest() {
        String[] inputArr = new String[]{"Bundesliga 1;bl1", "Bundesliga 2;bl2", "EM;em"};
        assertThrows(InvalidResourcesException.class, ()
                -> LeagueYearService.getLeagueShortCut(inputArr, "NFL"));
    }

    @Test
    public void getLeagueShortCutLeagueFoundTest() throws InvalidResourcesException {
        String[] inputArr = new String[]{"Bundesliga 1;bl1", "Bundesliga 2;bl2", "EM;em"};
        String leagueShortCut1 = LeagueYearService.getLeagueShortCut(inputArr, "EM");
        String leagueShortCut2 = LeagueYearService.getLeagueShortCut(inputArr, "Bundesliga 1");
        assertEquals("em", leagueShortCut1);
        assertEquals("bl1", leagueShortCut2);
    }
}