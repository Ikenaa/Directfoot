package com.example.fragment;

import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.BackEnd.Championship;
import com.example.BackEnd.Game;
import com.example.BackEnd.LiveGames;

import java.io.IOException;
import java.util.ArrayList;

public class FragmentAccueil extends Fragment {

    View view;
    TextView team;
    private AppCompatActivity currentActivity;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

   @RequiresApi(api = Build.VERSION_CODES.O)
   @Override
   public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment


       view = inflater.inflate(R.layout.fragment_accueil, container,false);
       LinearLayout competFav = view.findViewById(R.id.compet);
       try {
          LiveGames lg =  new LiveGames(view, inflater, competFav, currentActivity);
          lg.execute();
       } catch (IOException e) {
           e.printStackTrace();
       }


        return view;
    }

    public void setFragmentActivity(AppCompatActivity activity){
        this.currentActivity = activity;
    }
}