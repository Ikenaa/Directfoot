package com.example.BackEnd.JsonModel;

import java.util.ArrayList;
import java.util.Date;

public class FixturesJsonModel {


    public class Statut{
        private String type;

        public String getType() {
            return type;
        }

    }

    public class Equipe{
        private String url_image;
        private String nom;

        public String getUrl_image() {
            return url_image;
        }

        public String getNom() {
            return nom;
        }
    }

    public class Domicile{
        private Equipe equipe;

        public Equipe getEquipe() {
            return equipe;
        }

    }

    public class Exterieur{

        private Equipe equipe;

        public Equipe getEquipe() {
            return equipe;
        }
    }

    public class Score{

        private String exterieur;
        private String domicile;

        public String getExterieur() {
            return exterieur;
        }

        public String getDomicile() {
            return domicile;
        }

    }

    public class Specifics{

        private Domicile domicile;
        private Exterieur exterieur;
        private Score score;

        public Domicile getDomicile() {
            return domicile;
        }

        public Exterieur getExterieur() {
            return exterieur;
        }

        public Score getScore() {
            return score;
        }


    }

    public class Event{
        private Date date;
        private Specifics specifics;
        private String lien_web;
        private Statut statut;

        public Date getDate() {
            return date;
        }

        public String getLien_web() {
            return lien_web;
        }

        public Statut getStatut() {
            return statut;
        }

        public Specifics getSpecifics() {
            return specifics;
        }


    }

    public class Item{
        private String __type;
        private Event event;
        private ArrayList<Item> items;

        public String get__type() {
            return __type;
        }

        public Event getEvent() {
            return event;
        }

        public ArrayList<Item> getItems() {
            return items;
        }


    }

    public class Root{

        private ArrayList<Item> items;

        public ArrayList<Item> getItems() {
            return items;
        }

    }
}
