package uta.advse6324.ubs.pojo;

import java.io.Serializable;

public class transaction implements Serializable {

    private String userid;
    private String merid;
    private String time;
    private Boolean buy_borrow;
    private Boolean pay_status;

    public transaction(String userid, String merid, String time, Boolean buy_borrow, Boolean pay_status) {
        this.userid = userid;
        this.merid = merid;
        this.time = time;
        this.buy_borrow = buy_borrow;
        this.pay_status = pay_status;
    }

    public transaction(String userid, String merid,  Boolean buy_borrow, Boolean pay_status) {
        this.userid = userid;
        this.merid = merid;
//        this.time = time;
        this.buy_borrow = buy_borrow;
        this.pay_status = pay_status;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getMerid() {
        return merid;
    }

    public void setMerid(String merid) {
        this.merid = merid;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Boolean getBuy_borrow() {
        return buy_borrow;
    }

    public void setBuy_borrow(Boolean buy_borrow) {
        this.buy_borrow = buy_borrow;
    }

    public Boolean getPay_status() {
        return pay_status;
    }

    public void setPay_status(Boolean pay_status) {
        this.pay_status = pay_status;
    }
}
