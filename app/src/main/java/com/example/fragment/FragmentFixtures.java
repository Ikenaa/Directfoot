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

    public FragmentFixtures(AppCompatActivity currentActivity, String championship)
    {
        this.championship = championship;
        this.currentActivity = currentActivity;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        viewGeneral = inflater.inflate(R.layout.fragment_fixtures, container, false);
        LinearLayout layoutGeneral = viewGeneral.findViewById(R.id.layoutGeneral);
        Button tableButton = viewGeneral.findViewById(R.id.tableButton);
        Button fixturesButton = viewGeneral.findViewById(R.id.fixturesButton);
        View viewTable = inflater.inflate(R.layout.fragment_classement_ligue, layoutGeneral, false);

        View spinnerView = inflater.inflate(R.layout.spinner, viewGeneral.findViewById(R.id.underButtons), false );
        LinearLayout underButton = viewGeneral.findViewById(R.id.underButtons);
        Spinner spinner = spinnerView.findViewById(R.id.spinner);
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
            Fixtures fixtures = new Fixtures(championship, viewGeneral, currentActivity, inflater, layoutGeneral, null, true, spinner);
            fixtures.execute();
        } catch (IOException e) {
            e.printStackTrace();
        }


        return viewGeneral;
    }

    public void setFragmentActivity(AppCompatActivity activity){
        this.currentActivity = activity;
    }
}