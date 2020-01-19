package com.lenovo.smarttraffic.ui.activity.T56;


public class EnvironmentData {

    /**
     * lightIntensity : 3491
     * road : {"roadId":1,"status":4}
     * co2 : 1801
     * temperature : -27
     * humidity : 575
     * pm : 831
     */

    private String lightIntensity;
    private RoadBean road;
    private String co2;
    private String temperature;
    private String humidity;
    private String pm;

    public String getLightIntensity() {
        return lightIntensity;
    }

    public void setLightIntensity(String lightIntensity) {
        this.lightIntensity = lightIntensity;
    }

    public RoadBean getRoad() {
        return road;
    }

    public void setRoad(RoadBean road) {
        this.road = road;
    }

    public String getCo2() {
        return co2;
    }

    public void setCo2(String co2) {
        this.co2 = co2;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getHumidity() {
        return humidity;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }

    public String getPm() {
        return pm;
    }

    public void setPm(String pm) {
        this.pm = pm;
    }

    public static class RoadBean {
        /**
         * roadId : 1
         * status : 4
         */

        private int roadId;
        private int status;

        public int getRoadId() {
            return roadId;
        }

        public void setRoadId(int roadId) {
            this.roadId = roadId;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }
    }
}
