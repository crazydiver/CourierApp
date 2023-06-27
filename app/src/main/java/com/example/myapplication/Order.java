package com.example.myapplication;

import android.content.Intent;

public class Order {
    private Corp corp;
    private String corpDest;
    private Pac pac;
    private int cost;
    private int pacSize;
    private String dest;

    public Corp getCorp() {
        return corp;
    }
    public String getCorpName(){
        return corpDest;
    }

    public void setCorp(Corp corp) {
        this.corp = corp;
    }

    public Pac getPac() {
        return pac;
    }

    public Integer getPacSize(){ return pacSize; }

    public void setPac(Pac pac) {
        this.pac = pac;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public String getDest() {
        return dest;
    }

    public void setDest(String dest) {
        this.dest = dest;
    }

    public Order(Corp corp, Pac pac, int cost, String dest) {
        this.corp = corp;
        this.corpDest = corp.getAdr();
        this.pac = pac;
        this.pacSize = pac.getVol();
        this.cost = cost;
        this.dest = dest;
    }

    public Order() {
        corp = new Corp();
        pac = new Pac();
        cost = 0;
        dest = "";
    }
}
