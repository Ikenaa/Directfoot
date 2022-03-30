package com.example.BackEnd;

import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.BackEnd.JsonModel.CurrentDayJsonModel;
import com.example.BackEnd.JsonModel.FixturesJsonModel;
import com.example.fragment.FragmentFixtures;
import com.example.fragment.FragmentMatch;
import com.example.fragment.MainActivity;
import com.example.fragment.R;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class Fixtures extends AsyncTask<Void, Void, Void> {

    private ArrayList<Game> fixturesGame;
    private Integer nbFixtures;
    private String day;
    private View progressBar;
    private String currentDay;
    private boolean refreshSpinner;
    private FragmentFixtures fragmentFixtures;

    public Fixtures(FragmentFixtures fragmentFixtures, String day, boolean refreshSpinner) throws IOException {
        this.fragmentFixtures = fragmentFixtures;
        this.day = day;
        fixturesGame = new ArrayList<>();
        this.refreshSpinner = refreshSpinner;

    }

    @Override
    protected Void doInBackground(Void... voids) {

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT
        );

        params.setMargins(0, 500, 0, 0);

        progressBar = fragmentFixtures.getInflater().inflate(R.layout.loading,fragmentFixtures.getViewGeneral().findViewById((R.id.layoutGeneral)),false);

        fragmentFixtures.getCurrentActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                fragmentFixtures.getLayoutGeneral().removeAllViews();
                fragmentFixtures.getLayoutGeneral().addView(progressBar, params);
            }
        });

            try {

                String urlString2 = "https://iphdata.lequipe.fr/iPhoneDatas/EFR/STD/ALL/V1/Football/CalendarList/CompetitionPhase/" + fragmentFixtures.getChampionship() + "/current/container.json";
                URL url2 = new URL(urlString2);
                BufferedReader bufferedReader2 = new BufferedReader(new InputStreamReader(url2.openStream()));
                String jsonString2 = bufferedReader2.readLine();

                CurrentDayJsonModel.Root jsonObject2 = new Gson().fromJson(jsonString2, CurrentDayJsonModel.Root.class);

                nbFixtures = 1;
                for (CurrentDayJsonModel.SubNav subNav : jsonObject2.getFeeds().get(0).getSub_nav()) {
                    if (subNav.isCurrent())
                        currentDay = nbFixtures.toString();

                    nbFixtures += 1;
                }

                String urlString;
                if(day == null) {
                    day = currentDay;
                }

                if (day.equals("1")) {
                    day += "r";
                }
                urlString = "https://iphdata.lequipe.fr/iPhoneDatas/EFR/STD/ALL/V1/Football/CalendarList/CompetitionPhase/" + fragmentFixtures.getChampionship() + "/current/" + day +"e-journee.json";


                URL url = new URL(urlString);
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(url.openStream()));
                String jsonString = bufferedReader.readLine();

                FixturesJsonModel.Root jsonObject = new Gson().fromJson(jsonString, FixturesJsonModel.Root.class);

                for (FixturesJsonModel.Item item : jsonObject.getItems())
                {
                    if(item.get__type().equals("calendar_widget_list"))
                    {
                        for (FixturesJsonModel.Item itemBis : item.getItems()) {
                            fixturesGame.add(new Game(itemBis.getEvent(), fragmentFixtures.getChampionship(), (MainActivity) fragmentFixtures.getCurrentActivity()));
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

        fragmentFixtures.getCurrentActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                fragmentFixtures.getLayoutGeneral().removeView(progressBar);

                if(refreshSpinner) {



                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(fragmentFixtures.getCurrentActivity(), R.layout.spinner_item);

                    for (Integer i = 1; i < nbFixtures; i++) {
                        adapter.add(i + "e journée");
                    }
                    fragmentFixtures.getSpinner().setAdapter(adapter);
                    fragmentFixtures.getSpinner().setSelection(Integer.parseInt(day) - 1);
                    fragmentFixtures.getSpinner().setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            // Get the spinner selected item text
                            String day = (String) adapterView.getItemAtPosition(i);
                            if(Character.isDigit(day.charAt(1)))
                            {
                                day = day.substring(0, 2);
                            }
                            else
                                day = day.substring(0, 1);

                            try {
                                new Fixtures(fragmentFixtures, day,false).execute();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {

                        }
                    });
                }
                else
                {
                    String date = "null";
                    for(Game game : fixturesGame)
                    {
                        if(!date.equals(game.getDate())) {
                            View titre_ligue = fragmentFixtures.getInflater().inflate(R.layout.name_champ, fragmentFixtures.getViewGeneral().findViewById((R.id.compet)), false);
                            ((TextView) titre_ligue.findViewById(R.id.name_Ligue)).setText(game.getDate());
                            fragmentFixtures.getLayoutGeneral().addView(titre_ligue);
                            date = game.getDate();
                        }
                        View truc =  fragmentFixtures.getInflater().inflate(R.layout.match_component,fragmentFixtures.getViewGeneral().findViewById((R.id.compet)),false);
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

                        truc.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                ((MainActivity)fragmentFixtures.getCurrentActivity()).replaceFragment(new FragmentMatch((MainActivity) fragmentFixtures.getCurrentActivity(), game));
                            }
                        });

                        fragmentFixtures.getLayoutGeneral().addView(truc, new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT, 0) );
                    }
                }

            }
        });

    }
}
