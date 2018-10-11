package com.ted.getajob.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ted.getajob.model.pojo.CommentPOJO;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "COMMENT")
public class Comment {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "CONTENT")
    private String content;

    @Column(name = "CREATED_AT")
    @CreationTimestamp
    private Date created_at;

    @ManyToOne
    @JoinColumn(name = "POST_ID", referencedColumnName = "ID")
    @JsonIgnore
    private Post post;

    @ManyToOne
    @JoinColumn(name = "USER_ID", referencedColumnName = "ID")
    @JsonIgnore
    private UserModel userModel;

    public Comment() {
    }

    public Comment(String content, Post post, UserModel userModel) {
        this.content = content;
        this.post = post;
        this.userModel = userModel;
    }

    public CommentPOJO convertToPOJO() {
        return new CommentPOJO(this.id, this.userModel.getUsername(), this.userModel.getPhoto_url(), this.content, this.post.getId());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public UserModel getUserModel() {
        return userModel;
    }

    public void setUserModel(UserModel userModel) {
        this.userModel = userModel;
    }
}
