package com.example.sportbet.controler;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.internal.util.reflection.FieldSetter;

public class TeamServiceTest {

/*    private TeamIconService teamIconServiceMock = Mockito.mock(TeamIconService.class);
    private TeamService teamService = new TeamService();

    @Before
    public void init() throws NoSuchFieldException {
        Mockito.when(teamIconServiceMock.getTeamIcon(any(String.class))).thenReturn(null);
        new FieldSetter(teamService, teamService.getClass().getDeclaredField("teamIconService"))
                .set(teamIconServiceMock);
    }

    @Test
    public void getTeam() {
        var team1 = teamService.getTeam(1, "AS Roma", "teamIconUrl1");
        var team2 = teamService.getTeam(3, "SV Gutenstetten", "teamIconUrl2");
        var team3 = teamService.getTeam(1, "AS Roma", "teamIconUrl1");
        var team4 = teamService.getTeam(1, "AS Roma", "teamIconUrl1");
        var team5 = teamService.getTeam(1, "AS Oma", "teamIconUrl3");

        assertEquals(1, team1.getTeamId());
        assertEquals("AS Roma", team1.getTeamName());
        assertEquals(team1, team3);
        assertEquals(team1, team4);
        assertEquals(3, team2.getTeamId());
        assertEquals("SV Gutenstetten", team2.getTeamName());
        assertEquals(1, team5.getTeamId());
        assertEquals("AS Oma", team5.getTeamName());
        Mockito.verify(teamIconServiceMock,Mockito.times(1)).getTeamIcon("teamIconUrl1");
        Mockito.verify(teamIconServiceMock,Mockito.times(1)).getTeamIcon("teamIconUrl2");
        Mockito.verify(teamIconServiceMock,Mockito.times(1)).getTeamIcon("teamIconUrl3");

    }*/
}