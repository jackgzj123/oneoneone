package com.lenovo.smarttraffic.bean;

public class ZFM11Entity {
    private String roadName;
    private int red;
    private int green;
    private int yellow;

    public ZFM11Entity(String roadName, int red, int green, int yellow) {
        this.roadName = roadName;
        this.red = red;
        this.green = green;
        this.yellow = yellow;
    }

    public String getRoadName() {
        return roadName;
    }

    public void setRoadName(String roadName) {
        this.roadName = roadName;
    }

    public int getRed() {
        return red;
    }

    public void setRed(int red) {
        this.red = red;
    }

    public int getGreen() {
        return green;
    }

    public void setGreen(int green) {
        this.green = green;
    }

    public int getYellow() {
        return yellow;
    }

    public void setYellow(int yellow) {
        this.yellow = yellow;
    }
}
