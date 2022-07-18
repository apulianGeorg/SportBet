package com.example.sportbet.model.match;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

import org.junit.Test;

public class MatchBetTest {

    @Test
    public void resultNullGoalsTest() throws InvalidBetException {
        MatchBet matchBet = new MatchBet();
        assertThrows(NullPointerException.class, () -> matchBet.setGoals(null));
    }

    @Test
    public void constructorResultWithMinusTest() throws InvalidBetException {
        MatchBet matchBet = new MatchBet();
        matchBet.setMatchId(12);
        matchBet.setGoals("1-3");
        assertMatchBetValues(matchBet);
    }

    @Test
    public void constructorTest() throws InvalidBetException {
        MatchBet matchBet = new MatchBet();
        matchBet.setMatchId(12);
        matchBet.setGoals("1:3");
        assertMatchBetValues(matchBet);

    }

    private void assertMatchBetValues(MatchBet matchBet) {
        assertEquals(12, matchBet.getMatchId());
        assertEquals(0, matchBet.getBetPointsForMatch());
        assertEquals("1 : 3", matchBet.getResultAsString());
    }

}