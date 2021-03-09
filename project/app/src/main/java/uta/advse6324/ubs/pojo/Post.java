package uta.advse6324.ubs.pojo;

import android.util.Log;

import java.sql.Timestamp;

import static android.content.ContentValues.TAG;

public class Post {

    private String id;
    private String title;
    private String content;
    private Integer liked;
    private String owner;
    private Boolean advertisement;
    private String contact;
    private String timestamp;

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


    public Post(String id, String title, String content, String owner, String contact, Boolean is_adv, Integer liked) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.liked = liked;
        this.owner = owner;
        this.contact = contact;
        this.advertisement = is_adv;
    }

    public Post(String id, String title, String content, String owner, String contact, Boolean is_adv, Integer liked, String timestamp) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.liked = liked;
        this.owner = owner;
        this.contact = contact;
        this.advertisement = is_adv;
        this.timestamp = timestamp;
    }

    public Boolean getAdvertisement() {
        return advertisement;
    }

    public void setAdvertisement(Boolean advertisement) {
        this.advertisement = advertisement;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}
