package com.example.fragment;

import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.BackEnd.Championship;
import com.example.BackEnd.Table;


public class FragmentTable extends Fragment {

    View view;
    private AppCompatActivity currentActivity;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_classement_ligue, container, false);

        LinearLayout leftSideTable = view.findViewById(R.id.layoutLeftSide);
        LinearLayout rightSideTable = view.findViewById((R.id.layoutRightSide));

        try {
            Table italyTable = new Table(Championship.SERIE_A, view, inflater, leftSideTable, rightSideTable);
            italyTable.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return view;
    }

    public void setFragmentActivity(AppCompatActivity activity){
        this.currentActivity = activity;
    }
}