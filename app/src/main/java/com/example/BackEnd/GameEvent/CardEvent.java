package com.example.BackEnd.GameEvent;

import com.example.BackEnd.JsonModel.GameEventJsonModel;

public class CardEvent extends GameEvent{

    public CardEvent(GameEventJsonModel.Carton card, Side side)
    {
        super(side);

        if(card.getType().equalsIgnoreCase("jaune"))
            this.typeEvent = TypeEvent.YELLOW_CARD;
        else this.typeEvent = TypeEvent.RED_CARD;

        this.minute = card.getInstant().getLibelle();
        this.player = card.getJoueur().getNom_abrege();
    }
}
