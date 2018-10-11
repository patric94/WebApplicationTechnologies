package com.ted.getajob.model.pojo;

public class NotificationPOJO {
    private Long id;

    private Long post_id;

    private String username;

    private String photo_url;

    private int type;

    public NotificationPOJO() {
    }

    public NotificationPOJO(String username, String photo_url) {
        this.username = username;
        this.photo_url = photo_url;
    }

    public NotificationPOJO(Long id, Long post_id, String username, String photo_url, int type) {
        this.id = id;
        this.post_id = post_id;
        this.username = username;
        this.photo_url = photo_url;
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPost_id() {
        return post_id;
    }

    public void setPost_id(Long post_id) {
        this.post_id = post_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPhoto_url() {
        return photo_url;
    }

    public void setPhoto_url(String photo_url) {
        this.photo_url = photo_url;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
