package com.example.fragment;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import com.example.BackEnd.Fixtures;
import com.example.BackEnd.Table;
import java.io.IOException;


public class FragmentFixtures extends Fragment {



    private View viewGeneral;
    private AppCompatActivity currentActivity;
    private String championship;
    private LayoutInflater inflater;
    private LinearLayout layoutGeneral;
    private Spinner spinner;

    public FragmentFixtures(AppCompatActivity currentActivity, String championship)
    {
        this.championship = championship;
        this.currentActivity = currentActivity;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        this.inflater = inflater;
        viewGeneral = inflater.inflate(R.layout.fragment_fixtures, container, false);
        layoutGeneral = viewGeneral.findViewById(R.id.layoutGeneral);
        Button tableButton = viewGeneral.findViewById(R.id.tableButton);
        Button fixturesButton = viewGeneral.findViewById(R.id.fixturesButton);
        View viewTable = inflater.inflate(R.layout.fragment_classement_ligue, layoutGeneral, false);

        View spinnerView = inflater.inflate(R.layout.spinner, viewGeneral.findViewById(R.id.underButtons), false );
        LinearLayout underButton = viewGeneral.findViewById(R.id.underButtons);
        spinner = spinnerView.findViewById(R.id.spinner);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        layoutParams.setMargins(0, 30, 0, 30);
        currentActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                underButton.addView(spinnerView, layoutParams);
            }
        });


        tableButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Table table = new Table(championship, inflater, viewGeneral, (MainActivity) currentActivity, layoutGeneral, viewTable, underButton, spinnerView);
                    table.execute();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        fixturesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity)currentActivity).replaceFragment(new FragmentFixtures(currentActivity, championship));
            }
        });

        try {
            Fixtures fixtures = new Fixtures(this, null, true);
            fixtures.execute();
        } catch (IOException e) {
            e.printStackTrace();
        }


        return viewGeneral;
    }

    public String getChampionship() {
        return championship;
    }

    public View getViewGeneral() {
        return viewGeneral;
    }

    public AppCompatActivity getCurrentActivity() {
        return currentActivity;
    }


    public LayoutInflater getInflater() {
        return inflater;
    }

    public LinearLayout getLayoutGeneral() {
        return layoutGeneral;
    }

    public Spinner getSpinner() {
        return spinner;
    }




    public void setFragmentActivity(AppCompatActivity activity){
        this.currentActivity = activity;
    }
}