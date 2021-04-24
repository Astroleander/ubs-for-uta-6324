package uta.advse6324.ubs.pojo;

import java.io.Serializable;

public class Message implements Serializable {
    private String time;
    private String send;
    private String receive;
    private String content;
    private boolean read_status; // 1 已读 0 未读


    public Message(String time, String send, String receive, String content, boolean read_status) {
        this.time = time;
        this.send = send;
        this.receive = receive;
        this.content = content;
        this.read_status = read_status;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getSend() {
        return send;
    }

    public void setSend(String send) {
        this.send = send;
    }

    public String getReceive() {
        return receive;
    }

    public void setReceive(String receive) {
        this.receive = receive;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isRead_status() {
        return read_status;
    }

    public void setRead_status(boolean read_status) {
        this.read_status = read_status;
    }
}
