package com.example.BackEnd.GameEvent;

public abstract class GameEvent implements Comparable {

    public enum TypeEvent
    {
        RED_CARD, YELLOW_CARD, GOAL, CHANGE;
    }

    public enum Side
    {
        HOME, AWAY;
    }

    protected TypeEvent typeEvent = null;
    protected String minute = null;
    protected String player = null;
    protected Side side = null;

    public GameEvent(Side side)
    {
        this.side = side;
    }

    @Override
    public int compareTo(Object o) {
        GameEvent gameEventCompare = (GameEvent) o;

        Integer minute1 = 0;
        Integer minute2 = 0;

        if(!this.minute.contains("+"))
            minute1 = Integer.parseInt(this.minute.replace("’", ""));

        if(!((GameEvent) o).getMinute().contains("+"))
            minute2 = Integer.parseInt(((GameEvent) o).getMinute().replace("’", ""));

        if(this.minute.contains("90’ +"))
            minute1 = Integer.parseInt(this.minute.substring(0, 2)) + Integer.parseInt(this.minute.replace("90’ +", ""));

        if(((GameEvent) o).getMinute().contains("90’ +"))
            minute2 = Integer.parseInt(((GameEvent) o).getMinute().substring(0, 2)) + Integer.parseInt(((GameEvent) o).getMinute().replace("90’ +", ""));

        if(this.minute.contains("45’ +"))
        {
            if(((GameEvent) o).getMinute().contains("45’ +"))
            {
                minute1 = Integer.parseInt(this.minute.replace("45’ +", ""));
                minute2 = Integer.parseInt(((GameEvent) o).getMinute().replace("45’ +", ""));
            }
            else minute1 = 45;
        }

        return minute1.compareTo(minute2);
    }

    public TypeEvent getTypeEvent() {
        return typeEvent;
    }

    public String getMinute() {
        return minute;
    }

    public String getPlayer() {
        return player;
    }

    public Side getSide() {
        return side;
    }



}
