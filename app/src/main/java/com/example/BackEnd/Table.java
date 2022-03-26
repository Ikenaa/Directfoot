package com.example.BackEnd;

import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.BackEnd.JsonModel.TableJsonModel;
import com.example.fragment.R;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;

public class Table extends AsyncTask<Void, Void, Void> {

    private ArrayList<Team> tableTeams;
    private View view;
    private LayoutInflater inflater;
    private LinearLayout leftSideTable;
    private LinearLayout rightSideTable;

    private String championship;



    public Table(String championship, View view, LayoutInflater inflater, LinearLayout leftSideTable, LinearLayout rightSideTable) throws Exception {
        tableTeams = new ArrayList<>();
        this.inflater = inflater;
        this.leftSideTable = leftSideTable;
        this.rightSideTable = rightSideTable;
        this.view = view;
        this.championship = championship;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        String urlString = "https://iphdata.lequipe.fr/iPhoneDatas/EFR/STD/ALL/V1/Football/ClassementsBase/current/" + championship + "/general.json";


        try {
            //read the answer of the api
            URL url = new URL(urlString);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(url.openStream()));
            String jsonString = bufferedReader.readLine();

            //Create an objet with this answer
            TableJsonModel.Root jsonObject = new Gson().fromJson(jsonString, TableJsonModel.Root.class);

            for(TableJsonModel.Item tmp : jsonObject.getItems().get(1).getObjet().getItems())
            {
                tableTeams.add(new Team(tmp));
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void unused) {
        Integer i= 1;
        for(Team team : tableTeams)
        {
            View leftSide = inflater.inflate(R.layout.left_side_table, view.findViewById(R.id.layoutLeftSide), false);
            ((TextView) leftSide.findViewById(R.id.rank)).setText(i.toString());
            ((TextView) leftSide.findViewById(R.id.teamName)).setText(team.getTeamName());
            ((ImageView) leftSide.findViewById(R.id.logo)).setImageBitmap(team.getLogo());
            leftSideTable.addView(leftSide, new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT, 0));


/*
            View rightSide = inflater.inflate(R.layout.right_side_table, view.findViewById(R.id.layoutRightSide), false);
            ((TextView) rightSide.findViewById(R.id.points)).setText("32");
            ((TextView) rightSide.findViewById(R.id.played)).setText("11");
            ((TextView) rightSide.findViewById(R.id.win)).setText("12");
            ((TextView) rightSide.findViewById(R.id.draw)).setText("13");
            ((TextView) rightSide.findViewById(R.id.lose)).setText("14");
            ((TextView) rightSide.findViewById(R.id.goalsFor)).setText("15");
            ((TextView) rightSide.findViewById(R.id.goalsAgainst)).setText("16");
            ((TextView) rightSide.findViewById(R.id.goalDiff)).setText("17");
            rightSideTable.addView(rightSide, new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT, 0));
*/

            View rightSide = inflater.inflate(R.layout.right_side_table, view.findViewById(R.id.layoutRightSide), false);
            ((TextView) rightSide.findViewById(R.id.points)).setText(String.valueOf(team.getPoints()));
            ((TextView) rightSide.findViewById(R.id.played)).setText(String.valueOf(team.getPlayedNumber()));
            ((TextView) rightSide.findViewById(R.id.win)).setText(String.valueOf(team.getWinNumber()));
            ((TextView) rightSide.findViewById(R.id.draw)).setText(String.valueOf(team.getDrawNumber()));
            ((TextView) rightSide.findViewById(R.id.lose)).setText(String.valueOf(team.getLoseNumber()));
            ((TextView) rightSide.findViewById(R.id.goalsFor)).setText(String.valueOf(team.getGoalsFor()));
            ((TextView) rightSide.findViewById(R.id.goalsAgainst)).setText(String.valueOf(team.getGoalsAgainst()));
            ((TextView) rightSide.findViewById(R.id.goalDiff)).setText(String.valueOf(team.getGoalDifference()));
            rightSideTable.addView(rightSide, new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT, 0));

            i+=1;
        }
    }

    public ArrayList<Team> getTableTeams() {
        return tableTeams;
    }
}
