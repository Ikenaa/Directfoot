package com.example.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.BackEnd.Championship;
import com.example.BackEnd.Game;
import com.example.BackEnd.Statut;


public class FragmentMatch extends Fragment {

    private Game game;

    public FragmentMatch(MainActivity currentActivity, Game game) {
        // Required empty public constructor
        this.game = game;
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.statistique_match_top, container, false);
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                switch (game.getChampionship())
                {
                    case Championship
                            .BUNDESLIGA : ((TextView)view.findViewById(R.id.nameChamp)).setText("Bundesliga");
                        break;
                    case Championship
                            .LIGUE_1 : ((TextView)view.findViewById(R.id.nameChamp)).setText("Ligue 1");
                        break;
                    case Championship
                            .SERIE_A : ((TextView)view.findViewById(R.id.nameChamp)).setText("Serie A");
                        break;
                    case Championship
                            .PREMIER_LEAGUE: ((TextView)view.findViewById(R.id.nameChamp)).setText("Premier League");
                        break;
                    case Championship
                            .LIGA: ((TextView)view.findViewById(R.id.nameChamp)).setText("Liga");
                        break;
                }


                ((TextView)view.findViewById(R.id.date_match)).setText(game.getDate());
                ((TextView)view.findViewById(R.id.statut_match)).setText(game.getStatut());
                ((ImageView)view.findViewById(R.id.homeLogo)).setImageBitmap(game.getLogoHome());
                ((ImageView)view.findViewById(R.id.awayLogo)).setImageBitmap(game.getLogoAway());

                if(game.getStatut().equals(Statut.RUNNING)
                || game.getStatut().equals(Statut.FINISHED)
                || game.getStatut().equals(Statut.HALF_TIME))
                {
                    game.setView(view);
                    game.setInflater(inflater);
                    game.execute();
                }
           }
        });


        return view;
    }
}