package com.lenovo.smarttraffic.database.data;

/**
 * @Author：gzj
 * @CreateDate: 2019/11/6
 */
public class ETCData {

    /**
     * carNumber : 辽A10001
     * carMoney : 10825
     * carId : 1
     */

    private String carNumber;
    private String carMoney;
    private int carId;

    public String getCarNumber() {
        return carNumber;
    }

    public void setCarNumber(String carNumber) {
        this.carNumber = carNumber;
    }

    public String getCarMoney() {
        return carMoney;
    }

    public void setCarMoney(String carMoney) {
        this.carMoney = carMoney;
    }

    public int getCarId() {
        return carId;
    }

    public void setCarId(int carId) {
        this.carId = carId;
    }
}
