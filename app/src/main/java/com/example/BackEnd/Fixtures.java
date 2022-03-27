package com.example.BackEnd;

import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.BackEnd.JsonModel.CurrentDayJsonModel;
import com.example.BackEnd.JsonModel.FixturesJsonModel;
import com.example.fragment.R;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class Fixtures extends AsyncTask<Void, Void, Void> {

    private ArrayList<Game> fixturesGame;
    private String championship;
    private View view;
    private AppCompatActivity currentActivity;
    private Integer nbFixtures;
    private LinearLayout layoutFixtures;
    private LayoutInflater inflater;
    private String day;
    private View progressBar;

    public Fixtures(String championship, View view, AppCompatActivity currentActivity, LayoutInflater inflater, LinearLayout layoutFixtures) throws IOException {
        this.layoutFixtures = layoutFixtures;
        this.inflater = inflater;
        this.currentActivity = currentActivity;
        this.view = view;
        this.championship = championship;
        fixturesGame = new ArrayList<>();

    }

    @Override
    protected Void doInBackground(Void... voids) {

        layoutFixtures = view.findViewById(R.id.layoutFixtures);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT
        );

        params.setMargins(0, 500, 0, 0);

        progressBar = inflater.inflate(R.layout.loading,view.findViewById((R.id.layoutFixtures)),false);

        layoutFixtures.addView(progressBar, params);
            try {
                if(day == null) {
                    String urlString2 = "https://iphdata.lequipe.fr/iPhoneDatas/EFR/STD/ALL/V1/Football/CalendarList/CompetitionPhase/" + championship + "/current/container.json";
                    URL url2 = new URL(urlString2);
                    BufferedReader bufferedReader2 = new BufferedReader(new InputStreamReader(url2.openStream()));
                    String jsonString2 = bufferedReader2.readLine();

                    CurrentDayJsonModel.Root jsonObject2 = new Gson().fromJson(jsonString2, CurrentDayJsonModel.Root.class);

                    nbFixtures = 1;
                    for (CurrentDayJsonModel.SubNav subNav : jsonObject2.getFeeds().get(0).getSub_nav()) {
                        if (subNav.isCurrent())
                            day = nbFixtures.toString();

                        nbFixtures += 1;
                    }

                    if (day.equals("1")) {
                        day += "r";
                    }
                }

                String urlString = "https://iphdata.lequipe.fr/iPhoneDatas/EFR/STD/ALL/V1/Football/CalendarList/CompetitionPhase/" + championship + "/current/" + day +"e-journee.json";

                URL url = new URL(urlString);
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(url.openStream()));
                String jsonString = bufferedReader.readLine();

                FixturesJsonModel.Root jsonObject = new Gson().fromJson(jsonString, FixturesJsonModel.Root.class);

                for (FixturesJsonModel.Item item : jsonObject.getItems())
                {
                    if(item.get__type().equals("calendar_widget_list"))
                    {
                        for (FixturesJsonModel.Item itemBis : item.getItems()) {
                            fixturesGame.add(new Game(itemBis.getEvent()));
                        }
                    }
                }
            }
            catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        return null;
    }

    @Override
    protected void onPostExecute(Void voids) {
        layoutFixtures.removeView(progressBar);
        Spinner spinner = view.findViewById(R.id.spinner);



        ArrayAdapter<String> adapter = new ArrayAdapter<String>(currentActivity, R.layout.spinner_item);

        for(Integer i = 1; i< nbFixtures; i++)
        {
            adapter.add(i+"e journée");
        }
        spinner.setAdapter(adapter);
        spinner.setSelection(Integer.parseInt(day)-1);

        for(Game game : fixturesGame)
        {
            View truc =  inflater.inflate(R.layout.match_component,view.findViewById((R.id.compet)),false);

            ((TextView)truc.findViewById(R.id.team1)).setText(game.getHomeTeamName());
            ((TextView)truc.findViewById(R.id.team2)).setText(game.getAwayTeamName());
            ((TextView)truc.findViewById(R.id.actual)).setText(game.getHour());
            ((TextView)truc.findViewById(R.id.statut)).setText(game.getStatut());
            ((ImageView)truc.findViewById(R.id.logoHome)).setImageBitmap(game.getLogoHome());
            ((ImageView)truc.findViewById(R.id.logoAway)).setImageBitmap(game.getLogoAway());

            if(game.getStatut().equals(Statut.RUNNING) || game.getStatut().equals(Statut.FINISHED) || game.getStatut().equals(Statut.HALF_TIME) )
            {
                ((TextView)truc.findViewById(R.id.score_team1)).setText(game.getHomeScore());
                ((TextView)truc.findViewById(R.id.score_team2)).setText(game.getAwayScore());
            }

            else if(game.getStatut().equals(Statut.POSTPONED) || game.getStatut().equals(Statut.CANCELED) || game.getStatut().equals(Statut.COMING))
            {
                ((TextView)truc.findViewById(R.id.score_team1)).setText("-");
                ((TextView)truc.findViewById(R.id.score_team2)).setText("-");
            }

            layoutFixtures.addView(truc, new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT, 0) );
        }
    }
}
