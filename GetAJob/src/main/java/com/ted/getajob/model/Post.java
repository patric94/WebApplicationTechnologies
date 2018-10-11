package com.ted.getajob.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ted.getajob.model.pojo.CommentPOJO;
import com.ted.getajob.model.pojo.PostPOJO;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "POST")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Post {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "CONTENT")
    private String content;

    @Column(nullable = false, updatable = false)
    @CreationTimestamp
    @JsonIgnore
    private Date created_at;

    @ManyToOne
    @JoinColumn(name = "USER_ID", referencedColumnName = "ID")
    @JsonIgnore
    private UserModel userModel;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private List<Comment> comments = new ArrayList<>();

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private Set<Interest> interests = new HashSet<>();

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<PostNotification> notifications = new HashSet<>();

    public Post() {
    }

    public Post(String content, UserModel userModel) {
        this.content = content;
        this.userModel = userModel;
    }

    public Post(String content, UserModel userModel, List<Comment> comments, Set<Interest> interests) {
        this.content = content;
        this.userModel = userModel;
        this.comments = comments;
        this.interests = interests;
    }

    public PostPOJO convertToPOJO() {
        List<CommentPOJO> pojos = new ArrayList<>();
        for (Comment com : this.comments){
            pojos.add(com.convertToPOJO());
        }
        return new PostPOJO(this.id, this.userModel.getUsername(), this.content, this.userModel.getPhoto_url(), pojos, this.interests.size(), this.created_at);
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

    public UserModel getUserModel() {
        return userModel;
    }

    public void setUserModel(UserModel userModel) {
        this.userModel = userModel;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public Set<Interest> getInterests() {
        return interests;
    }

    public void setInterests(Set<Interest> interests) {
        this.interests = interests;
    }

    public Set<PostNotification> getNotifications() {
        return notifications;
    }

    public void setNotifications(Set<PostNotification> notifications) {
        this.notifications = notifications;
    }
}
