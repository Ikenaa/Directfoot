package com.example.BackEnd.JsonModel;

import java.util.ArrayList;

public class TableJsonModel {
    public class Item{
        public int nombre_points;
        public int buts_contre;
        public int rang;
        public int difference_de_buts;

        public Equipe getEquipe() {
            return equipe;
        }

        public Equipe equipe;
        public int nombre_de_victoires;
        public int nombre_de_defaites;
        public int nombre_de_matchs;
        public int buts_pour;
        public int nombre_de_nuls;
        public Objet objet;

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
        public String nom;
        public String url_image;

        public String getUrl_image() {
            return url_image;
        }

        public String getNom() {
            return nom;
        }

    }

    public class Objet{
        public ArrayList<Item> items;

        public ArrayList<Item> getItems() {
            return items;
        }
    }

    public class Root{
        public ArrayList<Item> items;

        public ArrayList<Item> getItems() {
            return items;
        }
    }
}
