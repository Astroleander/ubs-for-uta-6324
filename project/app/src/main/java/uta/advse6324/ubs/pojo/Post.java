package uta.advse6324.ubs.pojo;

import java.io.Serializable;
import java.sql.Timestamp;

public class Post implements Serializable {

    private String id;
    private String title;
    private String content;
    private Integer liked;
    private String owner;
    private Timestamp timestamp;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getLiked() {
        return liked;
    }

    public void setLiked(Integer liked) {
        this.liked = liked;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }


    public Post(String id, String title, String content, Integer liked, String owner) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.liked = liked;
        this.owner = owner;
    }

}
