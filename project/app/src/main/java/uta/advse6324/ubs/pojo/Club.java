package uta.advse6324.ubs.pojo;

import java.io.Serializable;

public class Club implements Serializable {
    private String name;
    private String introduction;
    private String category;
    private String managerid;

    public Club(String name, String managerid, String category, String introduction){
        this.name = name;
        this.introduction = introduction;
        this.category = category;
        this.managerid = managerid;
    }


    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCategory() {
        return category;
    }

    public void setManagerid(String managerid) {
        this.managerid = managerid;
    }

    public String getManagerid() {
        return managerid;
    }
}
