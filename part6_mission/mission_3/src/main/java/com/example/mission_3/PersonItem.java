package com.example.mission_3;

public class PersonItem implements ItemVO {
    String name, date;

    public PersonItem(String d, String n) {
        this.date = d;
        this.name = n;
    }

    @Override
    public int getType() {
        return TYPE_BODY;
    }
}
