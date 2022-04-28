package com.flowable.flowableboot.model;

import java.util.HashMap;
import java.util.Map;

public enum Loe {
    ONE(1),
    THREE(3),
    FIVE(5);

    public final int value;
    private static final Map map = new HashMap<>();

    private Loe(int value){
        this.value = value;
    }

    static {
        for (Loe l: Loe.values()){
            map.put(l.value, l);
        }
    }


    public static Loe valueOf(int loe){
        return (Loe) map.get(loe);
    }

    public int getValue() {
        return value;
    }
}
