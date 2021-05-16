package com.mycompany.PA2;

public class GetID {
    private static int id = 0;

    public void incId() {
        id += 1;
    }

    public int getId() {
        return id;
    }
}