package com.example.BackEnd.JsonModel;

import java.util.ArrayList;

public class TableJsonModel {
    public class Item{
        private int nombre_points;
        private int buts_contre;
        private int rang;
        private int difference_de_buts;
        private Equipe equipe;
        private int nombre_de_victoires;
        private int nombre_de_defaites;
        private int nombre_de_matchs;
        private int buts_pour;
        private int nombre_de_nuls;
        private Objet objet;

        public Equipe getEquipe() {
            return equipe;
        }

        public Objet getObjet() {
            return objet;
        }

        public int getNombre_points() {
            return nombre_points;
        }

        public int getButs_contre() {
            return buts_contre;
        }

        public int getRang() {
            return rang;
        }

        public int getDifference_de_buts() {
            return difference_de_buts;
        }

        public int getNombre_de_victoires() {
            return nombre_de_victoires;
        }

        public int getNombre_de_defaites() {
            return nombre_de_defaites;
        }

        public int getNombre_de_matchs() {
            return nombre_de_matchs;
        }

        public int getButs_pour() {
            return buts_pour;
        }

        public int getNombre_de_nuls() {
            return nombre_de_nuls;
        }
    }

    public class Equipe{
        private String nom;
        private String url_image;

        public String getUrl_image() {
            return url_image;
        }

        public String getNom() {
            return nom;
        }

    }

    public class Objet{
        private ArrayList<Item> items;

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
