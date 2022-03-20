package com.example.fragment;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

        view = inflater.inflate(R.layout.fragment_accueil, container,false);
        return view;
    }



  /*  View.OnClickListener directOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            LayoutInflater vi = (LayoutInflater)main.getSystemService(Context.LAYOUT_INFLATER_SERVICE);





        }

    };*/

    public void setFragmentActivity(AppCompatActivity activity){
        this.currentActivity = activity;
    }
}