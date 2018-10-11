import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

import { Chat, UserPOJO, Message } from '../_models/index';

@Injectable()
export class MessagingService {
  constructor(private http: HttpClient) {
  }

  getAllChats(username: string) {
    return this.http.get<Chat[]>('/api/get/user/chats/' + username);
  }

  send(message: Message){
    return this.http.post('/api/send/message', message);
  }

  loadChat(chat:Chat){
    return this.http.get<Message[]>('/api/show/chat/messages/' + chat.id)
  }
}
