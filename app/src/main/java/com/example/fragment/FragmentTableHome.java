package com.example.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.BackEnd.Championship;

public class FragmentTableHome extends Fragment {

    private View view;
    private TextView team;
    private AppCompatActivity currentActivity;
    private ImageButton ligue1,bundesliga,serieA,premLeague,liga;
    private MainActivity mainActivity;

    public FragmentTableHome(MainActivity mainActivity)
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


        view = inflater.inflate(R.layout.list_ligue, container,false);
        ligue1 = view.findViewById(R.id.ligue_1);
        bundesliga = view.findViewById(R.id.bundesliga);
        serieA = view.findViewById(R.id.serie_A);
        premLeague = view.findViewById(R.id.premier_league);
        liga = view.findViewById(R.id.liga);

        ligue1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainActivity.replaceFragment(new FragmentTable(currentActivity, Championship.LIGUE_1));
            }
        });

        bundesliga.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainActivity.replaceFragment(new FragmentTable(currentActivity, Championship.BUNDESLIGA));
            }
        });

        premLeague.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainActivity.replaceFragment(new FragmentTable(currentActivity, Championship.PREMIER_LEAGUE));
            }
        });

        liga.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainActivity.replaceFragment(new FragmentTable(currentActivity, Championship.LIGA));
            }
        });

        serieA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainActivity.replaceFragment(new FragmentTable(currentActivity, Championship.SERIE_A)) ;
            }
        });

        return view;
    }

    public void setFragmentActivity(AppCompatActivity activity){
        this.currentActivity = activity;
    }
}
