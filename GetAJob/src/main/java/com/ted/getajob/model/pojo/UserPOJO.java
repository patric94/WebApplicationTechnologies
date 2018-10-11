package com.ted.getajob.model.pojo;

public class UserPOJO {

    private String username;

    private String firstname;

    private String lastname;

    private String fullname;

    private String photo_url;

    private String curr_pos;

    private String curr_inst;

    public UserPOJO() {
    }

    public UserPOJO(String username, String firstname, String lastname, String fullname, String photo_url, String curr_pos, String curr_inst) {
        this.username = username;
        this.firstname = firstname;
        this.lastname = lastname;
        this.fullname = fullname;
        this.photo_url = photo_url;
        this.curr_pos = curr_pos;
        this.curr_inst = curr_inst;
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

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
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
}
