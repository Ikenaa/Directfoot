package com.example.fragment;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.BackEnd.Championship;

public class FragmentHome extends Fragment {

    private View view;
    private ImageButton ligue1,bundesliga,serieA,premLeague,liga;
    private TextView ligue1Text, bundesligaText, serieAtext, premLeagueText, ligaText;
    private MainActivity mainActivity;

    public FragmentHome(MainActivity mainActivity)
    {
        super();
        this.mainActivity = mainActivity;

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);




    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment


        view = inflater.inflate(R.layout.list_ligue_v2, container,false);
        ligue1 = view.findViewById(R.id.ligue_1_button_logo);
        bundesliga = view.findViewById(R.id.bundesliga_button_logo);
        serieA = view.findViewById(R.id.serie_a_button_logo);
        premLeague = view.findViewById(R.id.premier_league_button_logo);
        liga = view.findViewById(R.id.liga_button_logo);

        ligue1Text = view.findViewById(R.id.ligue_1_button_text);
        bundesligaText = view.findViewById(R.id.bundesliga_button_text);
        serieAtext = view.findViewById(R.id.serie_a_button_text);
        premLeagueText = view.findViewById(R.id.premier_league_button_text);
        ligaText = view.findViewById(R.id.liga_button_text);

        ligue1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainActivity.replaceFragment(new FragmentFixtures(mainActivity, Championship.LIGUE_1));
            }
        });

        bundesliga.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainActivity.replaceFragment(new FragmentFixtures(mainActivity, Championship.BUNDESLIGA));
            }
        });

        premLeague.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainActivity.replaceFragment(new FragmentFixtures(mainActivity, Championship.PREMIER_LEAGUE));
            }
        });

        liga.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainActivity.replaceFragment(new FragmentFixtures(mainActivity, Championship.LIGA));
            }
        });

        serieA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainActivity.replaceFragment(new FragmentFixtures(mainActivity, Championship.SERIE_A)) ;
            }
        });

        ligue1Text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainActivity.replaceFragment(new FragmentFixtures(mainActivity, Championship.LIGUE_1));
            }
        });

        bundesligaText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainActivity.replaceFragment(new FragmentFixtures(mainActivity, Championship.BUNDESLIGA));
            }
        });

        premLeagueText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainActivity.replaceFragment(new FragmentFixtures(mainActivity, Championship.PREMIER_LEAGUE));
            }
        });

        ligaText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainActivity.replaceFragment(new FragmentFixtures(mainActivity, Championship.LIGA));
            }
        });

        serieAtext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainActivity.replaceFragment(new FragmentFixtures(mainActivity, Championship.SERIE_A)) ;
            }
        });

        return view;
    }
}