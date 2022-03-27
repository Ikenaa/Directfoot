package com.example.BackEnd;

import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.BackEnd.JsonModel.LiveGamesJsonModel;
import com.example.fragment.FragmentDirect;
import com.example.fragment.FragmentFixtures;
import com.example.fragment.FragmentMatch;
import com.example.fragment.FragmentRecherche;
import com.example.fragment.MainActivity;
import com.example.fragment.R;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import com.example.BackEnd.JsonModel.LiveGamesJsonModel.*;

import org.w3c.dom.Text;

/**
 * Declaration of class LiveGames
 * This class creates an object that contains all games by the 5 main championships
 */
public class LiveGames extends AsyncTask<Void, Void, Void> {


    private ArrayList<Item> sportsList;
    private int indexFootball;
    private int indexFranceChampionship;
    private int indexSpainChampionship;
    private int indexItalyChampionship;
    private int indexGermanyChampionship;
    private int indexEnglandChampionship;
    private LiveGamesJsonModel.Objet jsonObject;
    private View view;
    private LayoutInflater layoutInflater;
    private LinearLayout competFav;
    private View progressBar;


    private ArrayList<Game> franceGames;
    private ArrayList<Game> italyGames;
    private ArrayList<Game> germanyGames;
    private ArrayList<Game> englandGames;
    private ArrayList<Game> spainGames;
    private MainActivity currentActivity;


    /**
     * Constructor of LiveGames object
     * Create an LiveGamesJsonModel with the url of the api
     *
     * @throws IOException
     */
    public LiveGames(View view, LayoutInflater layoutInflater, LinearLayout competFav, MainActivity currentActivity, View progressBar) throws IOException {

        this.progressBar = progressBar;
        this.view = view;
        this.competFav = competFav;
        this.layoutInflater = layoutInflater;
        this.currentActivity = currentActivity;



    }


    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected Void doInBackground(Void... voids) {

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT
        );

        params.setMargins(0, 800, 0, 0);


        competFav.addView(progressBar, params);

        //we need the todays' date in a particular format in the url of the api
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMdd");
        LocalDateTime today = LocalDateTime.now();

        //String urlString = "https://iphdata.lequipe.fr/iPhoneDatas/EFR/STD/ALL/V3/Lives/" + dtf.format(today) + ".json";
        String urlString = "https://iphdata.lequipe.fr/iPhoneDatas/EFR/STD/ALL/V3/Lives/20220320.json";






        try {
            //read the answer of the api
            URL url = new URL(urlString);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(url.openStream()));

            String jsonString = bufferedReader.readLine();
            jsonObject = new Gson().fromJson(jsonString, LiveGamesJsonModel.Objet.class);

