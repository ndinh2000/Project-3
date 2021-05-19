package com.mycompany.PA3;

public class GetID {
    private static int id = 1;

    public void incId() {
        id += 1;
    }

    public int getId() {
        return id;
    }
}