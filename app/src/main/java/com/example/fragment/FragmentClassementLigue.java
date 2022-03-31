package com.example.fragment;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class FragmentClassementLigue extends Fragment {

    Button ligue1,bundesliga,serieA,premLeague,liga;
    private AppCompatActivity currentActivity;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ligue1.findViewById(R.id.ligue_1_button_logo);
        bundesliga.findViewById(R.id.bundesliga_button_logo);
        serieA.findViewById(R.id.serie_a_button_logo);
        premLeague.findViewById(R.id.premier_league_button_logo);
        liga.findViewById(R.id.liga_button_logo);

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.list_ligue_v2, container, false);
    }

    public void setFragmentActivity(AppCompatActivity activity){
        this.currentActivity = activity;
    }

}