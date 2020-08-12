package com.example.mission_3;

public class HeaderItem implements ItemVO {
    String date;

    public HeaderItem(String d){
        this.date =d;
    }

    @Override
    public int getType() {
        return TYPE_HEADER;
    }
}
