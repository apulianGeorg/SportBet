package com.example.sportbet.model.match;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThrows;

import org.junit.Test;

public class TeamTest {

    @Test
    public void nullPointerExceptionTest() {
        assertThrows(NullPointerException.class, () -> new Team(0, null, null));
    }

    @Test
    public void teamWithoutIconTest() {
        Team team = new Team(0, "AS Roma", null);
        assertEquals(0, team.getTeamId());
        assertNull(team.getTeamIcon());
        assertEquals("AS Roma", team.getTeamName());
    }
}