package com.example.fragment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity{

    FragmentHome fragment1;

    Button AccueilFragmentBtn, ClassementFragmentBtn, SearchFragmentBtn, DirectFragmentButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        MainActivity mainActivity = this;

        setContentView(R.layout.activity_main);

        fragment1 = new FragmentHome(mainActivity);
        fragment1.setFragmentActivity(this);

        replaceFragment(fragment1);

        AccueilFragmentBtn = findViewById(R.id.accueil);

        ClassementFragmentBtn = findViewById(R.id.classement);

        SearchFragmentBtn = findViewById(R.id.search);

        DirectFragmentButton = findViewById(R.id.direct);


        AccueilFragmentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragment1 = new FragmentHome(mainActivity);
                fragment1.setFragmentActivity(mainActivity);


                replaceFragment(fragment1);

            }
        });

        ClassementFragmentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                replaceFragment(new FragmentTableHome(mainActivity));

            }
        });

        SearchFragmentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                replaceFragment(new FragmentMatch(mainActivity));

            }
        });

        DirectFragmentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                replaceFragment(new FragmentDirect(mainActivity));

            }
        });

     /*   FavorisFragmentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                replaceFragment(new FragmentFavoris());

            }
        });*/


    }

    public void replaceFragment(Fragment fragment) {

        FragmentManager fragmentManager = getSupportFragmentManager();

        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        fragmentTransaction.replace(R.id.frameLayout,fragment);

        fragmentTransaction.commit();

    }


}