package com.example.fragment;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

public class FragmentAccueil extends Fragment {

    View view;
    TextView team;
    private AppCompatActivity currentActivity;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

   @Override
   public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment

       TextView txtLigue = new TextView((currentActivity));
       txtLigue.setText("Ligue 1");






        view = inflater.inflate(R.layout.fragment_accueil, container,false);
        LinearLayout competFav = view.findViewById(R.id.compet);
        for(Integer i=0;i<50;i++){
            View truc =  inflater.inflate(R.layout.match_component,view.findViewById((R.id.compet)),false);
            String t = i.toString();
            ((TextView)truc.findViewById(R.id.team1)).setText(t);

            competFav.addView(truc, new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT, 0) );



        }

        return view;
    }

    public void setFragmentActivity(AppCompatActivity activity){
        this.currentActivity = activity;
    }
}