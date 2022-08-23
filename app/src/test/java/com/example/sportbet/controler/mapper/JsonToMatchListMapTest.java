package com.example.sportbet.controler.mapper;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;

import com.example.sportbet.controler.TeamService;
import com.example.sportbet.model.match.external.JsonMatch;
import com.example.sportbet.model.match.internal.Team;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.Test;
import org.mockito.Mockito;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class JsonToMatchListMapTest {
    private final TeamService teamServiceMock = Mockito.mock(TeamService.class);
    private final JsonToMatchListMap jsonToMatchListMap = new JsonToMatchListMap(teamServiceMock);

    /*@Test
    public void unknownPropertyDoesNotMatter() throws Exception {
        jsonToMatchListMap.mapJsonToMatchList(readTestData("jsonInputUnknownProperty.json"));
    }

    @Test
    public void mapOneMatch() throws Exception {
        Team teamMock1 = new Team(145, "Italien", null);
        Team teamMock2 = new Team(755, "England", null);
        Mockito.when(teamServiceMock.getTeam(any(Integer.class), any(String.class), any(String.class)))
                .thenReturn(teamMock1)
                .thenReturn(teamMock2);

        var matchList =
                jsonToMatchListMap.mapJsonToMatchList(readTestData("jsonInputOneMatch.json"));

        assertEquals(1, matchList.size());
        var match = matchList.get(0);
        assertEquals(61934, match.getMatchId());
        assertEquals("2021-07-11T21:00:00", match.getMatchTime());
        assertEquals("1 : 1", match.getResult());
        assertNull(match.getLocation());
        assertEquals(teamMock1.getTeamId(), match.getTeam1().getTeamId());
        assertEquals(teamMock1.getTeamName(), match.getTeam1().getTeamName());
        assertEquals(teamMock1.getTeamIcon(), match.getTeam1().getTeamIcon());
        assertEquals(teamMock2.getTeamId(), match.getTeam2().getTeamId());
        assertEquals(teamMock2.getTeamName(), match.getTeam2().getTeamName());
        assertEquals(teamMock2.getTeamIcon(), match.getTeam2().getTeamIcon());

        assertEquals(1, match.getGoalsTeam1().size());
        assertNull(match.getGoalsTeam1().get(0).getMinute());
        assertEquals("ScorerTeam1", match.getGoalsTeam1().get(0).getScorerName());
        assertFalse(match.getGoalsTeam1().get(0).isOwnGoal());
        assertFalse(match.getGoalsTeam1().get(0).isPenalty());
        assertEquals(1, match.getGoalsTeam2().size());
        assertNull(match.getGoalsTeam2().get(0).getMinute());
        assertEquals("ScorerTeam2", match.getGoalsTeam2().get(0).getScorerName());
        assertFalse(match.getGoalsTeam2().get(0).isOwnGoal());
        assertFalse(match.getGoalsTeam2().get(0).isPenalty());

    }

    @Test
    public void mapMoreMatches() throws Exception {
        Team teamMock1 = new Team(145, "Italien", null);
        Mockito.when(teamServiceMock.getTeam(any(Integer.class), any(String.class), any(String.class)))
                .thenReturn(teamMock1);

        var matchList =
                jsonToMatchListMap.mapJsonToMatchList(readTestData("jsonInputMoreMatches.json"));

        assertEquals(9, matchList.size());

    }

    @Test
    public void caseInsensitiveTest() throws Exception {
        var jsonStr = (readTestData("jsonInputMoreMatchesBl1.json"));

        ObjectMapper mapper = new ObjectMapper()
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);

        var jsonMatches = mapper.readValue(jsonStr, JsonMatch[].class);

        assertTrue(jsonMatches.length > 0);

    }

    private String readTestData(String fileName) {
        var data = new StringBuilder();
        try {
            String PATH_PREFIX = "src\\test\\java\\com\\example\\sportbet\\controler\\mapper\\";
            File myObj = new File(PATH_PREFIX + fileName);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                data.append(myReader.nextLine());
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("File " + fileName + " not found");
            e.printStackTrace();
        }
        return String.valueOf(data);
    }*/
}