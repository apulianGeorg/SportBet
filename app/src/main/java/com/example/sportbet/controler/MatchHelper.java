package com.example.sportbet.controler;

import com.example.sportbet.model.match.internal.Match;
import com.example.sportbet.model.bet.MatchBet;

import java.util.ArrayList;

//TODO: Brauchen wir das?
public class MatchHelper {

    public static Match getCorrespondingMatchViaMatchID(MatchBet matchBet, ArrayList<Match> matches) {
        return getCorrespondingMatchViaMatchID(matchBet.getMatchId(), matches);
    }

    public static Match getCorrespondingMatchViaMatchID(int matchId, ArrayList<Match> matches) {
        for (Match match : matches) {
            if (match.getMatchId() == matchId) {
                return match;
            }
        }
        //Zu jedem Tipp sollte auch ein Match vorhanden sein
        return null;
    }
}
