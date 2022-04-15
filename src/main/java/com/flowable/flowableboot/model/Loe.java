package com.flowable.flowableboot.model;

import java.util.HashMap;
import java.util.Map;

public enum Loe {
    HIGH("High"),
    MEDIUM("Medium"),
    LOW("Low");

    public final String loe;
    private static final Map<String, Loe> map = new HashMap<String, Loe>();

    static {
        for (Loe l: values()){
            map.put(l.loe, l);
        }
    }

    private Loe(String loe) {
        this.loe = loe;
    }

    public String loe() {
        return loe;
    }

    public static Loe valueOfLoe(String loe){
        return map.get(loe);
    }
}
