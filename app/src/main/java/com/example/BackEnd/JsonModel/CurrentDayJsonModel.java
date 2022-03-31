package com.example.BackEnd.JsonModel;


import java.util.ArrayList;

public class CurrentDayJsonModel {

    public class SubNav{
        private boolean is_current;
        private String id;

        public String getId() {
            return id;
        }
        public boolean isCurrent() {
            return is_current;
        }
    }

    public class Feed{
        private ArrayList<SubNav> sub_nav;
        public ArrayList<SubNav> getSub_nav() {
            return sub_nav;
        }

    }

    public class Root{

        private ArrayList<Feed> feeds;
        public ArrayList<Feed> getFeeds() {
            return feeds;
        }
    }
}
