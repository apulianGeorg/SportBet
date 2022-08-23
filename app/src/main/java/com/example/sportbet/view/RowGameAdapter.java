package com.example.sportbet.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sportbet.R;
import com.example.sportbet.model.match.internal.Match;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class RowGameAdapter extends ArrayAdapter<Match> {
    List<Match> matches;
    Context context;

    public RowGameAdapter(Context context, int textViewResourceId, List<Match> matches) {
        super(context, textViewResourceId, matches);
        this.matches = matches;
        this.context = context;
    }

    static class ViewHolder {
        ImageView team1Icon;
        ImageView team2Icon;
        TextView team1Name;
        TextView team2Name;
        TextView result;
        EditText bet;
    }

    @Override
    public int getCount() {
        return super.getCount();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) getContext()
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.list_row_games, parent, false);

            holder = new ViewHolder();
            holder.team1Icon = convertView.findViewById(R.id.team1Icon);
            holder.team1Name = convertView.findViewById(R.id.team1Name);
            holder.result = convertView.findViewById(R.id.result);
            holder.team2Name = convertView.findViewById(R.id.team2Name);
            holder.team2Icon = convertView.findViewById(R.id.team2Icon);
            //holder.bet = convertView.findViewById(R.id.bet);

            //make Bet field invisible
            //holder.bet.setVisibility(View.INVISIBLE);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.team1Name.setText(matches.get(position).getTeam1().getTeamName());
        holder.result.setText(matches.get(position).getResult());
        holder.team2Name.setText(matches.get(position).getTeam2().getTeamName());
        try {
            holder.team1Icon.setImageDrawable(matches.get(position).getTeam1().getTeamIcon().get());
            holder.team2Icon.setImageDrawable(matches.get(position).getTeam2().getTeamIcon().get());
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }

        return convertView;
    }
}
