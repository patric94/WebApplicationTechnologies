package com.ted.getajob.model;

/*
This entity represents a friend request between two users.
The status field represents the condition of the friend request as explained below
    Code	Meaning
    0	    Pending
    1	    Accepted
    2	    Declined
In order to avoid duplicate entries, user_one_id must always be less than user_two_id
*/

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "FRIEND_REQUEST")
public class FriendRequest {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "USER_ONE_ID", referencedColumnName = "ID")
    @JsonIgnore
    private UserModel userOne;

    @ManyToOne
    @JoinColumn(name = "USER_TWO_ID", referencedColumnName = "ID")
    @JsonIgnore
    private UserModel userTwo;

    @Column(name = "STATUS")
    private int status;

    @ManyToOne
    @JoinColumn(name = "ACTIVE_USER_ID", referencedColumnName = "ID")
    @JsonIgnore
    private UserModel activeUser;

    public FriendRequest() {
    }

    public FriendRequest(UserModel userOne, UserModel userTwo, int status, UserModel activeUser) {
        this.userOne = userOne;
        this.userTwo = userTwo;
        this.status = status;
        this.activeUser = activeUser;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public UserModel getUserOne() {
        return userOne;
    }

    public void setUserOne(UserModel userOne) {
        this.userOne = userOne;
    }

    public UserModel getUserTwo() {
        return userTwo;
    }

    public void setUserTwo(UserModel userTwo) {
        this.userTwo = userTwo;
    }

    public UserModel getActiveUser() {
        return activeUser;
    }

    public void setActiveUser(UserModel activeUser) {
        this.activeUser = activeUser;
    }
}
