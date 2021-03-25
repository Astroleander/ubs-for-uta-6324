package uta.advse6324.ubs.pojo;

import java.io.Serializable;
public class Billing implements Serializable {

    private String id;
    private String name;
    private String address;
    private String userId;

    public Billing(String id, String name, String address, String userId) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.userId = userId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() { return name; }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }






}
