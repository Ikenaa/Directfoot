package com.example.BackEnd;

import com.example.BackEnd.JsonModel.FixturesJsonModel;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;

public class Fixtures extends ArrayList<Game> {

    public Fixtures(String day, String championship) throws IOException {

        if(day.equals("1"))
        {
            day+= "r";
        }

        String urlString = "https://iphdata.lequipe.fr/iPhoneDatas/EFR/STD/ALL/V1/Football/CalendarList/CompetitionPhase/" + championship + "/current/" + day +"e-journee.json";
        URL url = new URL(urlString);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(url.openStream()));
        String jsonString = bufferedReader.readLine();

        FixturesJsonModel.Root jsonObject = new Gson().fromJson(jsonString, FixturesJsonModel.Root.class);

        for (FixturesJsonModel.Item item : jsonObject.getItems())
        {
            if(item.get__type().equals("calendar_widget_list"))
            {
                for (FixturesJsonModel.Item itemBis : item.getItems()) {
                    add(new Game(itemBis.getEvent()));
                }
            }
        }
    }
}
