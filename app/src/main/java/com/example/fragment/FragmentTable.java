/*
package com.example.fragment;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;

import com.example.BackEnd.Championship;
import com.example.BackEnd.Table;


public class FragmentTable extends Fragment {

    private View view;
    private AppCompatActivity currentActivity;
    private String championship;

    public FragmentTable(AppCompatActivity currentActivity, String championship)
    {
        this.championship = championship;
        this.currentActivity = currentActivity;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_classement_ligue, container, false);



        LinearLayout leftSideTable = view.findViewById(R.id.layoutLeftSide);
        LinearLayout rightSideTable = view.findViewById(R.id.layoutRightSide);



        try {
            Table table = new Table(championship, view, inflater, leftSideTable, rightSideTable);
            table.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return view;
    }

    public void setFragmentActivity(AppCompatActivity activity){
        this.currentActivity = activity;
    }
}*/
