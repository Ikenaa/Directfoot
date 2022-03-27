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
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;

import com.example.BackEnd.GameEvent.CardEvent;
import com.example.BackEnd.GameEvent.GameEvent;
import com.example.BackEnd.GameEvent.GoalEvent;
import com.example.BackEnd.JsonModel.FixturesJsonModel;
import com.example.BackEnd.JsonModel.GameEventJsonModel;
import com.example.BackEnd.JsonModel.GameStatJsonModel;
import com.example.BackEnd.JsonModel.LiveGamesJsonModel.*;
import com.google.gson.Gson;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
/**
 * Declaration of class Game
 */
public class Game {

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
    private ArrayList<GameEvent> gameEvents;
    private Bitmap logoHome,logoAway;

    /**
     * Declaration of enum Statut
     */


    /**
     * Constructor of Game
     * @param game
     */
    public Game(Objet game)
    {

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

    public Game(FixturesJsonModel.Event game)
    {
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
            urlStat = "https://iphdata.lequipe.fr/iPhoneDatas/EFR/STD/ALL/V2/Football/MatchStats/09/" + id + ".json;";
        }

        //Date
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        hour = sdf.format(game.getDate());
        SimpleDateFormat sdf2 = new SimpleDateFormat("EEEE dd MMMM");
        date = sdf2.format(game.getDate()).substring(0, 1).toUpperCase() + sdf2.format(game.getDate()).substring(1);;

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

    public void generateStat() throws IOException {
        //read the answer of the api
        URL url = new URL(urlStat);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(url.openStream()));
        String jsonString = bufferedReader.readLine();

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

}

