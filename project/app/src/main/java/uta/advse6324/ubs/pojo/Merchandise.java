package uta.advse6324.ubs.pojo;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.io.Serializable;

import uta.advse6324.ubs.utils.EnumTable;

public class Merchandise implements Serializable {

    private String id;
    private String name;
    private String description;
    // picture 还没定存储格式
    private byte[] picture;
    private String price;
    private Boolean available_status;
    private Boolean sell_lend;
    private String timestamp;
    private String owner_id;


    public Merchandise(String name, String description, byte[] picture, String price, Boolean available_status, Boolean sell_lend, String owner_id) {
//        this.id = id;
        this.name = name;
        this.description = description;
        this.picture = picture;
        this.price = price;
        this.available_status = available_status;
        this.sell_lend = sell_lend;
//        this.timestamp = timestamp;
        this.owner_id = owner_id;
    }
    public Merchandise(String id, String name, String description, byte[] picture, String price, Boolean available_status, Boolean sell_lend, String timestamp, String owner_id) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.picture = picture;
        this.price = price;
        this.available_status = available_status;
        this.sell_lend = sell_lend;
        this.timestamp = timestamp;
        this.owner_id = owner_id;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public byte[] getPicture() {
        return picture;
    }

    public void setPicture(byte[] picture) {
        this.picture = picture;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }



    public Boolean getSell_lend() {
        return sell_lend;
    }

    public void setSell_lend(Boolean sell_lend) {
        this.sell_lend = sell_lend;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getOwner_id() {
        return owner_id;
    }

    public void setOwner_id(String owner_id) {
        this.owner_id = owner_id;
    }

    public Boolean getAvailable_status() {
        return available_status;
    }

    public void setAvailable_status(Boolean available_status) {
        this.available_status = available_status;
    }





}
