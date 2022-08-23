package com.example.sportbet.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sportbet.InternalConstants;
import com.example.sportbet.R;
import com.example.sportbet.model.match.internal.Match;
import com.example.sportbet.model.match.internal.ShowMatch;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class RowGameAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final List<ShowMatch> showList;
    private final Context context;

    public RowGameAdapter(List<Match> matches, Context context) {
        this.showList = getShowMatchList(matches);
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        if (viewType == InternalConstants.VIEW_TYPE_GAME_HEADER) {
            ViewGroup viewGroup = (ViewGroup) inflater.inflate(R.layout.list_row_games_header, parent, false);
            return new GameHeaderHolder(viewGroup);
        } else {
            ViewGroup viewGroup = (ViewGroup) inflater.inflate(R.layout.list_row_games, parent, false);
            return new GameHolder(viewGroup);
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (showList.get(position).isHeader()) {
            return InternalConstants.VIEW_TYPE_GAME_HEADER;
        }
        return InternalConstants.VIEW_TYPE_GAME;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof GameHeaderHolder gameHeaderHolder) {
            gameHeaderHolder.teamHeader.setText(showList.get(position).getGroup().getGroupName());
        } else if (holder instanceof GameHolder gameHolder) {
            gameHolder.team1Name.setText(showList.get(position).getTeam1().getTeamName());
            gameHolder.team2Name.setText(showList.get(position).getTeam2().getTeamName());
            gameHolder.result.setText(showList.get(position).getResult());
            try {
                gameHolder.team1Icon.setImageDrawable(showList.get(position).getTeam1().getTeamIcon().get());
                gameHolder.team2Icon.setImageDrawable(showList.get(position).getTeam2().getTeamIcon().get());
            } catch (ExecutionException | InterruptedException e) {
                e.printStackTrace();
            }
            gameHolder.itemView.setOnClickListener(view -> {
                //TODO: Show Gamedetails (activity with 2 screens)
                //1. ergebniss, tore, schützen, zeit, ort
                //2. wetten (wer,was,wieviel punkte)

                System.out.println("clicked by Position " + position);
            });
            gameHolder.itemView.setOnLongClickListener(view -> {
                //TODO: Show Gamedetails
                System.out.println("clicked long by Position " + position);
                return true;
            });
        }
    }

    @Override
    public int getItemCount() {
        return showList.size();
    }

    //TODO:Auslagern???
    private List<ShowMatch> getShowMatchList(List<Match> matches) {
        var showMatches = new ArrayList<ShowMatch>();
        var lastGroupId = 0;
        for (var match : matches) {
            var actualGroupId = match.getGroup().getGroupOrderID();
            if (lastGroupId != actualGroupId) {
                lastGroupId = actualGroupId;
                showMatches.add(getShowMatchHeader(match));
            }
            showMatches.add(getShowMatch(match));
        }
        return showMatches;
    }

    private ShowMatch getShowMatchHeader(Match match) {
        var showMatch = new ShowMatch();
        showMatch.setHeader(true);
        showMatch.setGroup(match.getGroup());
        return showMatch;
    }

    private ShowMatch getShowMatch(Match match) {
        var showMatch = new ShowMatch();
        showMatch.setMatch(match);
        return showMatch;
    }

    private static class GameHeaderHolder extends RecyclerView.ViewHolder {

        TextView teamHeader;

        public GameHeaderHolder(@NonNull View itemView) {
            super(itemView);
            teamHeader = itemView.findViewById(R.id.teamHeader);
        }
    }

    private static class GameHolder extends RecyclerView.ViewHolder {
        ImageView team1Icon, team2Icon;
        TextView team1Name, team2Name, result;

        public GameHolder(@NonNull View itemView) {
            super(itemView);
            team1Icon = itemView.findViewById(R.id.team1Icon);
            team1Name = itemView.findViewById(R.id.team1Name);
            result = itemView.findViewById(R.id.result);
            team2Icon = itemView.findViewById(R.id.team2Icon);
            team2Name = itemView.findViewById(R.id.team2Name);
        }
    }
}
