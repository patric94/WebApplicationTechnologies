package com.ted.getajob.service;

import com.ted.getajob.model.Chat;
import com.ted.getajob.model.Message;
import com.ted.getajob.model.UserModel;
import com.ted.getajob.model.pojo.ChatPOJO;
import com.ted.getajob.model.pojo.MessagePOJO;
import com.ted.getajob.repository.ChatRepository;
import com.ted.getajob.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class ChatService {
    @Autowired
    private ChatRepository chatRepository;

    @Autowired
    private MessageRepository messageRepository;

    public Chat createChat(UserModel user_one, UserModel user_two) {
        List<UserModel> participants = new ArrayList<>();
        participants.add(user_one);
        participants.add(user_two);
        Chat chat = new Chat(participants);
        chatRepository.save(chat);
        return chat;
    }

    public ChatPOJO[] getUserChats(UserModel user) {
        Chat[] chats = user.getChats().toArray(new Chat[0]);
        for (int i = 1; i < chats.length; ++i) {
            Chat key = chats[i];
            int j = i-1;
            while (j >= 0 && chats[j].getLast_mes_sent().before(key.getLast_mes_sent())) {
                chats[j+1] = chats[j];
                j--;
            }
            chats[j+1] = key;
        }
        ChatPOJO[] chatPOJOS = new ChatPOJO[chats.length];
        for (int i = 0; i < chats.length; i++) {
            chatPOJOS[i] = chats[i].convertToPOJO();
        }
        return chatPOJOS;
    }

    @Transactional
    public void sendMessageToChat(MessagePOJO messagePOJO) {
        Chat chat = chatRepository.getOne(messagePOJO.getChat_id());
        Message message = new Message(messagePOJO.getSender(), messagePOJO.getContent(), chat);
        chat.getMessages().add(message);
        messageRepository.save(message);
        chat.setLast_mes_sent(message.getSend_at());
    }

    public List<MessagePOJO> showChatMessages(Long id) {
        Chat chat = chatRepository.getOne(id);
        List<MessagePOJO> retList = new ArrayList<>();
        for (Message message : chat.getMessages()) {
            retList.add(message.convertToPOJO());
        }
        return retList;
    }
}
