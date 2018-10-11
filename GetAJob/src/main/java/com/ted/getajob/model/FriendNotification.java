package com.ted.getajob.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ted.getajob.model.pojo.NotificationPOJO;

import javax.persistence.*;

@Entity
@Table(name = "FRIEND_NOTIFICATION")
public class FriendNotification {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "USER_ID", referencedColumnName = "ID")
    @JsonIgnore
    private UserModel userModel;

    @OneToOne
    @JoinColumn(name = "REQUEST_ID", referencedColumnName = "ID")
    @JsonIgnore
    private FriendRequest friendRequest;

    public FriendNotification() {
    }

    public FriendNotification(UserModel userModel, FriendRequest friendRequest) {
        this.userModel = userModel;
        this.friendRequest = friendRequest;
    }

    public NotificationPOJO convertToPOJO() {
        return new NotificationPOJO(this.friendRequest.getActiveUser().getUsername(), this.friendRequest.getActiveUser().getPhoto_url());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserModel getUserModel() {
        return userModel;
    }

    public void setUserModel(UserModel userModel) {
        this.userModel = userModel;
    }

    public FriendRequest getFriendRequest() {
        return friendRequest;
    }

    public void setFriendRequest(FriendRequest friendRequest) {
        this.friendRequest = friendRequest;
    }
}
