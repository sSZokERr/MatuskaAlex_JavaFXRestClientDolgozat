package com.example.matuskaalex_javafxrestclientdolgozat;

import com.google.gson.annotations.Expose;

public class Person {
    private int id;
    @Expose
    private String nev;
    @Expose
    private int kor;
    @Expose
    private String minosites;
    @Expose
    private String meret;
    @Expose
    private Boolean alt8;

    public Person(int id, String nev, int kor, String minosites, boolean alt8, String meret) {
        this.id = id;
        this.nev = nev;
        this.kor = kor;
        this.minosites = minosites;
        this.alt8 = alt8;
        this.meret = meret;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNev(String nev) {
        this.nev = nev;
    }

    public void setKor(int kor) {
        this.kor = kor;
    }

    public void setMinosites(String minosites) {
        this.minosites = minosites;
    }

    public void setMeret(String meret){this.meret = meret;}

    public void setAlt8(boolean alt8){this.alt8 = alt8;}

    public int getId() {
        return id;
    }

    public String getNev() {
        return nev;
    }

    public int getKor() {
        return kor;
    }

    public String getMinosites() {
        return minosites;
    }

    public String getMeret() {
        return meret;
    }

    public Boolean getAlt8() {
        return alt8;
    }
}
