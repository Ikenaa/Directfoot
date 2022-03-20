package com.example.BackEnd;

import com.example.BackEnd.JsonModel.TableJsonModel;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.*;

public class Table extends ArrayList<Team> {

    public Table(String championship) throws Exception {
        String urlString = "https://iphdata.lequipe.fr/iPhoneDatas/EFR/STD/ALL/V1/Football/ClassementsBase/current/" + championship + "/general.json";

        //read the answer of the api
        URL url = new URL(urlString);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(url.openStream()));
        String jsonString = bufferedReader.readLine();

        //Create an objet with this answer
        TableJsonModel.Root jsonObject = new Gson().fromJson(jsonString, TableJsonModel.Root.class);

        for(TableJsonModel.Item tmp : jsonObject.getItems().get(1).getObjet().getItems())
        {
            add(new Team(tmp));
        }
    }
}
