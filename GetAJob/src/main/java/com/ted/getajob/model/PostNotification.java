package com.ted.getajob.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ted.getajob.model.pojo.NotificationPOJO;

import javax.persistence.*;

@Entity
@Table(name = "POST_NOTIFICATION")
public class PostNotification {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /*
    COMMENT NOTIFICATION = 0
    LIKE NOTIFICATION = 1
    */
    @Column(name = "TYPE")
    private int type;

    @ManyToOne
    @JoinColumn(name = "USER_ID", referencedColumnName = "ID")
    @JsonIgnore
    private UserModel userModel;

    @ManyToOne
    @JoinColumn(name = "POST_ID", referencedColumnName = "ID")
    @JsonIgnore
    private Post post;

    public PostNotification() {
    }

    public PostNotification(UserModel userModel, Post post, int type) {
        this.userModel = userModel;
        this.post = post;
        this.type = type;
    }

    public NotificationPOJO convertToPOJO() {
        return new NotificationPOJO(this.id, this.post.getId(), this.userModel.getUsername(), this.userModel.getPhoto_url(), this.type);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public UserModel getUserModel() {
        return userModel;
    }

    public void setUserModel(UserModel userModel) {
        this.userModel = userModel;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }
}
