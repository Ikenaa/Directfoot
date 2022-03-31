package com.example.BackEnd;

/*import static com.example.BackEnd.Game.Statut.COMING;
import static com.example.BackEnd.Game.Statut.FINISHED;
import static com.example.BackEnd.Game.Statut.HALF_TIME;
import static com.example.BackEnd.Game.Statut.POSPONED;
import static com.example.BackEnd.Game.Statut.RUNNING;*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Locale;

import com.example.BackEnd.GameEvent.CardEvent;
import com.example.BackEnd.GameEvent.GameEvent;
import com.example.BackEnd.GameEvent.GoalEvent;
import com.example.BackEnd.JsonModel.FixturesJsonModel;
import com.example.BackEnd.JsonModel.GameEventJsonModel;
import com.example.BackEnd.JsonModel.GameStatJsonModel;
import com.example.BackEnd.JsonModel.LiveGamesJsonModel.*;
import com.example.fragment.MainActivity;
import com.example.fragment.R;
import com.google.gson.Gson;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Declaration of class Game
 */
public class Game extends AsyncTask<Void, Void, Void> {

    private String id;
    private String homeTeamName;
    private String awayTeamName;
    private String homeScore;
    private String awayScore;
    private String hour;
    private String date;
    private String statut;
    private String urlHomeLogo;
    private String urlAwayLogo;
    private String urlStat;
    private String homeShots;
    private String awayShots;
    private String homeShotsOnTarget;
    private String awayShotsOnTarget;
    private String homePossession;
    private String awayPossession;
    private String homePass;
    private String awayPass;
    private String homePassPrecision;
    private String awayPassPrecision;
    private String homeFaults;
    private String awayFaults;
    private String homeYellowCard;
    private String awayYellowCard;
    private String homeRedCard;
    private String awayRedCard;
    private String homeOffSide;
    private String awayOffSide;
    private String championship;
    private ArrayList<GameEvent> gameEvents;
    private Bitmap logoHome,logoAway;
    private View view;
    private MainActivity currentActivity;
    private LayoutInflater inflater;


