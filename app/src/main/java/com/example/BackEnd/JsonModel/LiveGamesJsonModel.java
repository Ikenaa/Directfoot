package com.example.BackEnd.JsonModel;

import java.util.ArrayList;
import java.util.Date;

/**
 * Class which represents an answer in Json in our API
 */
public class LiveGamesJsonModel {

    /**
     * Déclaration of th class ObjetLiveGamesJson
     */
    public class Objet {

        private String id;
        private String __type;
        private Statut statut;
        private Date date;
        private String statistics_feed_url;
        private Competition competition;
        private Sport sport;
        private Specifics specifics;
        private ArrayList<Item> items;

        public String getId() {
            return id;
        }


        /**
         * @return statut
         */
        public Statut getStatut() {
            return statut;
        }

        /**
         * @return date
         */
        public Date getDate() {
            return date;
        }

        /**
         *
         * @return statistics_feed_url
         */
        public String getStatistics_feed_url() {
            return statistics_feed_url;
        }

        /**
         *
         * @return competition
         */
        public Competition getCompetition() {
            return competition;
        }

        /**
         *
         * @return sport
         */
        public Sport getSport() {
            return sport;
        }

        /**
         *
         * @return specifics
         */
        public Specifics getSpecifics() {
            return specifics;
        }

        /**
         *
         * @return items
         */
        public ArrayList<Item> getItems() {
            return items;
        }

        /**
         *
         * @return __type
         */
        public String get__type() {
            return __type;
        }


    }

    /**
     * Declaration of class Statut
     */
    public class Statut{

        private String type;

        /**
         *
         * @return type
         */
        public String getType() {
            return type;
        }
    }


    /**
     * Decalration of class Competition
     */
    public class Competition{
        private String libelle;

        /**
         *
         * @return libelle
         */
        public String getLibelle() {
            return libelle;
        }
    }

    /**
     * Decalration of class Sport
     */
    public class Sport{
        private String nom;

        /**
         *
         * @return nom
         */
        public String getNom() {
            return nom;
        }
    }

    /**
     * Declaration of class Equipe
     */
    public class Equipe{
        private String nom;
        private String url_image;

        /**
         *
         * @return nom
         */
        public String getNom() {
            return nom;
        }

        /**
         *
         * @return url_image
         */
        public String getUrl_image() {
            return url_image;
        }
    }

    /**
     * Declaration of class Exterieur
     */
    public class Exterieur{
        private Equipe equipe;

        /**
         *
         * @return equipe
         */
        public Equipe getEquipe() {
            return equipe;
        }
    }

    /**
     * Declaration of class Domicile
     */
    public class Domicile{

        private Equipe equipe;

        /**
         *
         * @return equipe
         */
        public Equipe getEquipe() {
            return equipe;
        }
    }

    /**
     * Declaration of class Score
     */
    public class Score{
        private String domicile;
        private String exterieur;

        /**
         *
         * @return domicile
         */
        public String getDomicile() {
            return domicile;
        }

        /**
         *
         * @return exterieur
         */
        public String getExterieur() {
            return exterieur;
        }

    }

    /**
     * Declaration of class Specifics
     */
    public class Specifics{
        private Exterieur exterieur;
        private Domicile domicile;
        private Score score;

        /**
         *
         * @return exterieur
         */
        public Exterieur getExterieur() {
            return exterieur;
        }

        /**
         *
         * @return domicile
         */
        public Domicile getDomicile() {
            return domicile;
        }

        /**
         *
         * @return score
         */
        public Score getScore() {
            return score;
        }

    }

    /**
     * Declaration of class Item
     */
    public class Item{
        private Objet objet;

        /**
         *
         * @return objet
         */
        public Objet getObjet() {
            return objet;
        }

    }

}
