package com.example.BackEnd.JsonModel;

import java.util.ArrayList;

public class GameStatJsonModel {


    public class Domicile{

        private String label;

        public String getLabel() {
            return label;
        }

    }

    public class Exterieur{

        private String label;

        public String getLabel() {
            return label;
        }

    }

    public class Statistic{

        private Domicile domicile;
        private Exterieur exterieur;
        private String label;

        public Domicile getDomicile() {
            return domicile;
        }

        public Exterieur getExterieur() {
            return exterieur;
        }

        public String getLabel() {
            return label;
        }
    }

    public class Objet{

        private ArrayList<Statistic> statistics;

        public ArrayList<Statistic> getStatistics() {
            return statistics;
        }

    }
    public class Item{

        private Objet objet;

        public Objet getObjet() {
            return objet;
        }

    }

    public class Root{

        private ArrayList<Item> items;

        public ArrayList<Item> getItems() {
            return items;
        }

    }

}
