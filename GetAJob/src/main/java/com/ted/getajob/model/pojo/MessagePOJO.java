package com.ted.getajob.model.pojo;

import java.util.Date;

public class MessagePOJO {
    private Long id;

    private Long chat_id;

    private String sender;

    private String photo_url;

    private String content;

    private Date send_at;

    public MessagePOJO() {
    }

    public MessagePOJO(Long id, Long chat_id, String sender, String photo_url, String content, Date send_at) {
        this.id = id;
        this.chat_id = chat_id;
        this.sender = sender;
        this.photo_url = photo_url;
        this.content = content;
        this.send_at = send_at;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getChat_id() {
        return chat_id;
    }

    public void setChat_id(Long chat_id) {
        this.chat_id = chat_id;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getPhoto_url() {
        return photo_url;
    }

    public void setPhoto_url(String photo_url) {
        this.photo_url = photo_url;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getSend_at() {
        return send_at;
    }

    public void setSend_at(Date send_at) {
        this.send_at = send_at;
    }
}
