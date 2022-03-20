package com.example.BackEnd.GameEvent;

import com.example.BackEnd.JsonModel.GameEventJsonModel;

public class GoalEvent extends GameEvent{

    public GoalEvent(GameEventJsonModel.But but, Side side)
    {
        super(side);
        typeEvent = TypeEvent.GOAL;
        this.minute = but.getInstant().getLibelle();
        this.player = but.getJoueur().getNom_abrege();
    }
}
