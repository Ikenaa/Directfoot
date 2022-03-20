package com.example.fragment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity{

    FragmentAccueil fragment1;
    FragmentClassement fragment2;

    Button AccueilFragmentBtn, ClassementFragmentBtn, SearchFragmentBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MainActivity main = this;
        setContentView(R.layout.activity_main);

        fragment1 = new FragmentAccueil();
        fragment1.setFragmentActivity(this);

        replaceFragment(fragment1);

        AccueilFragmentBtn = findViewById(R.id.direct);

        ClassementFragmentBtn = findViewById(R.id.classement);

        SearchFragmentBtn = findViewById(R.id.search);
        AccueilFragmentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                replaceFragment(new FragmentAccueil());

            }
        });

        ClassementFragmentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                replaceFragment(new FragmentClassement());

            }
        });

        SearchFragmentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                replaceFragment(new FragmentRecherche());

            }
        });

     /*   FavorisFragmentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                replaceFragment(new FragmentFavoris());

            }
        });*/


    }

    private void replaceFragment(Fragment fragment) {

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout,fragment);
        fragmentTransaction.commit();
    }


}