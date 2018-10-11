package com.ted.getajob.model.pojo;

import java.util.Date;
import java.util.List;

public class ChatPOJO {
    private Long id;

    private UserPOJO user_one;

    private UserPOJO user_two;

    private Date last_mes_sent;

    public ChatPOJO() {
    }

    public ChatPOJO(Long id, UserPOJO user_one, UserPOJO user_two, Date last_mes_sent) {
        this.id = id;
        this.user_one = user_one;
        this.user_two = user_two;
        this.last_mes_sent = last_mes_sent;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserPOJO getUser_one() {
        return user_one;
    }

    public void setUser_one(UserPOJO user_one) {
        this.user_one = user_one;
    }

    public UserPOJO getUser_two() {
        return user_two;
    }

    public void setUser_two(UserPOJO user_two) {
        this.user_two = user_two;
    }

    public Date getLast_mes_sent() {
        return last_mes_sent;
    }

    public void setLast_mes_sent(Date last_mes_sent) {
        this.last_mes_sent = last_mes_sent;
    }
}
