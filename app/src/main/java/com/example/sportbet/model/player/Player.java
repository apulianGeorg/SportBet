package com.example.sportbet.model.player;

import com.example.sportbet.model.match.MatchBet;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class Player implements Comparable<Player> {

    private String name;
    private int playerId;
    private int points;
    private List<MatchBet> gameBets = new ArrayList<>();

    @Override
    public int compareTo(Player o) {
        if (this.points > o.points) {
            return -1;
        }
        if (this.points < o.points) {
            return 1;
        }
        return this.name.compareTo(o.name);
    }

    public void addBet(MatchBet bet) {
        gameBets.add(bet);
    }
}
