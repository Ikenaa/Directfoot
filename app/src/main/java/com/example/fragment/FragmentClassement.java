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


public class FragmentClassement extends Fragment {

    View view;
    private AppCompatActivity currentActivity;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_classement_ligue, container, false);
        for(int i = 0; i< 20; i++) {
            LinearLayout leftSideTable = view.findViewById(R.id.layoutLeftSide);
            View leftSide = inflater.inflate(R.layout.left_side_table, view.findViewById(R.id.layoutLeftSide), false);
            ((TextView) leftSide.findViewById(R.id.rank)).setText("1");
            Drawable myDrawable = getResources().getDrawable(R.drawable.logo_bundesliga);
            ((ImageView) leftSide.findViewById(R.id.logo)).setImageDrawable(myDrawable);
            ((TextView) leftSide.findViewById(R.id.points)).setText("32");
            leftSideTable.addView(leftSide, new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT, 0));

            LinearLayout rightSideTable = view.findViewById((R.id.layoutRightSide));
            View rightSide = inflater.inflate(R.layout.right_side_table, view.findViewById(R.id.layoutRightSide), false);
            ((TextView) rightSide.findViewById(R.id.played)).setText("11");
            ((TextView) rightSide.findViewById(R.id.win)).setText("12");
            ((TextView) rightSide.findViewById(R.id.draw)).setText("13");
            ((TextView) rightSide.findViewById(R.id.lose)).setText("14");
            ((TextView) rightSide.findViewById(R.id.goalsFor)).setText("15");
            ((TextView) rightSide.findViewById(R.id.goalsAgainst)).setText("16");
            ((TextView) rightSide.findViewById(R.id.goalDiff)).setText("17");
            rightSideTable.addView(rightSide, new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT, 0));
        }
        return view;
    }

    public void setFragmentActivity(AppCompatActivity activity){
        this.currentActivity = activity;
    }
}