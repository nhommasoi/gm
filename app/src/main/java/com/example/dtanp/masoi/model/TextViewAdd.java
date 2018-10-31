package com.example.dtanp.masoi.model;

public class TextViewAdd {
    int id;
    boolean chon;

    public TextViewAdd() {

    }

    public TextViewAdd(int id, boolean chon) {
        this.id = id;
        this.chon = chon;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isChon() {
        return chon;
    }

    public void setChon(boolean chon) {
        this.chon = chon;
    }
}
