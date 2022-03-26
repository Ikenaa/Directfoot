package com.example.BackEnd;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.BackEnd.JsonModel.TableJsonModel;

import java.io.InputStream;

public class Team {

    private String teamName;
    private int rank;
    private int points;
    private int goalsFor;
    private int goalsAgainst;
    private int winNumber;
    private int loseNumber;
    private int drawNumber;
    private int playedNumber;
    private int goalDifference;
    private String urlLogo;
    private Bitmap logo;

    public Team(TableJsonModel.Item item)
    {
        teamName = item.getEquipe().getNom();
        rank = item.getRang();
        points = item.getNombre_points();
        goalsFor = item.getButs_pour();
        goalsAgainst = item.getButs_contre();
        winNumber = item.getNombre_de_victoires();
        loseNumber = item.getNombre_de_defaites();
        drawNumber = item.getNombre_de_nuls();
        playedNumber = item.getNombre_de_matchs();
        goalDifference = item.getDifference_de_buts();
        urlLogo = item.getEquipe().getUrl_image();

        try {
            InputStream in=new java.net.URL(urlLogo).openStream();
            logo= BitmapFactory.decodeStream(in);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int getRank() {
        return rank;
    }

    public int getPoints() {
        return points;
    }

    public int getGoalsFor() {
        return goalsFor;
    }

    public int getGoalsAgainst() {
        return goalsAgainst;
    }

    public int getWinNumber() {
        return winNumber;
    }

    public int getLoseNumber() {
        return loseNumber;
    }

    public int getDrawNumber() {
        return drawNumber;
    }

    public int getPlayedNumber() {
        return playedNumber;
    }

    public String getTeamName() {
        return teamName;
    }

    public int getGoalDifference() {
        return goalDifference;
    }

    public String getUrlLogo() {
        return urlLogo;
    }

    public Bitmap getLogo() {
        return logo;
    }
}
