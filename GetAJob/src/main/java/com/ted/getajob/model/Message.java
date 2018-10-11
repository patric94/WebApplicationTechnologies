package com.ted.getajob.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ted.getajob.model.pojo.MessagePOJO;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "MESSAGE")
public class Message {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "SENDER")
    private String sender;

    @Column(name = "CONTENT")
    private String content;

    @Column(nullable = false, updatable = false)
    @CreationTimestamp
    @JsonIgnore
    private Date send_at;

    @ManyToOne
    @JoinColumn(name = "CHAT_ID", referencedColumnName = "ID")
    @JsonIgnore
    private Chat chat;

    public Message() {
    }

    public Message(String sender, String content, Chat chat) {
        this.sender = sender;
        this.content = content;
        this.chat = chat;
    }

    public MessagePOJO convertToPOJO() {
        String photo_url = this.chat.getParticipants().get(0).getUsername().equals(sender) ? this.chat.getParticipants().get(0).getPhoto_url() : this.chat.getParticipants().get(1).getPhoto_url();
        return new MessagePOJO(this.id, this.chat.getId(), this.sender, photo_url, this.content, this.send_at);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getSend_at() {
        return send_at;
    }

    public void setSend_at(Date send_at) {
        this.send_at = send_at;
    }

    public Chat getChat() {
        return chat;
    }

    public void setChat(Chat chat) {
        this.chat = chat;
    }
}
