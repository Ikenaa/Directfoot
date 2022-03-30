package com.example.fragment;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Spinner;

import com.example.BackEnd.Championship;
import com.example.BackEnd.Fixtures;

import java.io.IOException;


public class FragmentFixtures extends Fragment {

    View view;
    private AppCompatActivity currentActivity;
    private String championship;

    public FragmentFixtures(AppCompatActivity currentActivity, String championship)
    {
        this.championship = championship;
        this.currentActivity = currentActivity;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_fixtures, container, false);
        LinearLayout layoutFixtures = view.findViewById(R.id.layoutFixtures);
        Spinner spinner = view.findViewById(R.id.spinner);
        try {
            Fixtures fixtures = new Fixtures(championship, view, currentActivity, inflater, layoutFixtures, null, true, spinner);
            fixtures.execute();
        } catch (IOException e) {
            e.printStackTrace();
        }


        return view;
    }

    public void setFragmentActivity(AppCompatActivity activity){
        this.currentActivity = activity;
    }
}