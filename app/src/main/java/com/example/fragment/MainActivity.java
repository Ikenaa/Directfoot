package com.example.fragment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity{

    private Fragment fragment1;
    private Button AccueilFragmentBtn, SearchFragmentBtn, DirectFragmentButton;

   // private SwipeListener swipeListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        MainActivity mainActivity = this;

        setContentView(R.layout.activity_main);




        fragment1 = new FragmentHome(mainActivity);


        replaceFragment(fragment1);

        AccueilFragmentBtn = findViewById(R.id.accueil);


        SearchFragmentBtn = findViewById(R.id.search);

        DirectFragmentButton = findViewById(R.id.direct);


        AccueilFragmentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragment1 = new FragmentHome(mainActivity);



                replaceFragment(fragment1);

            }
        });

       /* ClassementFragmentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                replaceFragment(new FragmentTableHome(mainActivity));


            }
        });*/

        SearchFragmentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                replaceFragment(new FragmentRecherche(mainActivity));

            }
        });

        DirectFragmentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                replaceFragment(new FragmentLive(mainActivity));

            }
        });



    }

    public void replaceFragment(Fragment fragment) {

        FragmentManager fragmentManager = getSupportFragmentManager();

        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        fragmentTransaction.replace(R.id.frameLayout,fragment);

        fragmentTransaction.commit();

    }

    public void removeFragment(Fragment fragment) {

        FragmentManager fragmentManager = getSupportFragmentManager();

        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        fragmentTransaction.remove(fragment);

        fragmentTransaction.commit();

    }

 /*   @Override
    public void onBackPressed() {

       removeFragment(fragment1);
    }


    private class SwipeListener implements View.OnTouchListener {

        GestureDetector gestureDetector;

        SwipeListener(View view){
            int threshold = 100;
            int velocity_threshold = 100;

            GestureDetector.SimpleOnGestureListener listener =
                    new GestureDetector.SimpleOnGestureListener(){
                        @Override
                        public boolean onLeft(MotionEvent e){

                        }
                    }
        }

        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {
            return false;
        }
    }*/
}