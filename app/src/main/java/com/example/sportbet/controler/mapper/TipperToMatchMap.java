package com.example.sportbet.controler.mapper;

import com.example.sportbet.controler.MatchHelper;
import com.example.sportbet.controler.MatchService;
import com.example.sportbet.model.match.Match;
import com.example.sportbet.model.match.MatchBet;
import com.example.sportbet.model.player.Player;

import java.util.ArrayList;
import java.util.List;

//TODO: Tests
public class TipperToMatchMap {
    public static ArrayList<Match> mapFileToTipperList(List<Player> tipperList, String tipperName) {
        ArrayList<Match> matchTipperList = new ArrayList<>();
        //TODO: das passt noch nicht da muss Jahr undLeague mit
        ArrayList<Match> matches = MatchService.getMatchesForLeagueAndYear("","");
        for (Player player : tipperList) {
            if (player.getName().equals(tipperName)) {
                for (MatchBet matchBet : player.getGameBets()) {
                    matchTipperList.add(getMatch(matchBet, matches));
                }
            }
        }
        return matchTipperList;
    }

    //TODO: Was macht die Methode
    private static Match getMatch(MatchBet matchTipp, ArrayList<Match> matches) {
        Match correspondingMatch =
                MatchHelper.getCorrespondingMatchViaMatchID(matchTipp, matches);
        if (correspondingMatch == null) {
            //Evtl. werden die ehemals getippten Spiele nichtmehr angezeigt
            return null;
        }
/*        Match match = new Match(correspondingMatch);
        match.getTeam1().setGoalsTeam(matchTipp.getGoalsTeam1());
        match.getTeam2().setGoalsTeam(matchTipp.getGoalsTeam2());*/
        return new Match();
    }

}