            if(isAFootballDay())
            {
                if(isAFranceChampionshipDay())
                {
                    franceGames = getEachMatchByChampionship(Championship.LIGUE_1);
                }

                if(isASpainChampionshipDay())
                {
                    spainGames = getEachMatchByChampionship(Championship.LIGA);
                }

                if(isAItalyChampionshipDay())
                {
                    italyGames = getEachMatchByChampionship(Championship.SERIE_A);
                }

                if(isAGermanyChampionshipDay())
                {
                    germanyGames = getEachMatchByChampionship(Championship.BUNDESLIGA);
                }

                if(isAEnglandChampionshipDay())
                {
                    englandGames = getEachMatchByChampionship(Championship.PREMIER_LEAGUE);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        //Create an objet with this answer

        return null;
    }

    @Override
    protected void onPostExecute(Void voids) {

        competFav.removeView(progressBar);
        if(isAFootballDay())
        {
            if(isAFranceChampionshipDay())
            {
               View titre_ligue = layoutInflater.inflate(R.layout.name_champ,view.findViewById((R.id.compet)),false);

               ((TextView) titre_ligue.findViewById(R.id.name_Ligue)).setText("Ligue 1");

               competFav.addView(titre_ligue);

                for(Game game : franceGames)
                {
                    View truc =  layoutInflater.inflate(R.layout.match_component,view.findViewById((R.id.compet)),false);

                    truc.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            // ADD your action here
                            currentActivity.replaceFragment(new FragmentMatch(currentActivity));

                        }
                    });

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

                    else if(game.getStatut().equals(Statut.POSTPONED) || game.getStatut().equals(Statut.CANCELED))
                    {
                        ((TextView)truc.findViewById(R.id.score_team1)).setText("-");
                        ((TextView)truc.findViewById(R.id.score_team2)).setText("-");
                    }

                    competFav.addView(truc, new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT, 0) );
                }
            }
            if(isAEnglandChampionshipDay())
            {

                View titre_ligue = layoutInflater.inflate(R.layout.name_champ,view.findViewById((R.id.compet)),false);

                ((TextView) titre_ligue.findViewById(R.id.name_Ligue)).setText("Premier League");

                competFav.addView(titre_ligue);


                for(Game game : englandGames)
                {
                    View truc =  layoutInflater.inflate(R.layout.match_component,view.findViewById((R.id.compet)),false);

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

                    else if(game.getStatut().equals(Statut.POSTPONED) || game.getStatut().equals(Statut.CANCELED))
                    {
                        ((TextView)truc.findViewById(R.id.score_team1)).setText("-");
                        ((TextView)truc.findViewById(R.id.score_team2)).setText("-");
                    }

                    competFav.addView(truc, new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT, 0) );
                }
            }
            if(isAGermanyChampionshipDay())
            {

                View titre_ligue = layoutInflater.inflate(R.layout.name_champ,view.findViewById((R.id.compet)),false);

                ((TextView) titre_ligue.findViewById(R.id.name_Ligue)).setText("Bundesliga");

                competFav.addView(titre_ligue);

                for(Game game : germanyGames)
                {
                    View truc =  layoutInflater.inflate(R.layout.match_component,view.findViewById((R.id.compet)),false);

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

                    else if(game.getStatut().equals(Statut.POSTPONED) || game.getStatut().equals(Statut.CANCELED))
                    {
                        ((TextView)truc.findViewById(R.id.score_team1)).setText("-");
                        ((TextView)truc.findViewById(R.id.score_team2)).setText("-");
                    }

                    competFav.addView(truc, new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT, 0) );
                }
            }
            if(isAItalyChampionshipDay())
            {

                View titre_ligue = layoutInflater.inflate(R.layout.name_champ,view.findViewById((R.id.compet)),false);

                ((TextView) titre_ligue.findViewById(R.id.name_Ligue)).setText("Serie A");

                competFav.addView(titre_ligue);

                for(Game game : italyGames)
                {
                    View truc =  layoutInflater.inflate(R.layout.match_component,view.findViewById((R.id.compet)),false);

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

                    else if(game.getStatut().equals(Statut.POSTPONED) || game.getStatut().equals(Statut.CANCELED))
                    {
                        ((TextView)truc.findViewById(R.id.score_team1)).setText("-");
                        ((TextView)truc.findViewById(R.id.score_team2)).setText("-");
                    }

                    competFav.addView(truc, new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT, 0) );
                }
            }
            if(isASpainChampionshipDay())
            {

                View titre_ligue = layoutInflater.inflate(R.layout.name_champ,view.findViewById((R.id.compet)),false);

                ((TextView) titre_ligue.findViewById(R.id.name_Ligue)).setText("Liga");

                competFav.addView(titre_ligue);

                for(Game game : spainGames)
                {
                    View truc =  layoutInflater.inflate(R.layout.match_component,view.findViewById((R.id.compet)),false);

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

                    else if(game.getStatut().equals(Statut.POSTPONED) || game.getStatut().equals(Statut.CANCELED))
                    {
                        ((TextView)truc.findViewById(R.id.score_team1)).setText("-");
                        ((TextView)truc.findViewById(R.id.score_team2)).setText("-");
                    }

                    competFav.addView(truc, new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT, 0) );
                }
            }
        }
    }

    /**
     * method wich verifies if today is a football day
     * @return true if there are football games, false if not
     */
    public boolean isAFootballDay()
    {
        sportsList = jsonObject.getItems().get(1).getObjet().getItems();
        int i = 0;
        for(Item tmp : getSportsList())
        {
            if(tmp.getObjet()
                .getItems()
                .get(0)//Championnat
                .getObjet()
                .getItems()
                .get(0)//Match
                .getObjet()
                .getSport() != null) {

                    if(tmp.getObjet()
                        .getItems()
                        .get(0)//Championnat
                        .getObjet()
                        .getItems()
                        .get(0)//Match
                        .getObjet()
                        .getSport()
                        .getNom().equals("Football")) {
                            indexFootball = i;
                            return true;
                    }
            }

            i+=1;
        }
        return false;
    }

    /**
     * Method that verifies is there is Ligue 1 today
     * @return true if there is Ligue 1 today, false if not
     */
    public boolean isAFranceChampionshipDay()
    {
        int i = 0;
        for(Item tmp : getChampionshipList())
        {
            if(tmp.getObjet().get__type().equals("flux"))
            {
                if(tmp.getObjet()
                        .getItems()
                        .get(0)
                        .getObjet()
                        .getCompetition()
                        .getLibelle().equals("Ligue 1"))
                {
                    indexFranceChampionship = i;
                    return true;
                }

            }
            i+=1;
        }
        return false;
    }

    /**
     * Method that verifies is there is Bundesliga today
     * @return true if there is Bundesliga today, false if not
     */
    public boolean isAGermanyChampionshipDay()
    {
        int i = 0;
        for(Item tmp : getChampionshipList())
        {
            if(tmp.getObjet().get__type().equals("flux"))
            {
                if(tmp.getObjet()
                        .getItems()
                        .get(0)
                        .getObjet()
                        .getCompetition()
                        .getLibelle().equals("Championnat d'Allemagne"))
                {
                    indexGermanyChampionship = i;
                    return true;
                }

            }
            i+=1;
        }
        return false;
    }

    /**
     * Method that verifies is there is PremierLeague today
     * @return true if there is PremierLeague today, false if not
     */
    public boolean isAEnglandChampionshipDay()
    {
        int i = 0;
        for(Item tmp : getChampionshipList())
        {
            if(tmp.getObjet().get__type().equals("flux"))
            {
                if(tmp.getObjet()
                        .getItems()
                        .get(0)
                        .getObjet()
                        .getCompetition()
                        .getLibelle().equals("Championnat d'Angleterre"))
                {
                    indexEnglandChampionship = i;
                    return true;
                }

            }
            i+=1;
        }
        return false;
    }

    /**
     * Method that verifies is there is Liga today
     * @return true if there is Liga today, false if not
     */
    public boolean isASpainChampionshipDay()
    {
        int i = 0;
        for(Item tmp : getChampionshipList())
        {
            if(tmp.getObjet().get__type().equals("flux"))
            {
                if(tmp.getObjet()
                        .getItems()
                        .get(0)
                        .getObjet()
                        .getCompetition()
                        .getLibelle().equals("Championnat d'Espagne"))
                {
                    indexSpainChampionship = i;
                    return true;
                }

            }
            i+=1;
        }
        return false;
    }

    /**
     * Method that verifies is there is Serie A today
     * @return true if there is Serie A today, false if not
     */
    public boolean isAItalyChampionshipDay()
    {
        int i = 0;
        for(Item tmp : getChampionshipList())
        {
            if(tmp.getObjet().get__type().equals("flux"))
            {
                if(tmp.getObjet()
                        .getItems()
                        .get(0)
                        .getObjet()
                        .getCompetition()
                        .getLibelle().equals("Championnat d'Italie"))
                {
                    indexItalyChampionship = i;
                    return true;
                }

            }
            i+=1;
        }
        return false;
    }

    /**
     * Method that returns an array with all the games by championchip
     * @param championship index of the will championship
     * @return gameArray
     */
    public ArrayList<Game> getEachMatchByChampionship(String championship)
    {
        int indexChampionship;

        switch(championship)
        {
            case Championship.LIGUE_1 :
                indexChampionship = getIndexFranceChampionship();
                break;
            case Championship.SERIE_A:
                indexChampionship = getIndexItalyChampionship();
                break;
            case Championship.BUNDESLIGA:
                indexChampionship = getIndexGermanyChampionship();
                break;
            case Championship.PREMIER_LEAGUE:
                indexChampionship = getIndexEnglandChampionship();
                break;
            case Championship.LIGA:
                indexChampionship = getIndexSpainChampionship();
                break;
            default :
                indexChampionship = 0;
        }
        ArrayList<Game> gameArray = new ArrayList<>();
        for(Item item : getChampionshipList().get(indexChampionship).getObjet().getItems())
        {
            if(item.getObjet().get__type().equalsIgnoreCase("rencontre_sport_collectif"))
                gameArray.add(new Game(item.getObjet()));
        }
        return gameArray;
    }

    /**
     * Method that returns the list of the sports
     * @return sportList
     */
    private ArrayList<Item> getSportsList() {
        return sportsList;
    }

    /**
     * Method that return the list of the championship
     * @return championship List
     */
    private ArrayList<Item> getChampionshipList()
    {
        return getSportsList().get(indexFootball).getObjet().getItems();
    }

    /**
     *
     * @return indexFranceChampionship
     */
    public int getIndexFranceChampionship() {
        return indexFranceChampionship;
    }

    /**
     *
     * @return indexSpainChampionship
     */
    public int getIndexSpainChampionship() {
        return indexSpainChampionship;
    }

    /**
     *
     * @return indexItalyChampionship
     */
    public int getIndexItalyChampionship() {
        return indexItalyChampionship;
    }

    /**
     *
     * @return indexGermanyChampionship
     */
    public int getIndexGermanyChampionship() {
        return indexGermanyChampionship;
    }

    /**
     *
     * @return indexEnglandChampionship
     */
    public int getIndexEnglandChampionship() {
        return indexEnglandChampionship;
    }


}
