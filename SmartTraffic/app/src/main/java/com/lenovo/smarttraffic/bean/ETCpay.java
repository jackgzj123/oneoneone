package com.lenovo.smarttraffic.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Generated;

/**
 * @Author：gzj
 * @CreateDate: 2019/11/6
 */

@Entity
public class ETCpay {
    @Id(autoincrement = true)
    private Long id;
    @NotNull
    private String carname;
    private String time;
    private String paymoney;
    private String carid;
    @Generated(hash = 1400512198)
    public ETCpay(Long id, @NotNull String carname, String time, String paymoney,
            String carid) {
        this.id = id;
        this.carname = carname;
        this.time = time;
        this.paymoney = paymoney;
        this.carid = carid;
    }
    @Generated(hash = 1944478946)
    public ETCpay() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getCarname() {
        return this.carname;
    }
    public void setCarname(String carname) {
        this.carname = carname;
    }
    public String getTime() {
        return this.time;
    }
    public void setTime(String time) {
        this.time = time;
    }
    public String getPaymoney() {
        return this.paymoney;
    }
    public void setPaymoney(String paymoney) {
        this.paymoney = paymoney;
    }
    public String getCarid() {
        return this.carid;
    }
    public void setCarid(String carid) {
        this.carid = carid;
    }

}
