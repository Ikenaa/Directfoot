package com.example.BackEnd;

import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.BackEnd.JsonModel.TableJsonModel;
import com.example.fragment.FragmentFixtures;
import com.example.fragment.MainActivity;
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
    private String championship;
    private View progressBar;
    private FragmentFixtures fragmentFixtures;

    public Table(String championship, LayoutInflater inflater, View viewGeneral, MainActivity currentActivity, LinearLayout layoutGeneral, View viewTable, LinearLayout underButton, View spinnerView, FragmentFixtures fragmentFixtures) throws Exception {

        this.fragmentFixtures = fragmentFixtures;
        this.championship = championship;
        tableTeams = new ArrayList<>();
    }

    @Override
    protected Void doInBackground(Void... voids) {

        //View layoutTable =  inflater.inflate(R.layout.fragment_classement_ligue,view.findViewById((R.id.layoutGeneral)),false);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT
        );

        params.setMargins(0, 800, 0, 0);

        progressBar = fragmentFixtures.getInflater().inflate(R.layout.loading, fragmentFixtures.getViewGeneral().findViewById((R.id.layoutGeneral)),false);

        fragmentFixtures.getCurrentActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                fragmentFixtures.getUnderButton().removeView(fragmentFixtures.getSpinnerView());
                fragmentFixtures.getLayoutGeneral().removeAllViews();
                fragmentFixtures.getLayoutGeneral().addView(progressBar, params);
            }
        });


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
        fragmentFixtures.getCurrentActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {

                fragmentFixtures.getLayoutGeneral().removeView(progressBar);
                LinearLayout leftSideTable = fragmentFixtures.getViewTable().findViewById(R.id.layoutLeftSide);
                LinearLayout rightSideTable = fragmentFixtures.getViewTable().findViewById(R.id.layoutRightSide);

                Integer i= 1;
                for(Team team : tableTeams)
                {
                    View leftSide = fragmentFixtures.getInflater().inflate(R.layout.left_side_table, fragmentFixtures.getViewGeneral().findViewById(R.id.layoutLeftSide), false);
                    ((TextView) leftSide.findViewById(R.id.rank)).setText(i.toString());
                    ((TextView) leftSide.findViewById(R.id.teamName)).setText(team.getTeamName());
                    ((ImageView) leftSide.findViewById(R.id.logo)).setImageBitmap(team.getLogo());
                    leftSideTable.addView(leftSide, new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT, 0));


                    View rightSide = fragmentFixtures.getInflater().inflate(R.layout.right_side_table, fragmentFixtures.getViewGeneral().findViewById(R.id.layoutRightSide), false);
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
                RelativeLayout layoutTable = fragmentFixtures.getViewTable().findViewById(R.id.layoutTable);
                fragmentFixtures.getLayoutGeneral().addView((View) layoutTable);
            }
        });

    }

    public ArrayList<Team> getTableTeams() {
        return tableTeams;
    }
}
