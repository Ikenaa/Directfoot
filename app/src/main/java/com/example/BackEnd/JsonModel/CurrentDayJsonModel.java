package com.example.BackEnd.JsonModel;


import java.util.ArrayList;

public class CurrentDayJsonModel {

    public class SubNav{
        public String __type;
        public String url_web;
        public String url;
        public String feed_type;
        public String title;

        public String getId() {
            return id;
        }

        public String id;

        public boolean isCurrent() {
            return is_current;
        }

        public boolean is_current;
    }

    public class Feed{
        public String __type;
        public boolean selected;

        public ArrayList<SubNav> getSub_nav() {
            return sub_nav;
        }

        public ArrayList<SubNav> sub_nav;
        public String titre;
        public String type;
        public String url_web;
    }

    public class Root{
        public String __type;

        public ArrayList<Feed> getFeeds() {
            return feeds;
        }

        public ArrayList<Feed> feeds;
    }
}
