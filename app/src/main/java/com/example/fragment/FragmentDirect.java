package com.example.fragment;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.BackEnd.Championship;
import com.example.BackEnd.Game;
import com.example.BackEnd.LiveGames;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class FragmentDirect extends Fragment {



    private View view;
    private TextView team;
    private MainActivity currentActivity;
    private Date dateObj;
    private Button day;

    public FragmentDirect(MainActivity mainActivity) {
        super();
        this.currentActivity = mainActivity;
    }


    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        view = inflater.inflate(R.layout.fragment_accueil, container, false);
        LinearLayout competFav = view.findViewById(R.id.compet);
        View progressBar = inflater.inflate(R.layout.loading,view.findViewById((R.id.compet)),false);


        SimpleDateFormat dtf = new SimpleDateFormat("EEE dd/MM", new Locale("FR", "fr"));
        SimpleDateFormat dtf2 = new SimpleDateFormat("yyyyMMdd", new Locale("FR", "fr"));
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, -8);
        String formattedDateButton;
        LiveGames lg;

        for(Integer i = 1; i <=15; i++)
        {
            day = (Button) view.findViewWithTag("day" + i.toString());
            calendar.add(Calendar.DATE, +1);
            dateObj = calendar.getTime();
            if(i!=8) {

                formattedDateButton = dtf.format(dateObj);
            }
            else
            {
                formattedDateButton = "AUJOURD'HUI";
            }

            day.setText(formattedDateButton);
            String t = dtf2.format(dateObj);

            day.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    try {

                        LiveGames lg = new LiveGames(FragmentDirect.super.getView(), inflater, competFav, currentActivity, progressBar, t);
                        lg.execute();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });

        }


        try {
            lg = new LiveGames(view, inflater, competFav, currentActivity, progressBar, null);
            lg.execute();
        } catch (IOException e) {
            e.printStackTrace();
        }

        final HorizontalScrollView hsv = (HorizontalScrollView) view.findViewById(R.id.horizontalScrollView);

        // Scrolling to button 5.
        hsv.post(new Runnable() {
            @Override
            public void run() {
                // Get the button.
                View button = view.findViewById(R.id.day12);

                // Locate the button.
                int x, y;
                x = button.getLeft() - button.getLeft() / 2;
                y = button.getTop();
                System.out.println("X = " + x + " Y = " + y);
                // Scroll to the button.
                hsv.scrollTo(x, y);
            }
        });

        return view;
    }

    @Nullable
    @Override
    public View getView() {
        return view;
    }
}
