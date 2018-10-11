package com.ted.getajob.model.pojo;

import java.util.Set;

public class UserXML {

    private String username;

    private String firstname;

    private String lastname;

    private String photo_url;

    private String curr_pos;

    private String curr_inst;

    private Set<String> friends;

    private Set<String> jobs;

    private Set<PostPOJO> posts;

    private Set<CommentPOJO> comments;

    public UserXML() {
    }

    public UserXML(String username, String firstname, String lastname, String photo_url, String curr_pos, String curr_inst, Set<String> friends, Set<String> jobs, Set<PostPOJO> posts, Set<CommentPOJO> comments) {
        this.username = username;
        this.firstname = firstname;
        this.lastname = lastname;
        this.photo_url = photo_url;
        this.curr_pos = curr_pos;
        this.curr_inst = curr_inst;
        this.friends = friends;
        this.jobs = jobs;
        this.posts = posts;
        this.comments = comments;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getPhoto_url() {
        return photo_url;
    }

    public void setPhoto_url(String photo_url) {
        this.photo_url = photo_url;
    }

    public String getCurr_pos() {
        return curr_pos;
    }

    public void setCurr_pos(String curr_pos) {
        this.curr_pos = curr_pos;
    }

    public String getCurr_inst() {
        return curr_inst;
    }

    public void setCurr_inst(String curr_inst) {
        this.curr_inst = curr_inst;
    }

    public Set<String> getFriends() {
        return friends;
    }

    public void setFriends(Set<String> friends) {
        this.friends = friends;
    }

    public Set<String> getJobs() {
        return jobs;
    }

    public void setJobs(Set<String> jobs) {
        this.jobs = jobs;
    }

    public Set<PostPOJO> getPosts() {
        return posts;
    }

    public void setPosts(Set<PostPOJO> posts) {
        this.posts = posts;
    }

    public Set<CommentPOJO> getComments() {
        return comments;
    }

    public void setComments(Set<CommentPOJO> comments) {
        this.comments = comments;
    }
}
