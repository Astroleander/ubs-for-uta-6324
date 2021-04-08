package uta.advse6324.ubs.pojo;

import java.io.Serializable;

public class ClubMember implements Serializable {
    private String clubname;
    private String username;

    public ClubMember(String clubname, String username) {
        this.clubname = clubname;
        this.username = username;
    }

    public String getClubname() {
        return clubname;
    }

    public String getUsername() {
        return username;
    }

    public void setClubname(String clubname) {
        this.clubname = clubname;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
