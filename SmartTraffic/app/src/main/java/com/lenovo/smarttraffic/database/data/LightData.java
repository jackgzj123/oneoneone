package com.lenovo.smarttraffic.database.data;


public class LightData {

    /**
     * greenTime : 12
     * yellowTime : 12
     * redTime : 12
     * roadName : 1
     */

    private int greenTime;
    private int yellowTime;
    private int redTime;
    private String roadName;

    public int getGreenTime() {
        return greenTime;
    }

    public void setGreenTime(int greenTime) {
        this.greenTime = greenTime;
    }

    public int getYellowTime() {
        return yellowTime;
    }

    public void setYellowTime(int yellowTime) {
        this.yellowTime = yellowTime;
    }

    public int getRedTime() {
        return redTime;
    }

    public void setRedTime(int redTime) {
        this.redTime = redTime;
    }

    public String getRoadName() {
        return roadName;
    }

    public void setRoadName(String roadName) {
        this.roadName = roadName;
    }
}