    /**
     * Constructor of Game
     * @param game
     */
    public Game(Objet game, String championship, MainActivity currentActivity)
    {
        this.currentActivity = currentActivity;
        this.championship = championship;
        id = game.getId();
        //Teams name
        homeTeamName = game.getSpecifics().getDomicile().getEquipe().getNom();
        awayTeamName = game.getSpecifics().getExterieur().getEquipe().getNom();

        //Match Statut
        switch(game.getStatut().getType())
        {
            case "avenir" :
                statut = Statut.COMING;
                break;
            case "termine" :
                statut = Statut.FINISHED;
                break;
            case "encours" :
                statut = Statut.RUNNING;
                break;
            case "mi-temps" :
                statut = Statut.HALF_TIME;
                break;
            case "reporte" :
                statut = Statut.POSTPONED;
                break;
            case "annule" :
                statut = Statut.CANCELED;
                break;
            default :
                statut = null;
                break;
        }

        //Score if game is running, finished or half-time
        if(statut.equals(Statut.RUNNING) || statut.equals(Statut.FINISHED)) {
            homeScore = game.getSpecifics().getScore().getDomicile();
            awayScore = game.getSpecifics().getScore().getExterieur();
            urlStat = game.getStatistics_feed_url();
        }

        //Date
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        hour = sdf.format(game.getDate());
        SimpleDateFormat sdf2 = new SimpleDateFormat("EEEE dd MMMM", new Locale("FR", "fr"));
        date = sdf2.format(game.getDate()).substring(0, 1).toUpperCase() + sdf2.format(game.getDate()).substring(1);

        urlHomeLogo = game.getSpecifics().getDomicile().getEquipe().getUrl_image();
        urlAwayLogo = game.getSpecifics().getExterieur().getEquipe().getUrl_image();

        try {
            InputStream in=new java.net.URL(urlHomeLogo).openStream();
            logoHome= BitmapFactory.decodeStream(in);

            InputStream in2=new java.net.URL(urlAwayLogo).openStream();
            logoAway= BitmapFactory.decodeStream(in2);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public Game(FixturesJsonModel.Event game, String championship, MainActivity currentActivity)
    {
        this.currentActivity = currentActivity;
        this.championship = championship;
        id = game.getLien_web().substring(game.getLien_web().length()-6);
        //Teams name
        homeTeamName = game.getSpecifics().getDomicile().getEquipe().getNom();
        awayTeamName = game.getSpecifics().getExterieur().getEquipe().getNom();

        //Match Statut
        switch(game.getStatut().getType())
        {
            case "avenir" :
                statut = Statut.COMING;
                break;
            case "termine" :
                statut = Statut.FINISHED;
                break;
            case "encours" :
                statut = Statut.RUNNING;
                break;
            case "mi-temps" :
                statut = Statut.HALF_TIME;
                break;
            case "reporte" :
                statut = Statut.POSTPONED;
                break;
            case "annule" :
                statut = Statut.CANCELED;
                break;
            default :
                statut = null;
                break;
        }

        //Score if game is running, finished or half-time
        if(statut.equals(Statut.RUNNING) || statut.equals(Statut.FINISHED)) {
            homeScore = game.getSpecifics().getScore().getDomicile();
            awayScore = game.getSpecifics().getScore().getExterieur();
            urlStat = "https://iphdata.lequipe.fr/iPhoneDatas/EFR/STD/ALL/V2/Football/MatchStats/" +id.substring(id.length() -2)+ "/"+ id + ".json";
        }

        //Date
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        hour = sdf.format(game.getDate());
        SimpleDateFormat sdf2 = new SimpleDateFormat("EEEE dd MMMM", new Locale("FR", "fr"));
        date = sdf2.format(game.getDate()).substring(0, 1).toUpperCase() + sdf2.format(game.getDate()).substring(1);

        urlHomeLogo = game.getSpecifics().getDomicile().getEquipe().getUrl_image();
        urlAwayLogo = game.getSpecifics().getExterieur().getEquipe().getUrl_image();


        try {
            InputStream in=new java.net.URL(urlHomeLogo).openStream();
            logoHome= BitmapFactory.decodeStream(in);

            InputStream in2=new java.net.URL(urlAwayLogo).openStream();
            logoAway= BitmapFactory.decodeStream(in2);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    protected Void doInBackground(Void... voids) {
        String jsonString = null;
        try {
            //read the answer of the api
            URL url = new URL(urlStat);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(url.openStream()));
            jsonString = bufferedReader.readLine();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        //Create an objet with this answer
        GameStatJsonModel.Root jsonObject = new Gson().fromJson(jsonString, GameStatJsonModel.Root.class);
        ArrayList<GameStatJsonModel.Statistic> statistics= jsonObject.getItems().get(1).getObjet().getStatistics();

        for(GameStatJsonModel.Statistic tmp : statistics)
        {
            switch(tmp.getLabel())
            {
                case "possession" :
                    homePossession = tmp.getDomicile().getLabel();
                    awayPossession = tmp.getExterieur().getLabel();
                    break;
                case "passes" :
                    homePass = tmp.getDomicile().getLabel();
                    awayPass = tmp.getExterieur().getLabel();
                    break;
                case "passes réussies" :
                    homePassPrecision = tmp.getDomicile().getLabel();
                    awayPassPrecision = tmp.getExterieur().getLabel();
                    break;
                case "tirs" :
                    homeShots = tmp.getDomicile().getLabel();
                    awayShots = tmp.getExterieur().getLabel();
                    break;
                case "tirs cadrés" :
                    homeShotsOnTarget = tmp.getDomicile().getLabel();
                    awayShotsOnTarget = tmp.getExterieur().getLabel();
                    break;
                case "hors jeux" :
                    homeOffSide = tmp.getDomicile().getLabel();
                    awayOffSide = tmp.getExterieur().getLabel();
                    break;
                case "fautes" :
                    homeFaults = tmp.getDomicile().getLabel();
                    awayFaults = tmp.getExterieur().getLabel();
                    break;
                case "cartons jaunes" :
                    homeYellowCard = tmp.getDomicile().getLabel();
                    awayYellowCard = tmp.getExterieur().getLabel();
                    break;
                case "cartons rouges" :
                    homeRedCard = tmp.getDomicile().getLabel();
                    awayRedCard = tmp.getExterieur().getLabel();
                    break;

            }
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void voids)
    {
        currentActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                ((TextView)view.findViewById(R.id.homeScore)).setText(getHomeScore());
                ((TextView)view.findViewById(R.id.awayScore)).setText(getAwayScore());
                View botLayout = inflater.inflate(R.layout.statistique_match_bot,view.findViewById((R.id.statLayoutGeneral)),false);
                ((TextView)botLayout.findViewById(R.id.homeShots)).setText(getHomeShots());
                ((TextView)botLayout.findViewById(R.id.awayShots)).setText(getAwayShots());
                ((TextView)botLayout.findViewById(R.id.homeOffSide)).setText(getHomeOffSide());
                ((TextView)botLayout.findViewById(R.id.awayOffSide)).setText(getAwayOffSide());
                ((TextView)botLayout.findViewById(R.id.homePass)).setText(getHomePass());
                ((TextView)botLayout.findViewById(R.id.awayPass)).setText(getAwayPass());
                ((TextView)botLayout.findViewById(R.id.homeFault)).setText(getHomeFaults());
                ((TextView)botLayout.findViewById(R.id.awayFault)).setText(getAwayFaults());
                ((TextView)botLayout.findViewById(R.id.homePassPrecision)).setText(getHomePassPrecision());
                ((TextView)botLayout.findViewById(R.id.awayPassPrecision)).setText(getAwayPassPrecision());
                ((TextView)botLayout.findViewById(R.id.homePossession)).setText(getHomePossession());
                ((TextView)botLayout.findViewById(R.id.awayPossession)).setText(getAwayPossession());
                ((TextView)botLayout.findViewById(R.id.homeYellowCard)).setText(getHomeYellowCard());
                ((TextView)botLayout.findViewById(R.id.awayYellowCard)).setText(getAwayYellowCard());
                ((TextView)botLayout.findViewById(R.id.homeShotsOnTarget)).setText(getHomeShotsOnTarget());
                ((TextView)botLayout.findViewById(R.id.awayShotsOnTarget)).setText(getAwayShotsOnTarget());

                if(getHomeRedCard() == null)
                    ((TextView)botLayout.findViewById(R.id.homeRedCard)).setText("0");
                else
                    ((TextView)botLayout.findViewById(R.id.homeRedCard)).setText(getHomeRedCard());
                if(getAwayRedCard() == null)
                    ((TextView)botLayout.findViewById(R.id.awayRedCard)).setText("0");
                else
                    ((TextView)botLayout.findViewById(R.id.awayRedCard)).setText(getAwayRedCard());

                LinearLayout generalLayout = view.findViewById(R.id.statLayoutGeneral);
                generalLayout.addView(botLayout);
            }
        });


    }
    public void generateStat() throws IOException {

    }

    public void generateEvent() throws IOException {
        String urlEvent = getUrlStat().replace("V2", "V5").replace("MatchStats", "Matchs");
        //read the answer of the api
        URL url = new URL(urlEvent);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(url.openStream()));
        String jsonString = bufferedReader.readLine();

        //Create an objet with this answer
        GameEventJsonModel.Root jsonObject = new Gson().fromJson(jsonString, GameEventJsonModel.Root.class);

        gameEvents = new ArrayList<>();

        for(GameEventJsonModel.Carton tmp : jsonObject.getSpecifics().getDomicile().getCartons())
        {
            gameEvents.add(new CardEvent(tmp, GameEvent.Side.HOME));
        }

        for(GameEventJsonModel.Carton tmp : jsonObject.getSpecifics().getExterieur().getCartons())
        {
            gameEvents.add(new CardEvent(tmp, GameEvent.Side.AWAY));
        }

        for(GameEventJsonModel.But tmp : jsonObject.getSpecifics().getDomicile().getButs())
        {
            gameEvents.add(new GoalEvent(tmp, GameEvent.Side.HOME));
        }

        for(GameEventJsonModel.But tmp : jsonObject.getSpecifics().getExterieur().getButs())
        {
            gameEvents.add(new GoalEvent(tmp, GameEvent.Side.AWAY));
        }

        System.out.println("Match " + homeTeamName + " - " + awayTeamName);
        System.out.println("Evenements :");
        Collections.sort(gameEvents);
        for(GameEvent gameEvent : gameEvents)
        {
            System.out.println("Type Event : " + gameEvent.getTypeEvent() + " Minute event : " + gameEvent.getMinute() + " Joueur event : " + gameEvent.getPlayer() + " Coté : " + gameEvent.getSide());
        }
    }


    /**
     *
     * @return homeTeamName
     */
    public String getHomeTeamName() {
        return homeTeamName;
    }

    /**
     * @return awayTeamName
     */
    public String getAwayTeamName() {
        return awayTeamName;
    }

    /**
     *
     * @return homeScore
     */
    public String getHomeScore() {
        return homeScore;
    }

    /**
     *
     * @return awayScore
     */
    public String getAwayScore() {
        return awayScore;
    }

    /**
     *
     * @return date
     */
    public String getHour() {
        return hour;
    }

    /**
     *
     * @return statut
     */
    public String getStatut() {
        return statut;
    }

    /**
     *
     * @return urlHomeLogo
     */
    public String getUrlHomeLogo() {
        return urlHomeLogo;
    }

    /**
     *
     * @return urlAwayLogo
     */
    public String getUrlAwayLogo() {
        return urlAwayLogo;
    }

    public String getUrlStat() {
        return urlStat;
    }

    /**
     *
     * @return date
     */
    public String getDate() {
        return date;
    }

    public Bitmap getLogoHome() {
        return logoHome;
    }

    public Bitmap getLogoAway() {
        return logoAway;
    }

    public String getChampionship() {
        return championship;
    }


    public String getHomeShots() {
        return homeShots;
    }

    public String getAwayShots() {
        return awayShots;
    }

    public String getHomeShotsOnTarget() {
        return homeShotsOnTarget;
    }

    public String getAwayShotsOnTarget() {
        return awayShotsOnTarget;
    }

    public String getHomePossession() {
        return homePossession;
    }

    public String getAwayPossession() {
        return awayPossession;
    }

    public String getHomePass() {
        return homePass;
    }

    public String getAwayPass() {
        return awayPass;
    }

    public String getHomePassPrecision() {
        return homePassPrecision;
    }

    public String getAwayPassPrecision() {
        return awayPassPrecision;
    }

    public String getHomeFaults() {
        return homeFaults;
    }

    public String getAwayFaults() {
        return awayFaults;
    }

    public String getHomeYellowCard() {
        return homeYellowCard;
    }

    public String getAwayYellowCard() {
        return awayYellowCard;
    }

    public String getHomeRedCard() {
        return homeRedCard;
    }

    public String getAwayRedCard() {
        return awayRedCard;
    }

    public String getHomeOffSide() {
        return homeOffSide;
    }

    public String getAwayOffSide() {
        return awayOffSide;
    }

    public void setView(View view) {
        this.view = view;
    }

    public void setInflater(LayoutInflater inflater)
    {
        this.inflater = inflater;
    }
}

