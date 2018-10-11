package com.ted.getajob.controller;

import com.ted.getajob.model.pojo.ChatPOJO;
import com.ted.getajob.model.pojo.MessagePOJO;
import com.ted.getajob.service.ChatService;
import com.ted.getajob.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ChatRestController {
    @Autowired
    private ChatService chatService;

    @Autowired
    private UserService userService;

    @GetMapping("/api/get/user/chats/{username}")
    @PreAuthorize("hasRole('USER')")
    public ChatPOJO[] getUserChats(@PathVariable String username) {
        return chatService.getUserChats(userService.getUserByUsername(username));
    }

    @PostMapping("/api/send/message")
    @PreAuthorize("hasRole('USER')")
    @Transactional
    public void sendMessageToChat(@RequestBody MessagePOJO messagePOJO) {
        chatService.sendMessageToChat(messagePOJO);
    }

    @GetMapping("/api/show/chat/messages/{id}")
    @PreAuthorize("hasRole('USER')")
    public List<MessagePOJO> showChatMessages(@PathVariable Long id) {
        return chatService.showChatMessages(id);
    }
}
