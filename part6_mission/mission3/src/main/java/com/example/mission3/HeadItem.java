package com.example.mission3;

public class HeadItem implements ItemVO {
    String header;

    public HeadItem(String h){
        this.header =h;
    }

    @Override
    public int getType() {
        return TYPE_HEAD;
    }
}
