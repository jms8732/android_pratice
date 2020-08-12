package com.example.mission_3;

public interface ItemVO {
    public static final int TYPE_HEADER = 0;
    public static final int TYPE_BODY = 1;

    abstract int getType();
}
