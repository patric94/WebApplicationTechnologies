package com.ted.getajob.model.pojo;

import java.util.Set;

public class CommentPOJO {
    private Long id;

    private String commenter;

    private String photo_url;

    private String content;

    private Long post_id;

    public CommentPOJO() {
    }

    public CommentPOJO(Long id, String commenter, String photo_url,String content, Long post_id) {
        this.id = id;
        this.commenter = commenter;
        this.photo_url = photo_url;
        this.content = content;
        this.post_id = post_id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCommenter() {
        return commenter;
    }

    public void setCommenter(String commenter) {
        this.commenter = commenter;
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

    public Long getPost_id() {
        return post_id;
    }

    public void setPost_id(Long post_id) {
        this.post_id = post_id;
    }
}
