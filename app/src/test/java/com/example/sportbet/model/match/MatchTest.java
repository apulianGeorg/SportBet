package com.example.sportbet.model.match;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

public class MatchTest {

    @Test
    public void constructor_test() {
        Match match = new Match();
        assertNotNull(match);
        assertEquals(0, match.getMatchId());
        assertNull(match.getMatchTime());
        assertNull(match.getLocation());
        assertEquals("  :  ", match.getResult());
        assertNull(match.getTeam1());
        assertNull(match.getTeam2());
        assertFalse(match.isMatchIsFinished());
    }

    @Test
    public void properties_test() {
        Match match = new Match();
        match.setMatchId(1);
        match.setMatchIsFinished(true);
        match.setMatchTime("2022-07-13T09:23:59");
        match.setLocation("Gutenstetten");
        match.setTeam1(getTeam(12, "Team1"));
        match.setTeam2(getTeam(13, "Team2"));
        List<Goal> goalList1 = new ArrayList<>();
        goalList1.add(new Goal());
        goalList1.add(new Goal());
        match.setGoalsTeam1(goalList1);
        List<Goal> goalList2 = new ArrayList<>();
        goalList2.add(new Goal());
        goalList2.add(new Goal());
        goalList2.add(new Goal());
        match.setGoalsTeam2(goalList2);

        assertEquals(1, match.getMatchId());
        assertTrue(match.isMatchIsFinished());
        assertEquals("Gutenstetten", match.getLocation());
        assertEquals(LocalDateTime.of(2022, 7, 13, 9, 23, 59), match.getMatchTime());
        assertEquals("2 : 3", match.getResult());
        assertEquals(12, match.getTeam1().getTeamId());
        assertEquals(13, match.getTeam2().getTeamId());
    }

    @Test
    public void setIncorrectMatchTime() {
        Match match = new Match();
        assertThrows(DateTimeParseException.class, () -> match.setMatchTime("2022-07-13T09:23:60"));
        assertThrows(DateTimeParseException.class, () -> match.setMatchTime("2022-07-32T09:23:59"));
        assertThrows(DateTimeParseException.class, () -> match.setMatchTime("2022-13-02T09:23:59"));
        assertThrows(DateTimeParseException.class, () -> match.setMatchTime("Hugo"));
    }

    private Team getTeam(int teamId, String teamName) {
        return new Team(teamId, teamName, null);
    }

}