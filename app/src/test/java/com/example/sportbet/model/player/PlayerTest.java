package com.example.sportbet.model.player;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import com.example.sportbet.model.match.InvalidBetException;
import com.example.sportbet.model.match.MatchBet;

import org.junit.Test;

import java.util.ArrayList;

public class PlayerTest {

    @Test
    public void constructorTest() {
        Player player = new Player();
        assertNotNull(player);
    }

    @Test
    public void playerWithNameTest() {
        Player player = new Player();
        player.setName("Hugo");
        assertEquals("Hugo", player.getName());
        assertEquals(0, player.getPoints());
        assertEquals(0, player.getGameBets().size());
    }

    @Test
    public void addBetTest() throws InvalidBetException {
        Player player = new Player();
        player.setName("Hugo");
        MatchBet bet = new MatchBet();
        bet.setMatchId(123);
        bet.setGoals("1-2");
        player.addBet(bet);
        assertEquals("Hugo", player.getName());
        assertEquals(0, player.getPoints());
        assertEquals(1, player.getGameBets().size());
        assertEquals(123, player.getGameBets().get(0).getMatchId());
        assertEquals(0, player.getGameBets().get(0).getBetPointsForMatch());
        assertEquals("1 : 2", player.getGameBets().get(0).getResultAsString());
    }

    @Test
    public void comparatorTest() {
        ArrayList<Player> players = new ArrayList<>();
        Player player1 = new Player();
        player1.setPlayerId(1);
        player1.setName("Hugo");
        player1.setPoints(7);
        Player player2 = new Player();
        player2.setPlayerId(1);
        player2.setName("Boris");
        player2.setPoints(5);
        Player player3 = new Player();
        player3.setPlayerId(3);
        player3.setName("Xaver");
        player3.setPoints(1);
        Player player4 = new Player();
        player4.setPlayerId(4);
        player4.setName("Zeus");
        player4.setPoints(5);
        players.add(player1);
        players.add(player2);
        players.add(player3);
        players.add(player4);
        players.sort(Player::compareTo);

        assertEquals(4, players.size());
        assertEquals("Hugo", players.get(0).getName());
        assertEquals("Boris", players.get(1).getName());
        assertEquals("Zeus", players.get(2).getName());
        assertEquals("Xaver", players.get(3).getName());
    }
}