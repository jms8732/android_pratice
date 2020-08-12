package com.example.mission3;

public class BodyItem implements ItemVO{
    String name, date;

    public BodyItem(String n ,String d){
        this.date = d;
        this.name = n;
    }

    @Override
    public int getType() {
        return TYPE_BODY;
    }
}
