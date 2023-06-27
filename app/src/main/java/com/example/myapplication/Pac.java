package com.example.myapplication;

public class Pac {
    private int vol;
    private boolean isFragile;
    private String req;
    private PacType type;

    public int getVol() {
        return vol;
    }

    public void setVol(int vol) {
        this.vol = vol;
    }

    public boolean isFragile() {
        return isFragile;
    }

    public void setFragile(boolean fragile) {
        isFragile = fragile;
    }

    public String getReq() {
        return req;
    }

    public void setReq(String req) {
        this.req = req;
    }

    public PacType getType() {
        return type;
    }

    public void setType(PacType type) {
        this.type = type;
    }

    public Pac() {
        vol = 0;
        isFragile = false;
        req = "";
        type = PacType.NONE;
    }

    public Pac(int vol, boolean isFragile, String req, PacType type) {
        this.vol = vol;
        this.isFragile = isFragile;
        this.req = req;
        this.type = type;
    }
}
