package com.ted.getajob.model.pojo;

import java.util.Date;
import java.util.List;
import java.util.Set;

public class PostPOJO {
    private Long id;

    private String username;

    private String content;

    private String photo_url;

    private List<CommentPOJO> comments;

    private int likes;

    private Date created_at;

    public PostPOJO() {
    }

    public PostPOJO(Long id, String username, String content, String photo_url, List<CommentPOJO> comments, int likes, Date created_at) {
        this.id = id;
        this.username = username;
        this.content = content;
        this.photo_url = photo_url;
        this.comments = comments;
        this.likes = likes;
        this.created_at = created_at;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPhoto_url() {
        return photo_url;
    }

    public void setPhoto_url(String photo_url) {
        this.photo_url = photo_url;
    }

    public List<CommentPOJO> getComments() {
        return comments;
    }

    public void setComments(List<CommentPOJO> comments) {
        this.comments = comments;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }
}
