package com.example.BackEnd.JsonModel;

import java.util.ArrayList;

public class GameEventJsonModel {


    public class Joueur{
        private String nom_abrege;

        public String getNom_abrege() {
            return nom_abrege;
        }
    }

    public class Instant{
        private String libelle;

        public String getLibelle() {
            return libelle;
        }


    }

    public class Carton{
        private String type;
        private Joueur joueur;
        private Instant instant;

        public String getType() {
            return type;
        }

        public Joueur getJoueur() {
            return joueur;
        }

        public Instant getInstant() {
            return instant;
        }

    }






    public class Exterieur{
        private ArrayList<But> buts;
        private ArrayList<Carton> cartons;

        public ArrayList<But> getButs() {
            return buts;
        }

        public ArrayList<Carton> getCartons() {
            return cartons;
        }

    }

    public class But{
        private Instant instant;
        private Joueur joueur;

        public Instant getInstant() {
            return instant;
        }

        public Joueur getJoueur() {
            return joueur;
        }
    }

    public class Domicile{
        private ArrayList<But> buts;
        private ArrayList<Carton> cartons;

        public ArrayList<But> getButs() {
            return buts;
        }

        public ArrayList<Carton> getCartons() {
            return cartons;
        }

    }

    public class Specifics{
        private Exterieur exterieur;
        private Domicile domicile;

        public Exterieur getExterieur() {
            return exterieur;
        }

        public Domicile getDomicile() {
            return domicile;
        }
    }


    public class Root{
        private Specifics specifics;

        public Specifics getSpecifics() {
            return specifics;
        }

    }
}
