package com.ted.getajob.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ted.getajob.model.pojo.ChatPOJO;
import com.ted.getajob.model.pojo.MessagePOJO;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "CHAT")
public class Chat {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "LAST_MES_SENT")
    @CreationTimestamp
    private Date last_mes_sent;

    @ManyToMany(mappedBy = "chats", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnore
    private List<UserModel> participants;

    @OneToMany(mappedBy = "chat", cascade = CascadeType.ALL)
    private List<Message> messages = new ArrayList<>();

    public Chat() {
    }

    public Chat(List<UserModel> participants) {
        this.participants = participants;
    }

    public Chat(List<UserModel> participants, ArrayList<Message> messages) {
        this.participants = participants;
        this.messages = messages;
    }

    public ChatPOJO convertToPOJO() {
        return new ChatPOJO(this.id, this.participants.get(0).convertToPOJO(), this.participants.get(1).convertToPOJO(), this.last_mes_sent);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getLast_mes_sent() {
        return last_mes_sent;
    }

    public void setLast_mes_sent(Date last_mes_setn) {
        this.last_mes_sent = last_mes_setn;
    }

    public List<UserModel> getParticipants() {
        return participants;
    }

    public void setParticipants(List<UserModel> participants) {
        this.participants = participants;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }
}
