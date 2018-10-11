import { Component, OnInit, ViewEncapsulation, Inject } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialog, MatDialogRef } from '@angular/material';
import { Router, ActivatedRoute } from '@angular/router';
import { UserService, AlertService, MessagingService } from "../_services";
import {User, Job, UserPOJO, Chat, Post, Comment, Message} from "../_models";
import { FormControl, Validators } from "@angular/forms";


@Component({
  selector: 'app-messaging',
  templateUrl: './messaging.component.html',
  styleUrls: ['./messaging.component.css'],
  encapsulation: ViewEncapsulation.None
})
export class MessagingComponent implements OnInit {
  currentUser: User;
  chat: Chat;
  message: Message;
  messageList: Message[] = [];

  currentlyViewing: Chat = {
    id: -1,
    view_username: "",
    view_fullname: "",
    view_photo_url: "",
    user_one: this.currentUser,
    user_two: this.currentUser
  };

  user: User = {
    id: -1,
    password: "",
    email: "",
    token: "",
    url: "",
    photo_url: "",
    curr_pos: "",
    curr_inst: "",
    username : "",
    firstname : "",
    lastname : "",
    fullname : ""
  };

  chatList: Chat[] = [];
  public data: any = {};

  constructor(public dialog: MatDialog, private messagingService: MessagingService, private alertService: AlertService, private router: Router, private route: ActivatedRoute, private userService: UserService) {
    this.currentUser = JSON.parse(localStorage.getItem('currentUser'));
  }

  ngOnInit() {
    this.getUser(this.currentUser.username);
    this.getAllChats();
  }

  private getUser(username: string){
    this.userService.getByUsername(username).subscribe(user => {
      this.user = user;
    });
  }

  private getAllChats(){
    this.messagingService.getAllChats(this.currentUser.username).subscribe(data => {
      this.chatList = data;
      this.currentlyViewing = this.chatList[0];
      this.messagingService.loadChat(this.currentlyViewing)
        .subscribe(
          data => {
            // this.alertService.success('Chat loaded!', false);
            this.messageList = data;
          },
          error => {
            // this.alertService.error('Chat failed!', false);
          });

      for(let entry of this.chatList){
        if(entry.user_two.username == this.currentUser.username){
          entry.view_username = entry.user_one.username;
          entry.view_fullname = entry.user_one.fullname;
          entry.view_photo_url = entry.user_one.photo_url;
        }
        else{
          entry.view_username = entry.user_two.username;
          entry.view_fullname = entry.user_two.fullname;
          entry.view_photo_url = entry.user_two.photo_url;
        }
      }
    });
  }

  onRowClicked(chat: Chat){
      this.currentlyViewing = chat;
      this.loadMessages(this.currentlyViewing);
  }

  loadMessages(chat: Chat){
    this.messagingService.loadChat(chat)
      .subscribe(
        data => {
          // this.alertService.success('Chat loaded!', false);
          this.messageList = data;
        },
        error => {
          // this.alertService.error('Chat failed!', false);
        });

  }

  newMessage(newMessage: string, chat: Chat){
    this.message = new Message;
    this.message.sender = this.currentUser.username;
    this.message.photo_url = this.user.photo_url;
    this.message.content = newMessage;
    this.message.chat_id = chat.id;

    this.messageList.push(this.message);

    this.messagingService.send(this.message)
      .subscribe(
        data => {
          this.alertService.success('Message sent!', false);
        },
        error => {
          this.alertService.error('Message failed!', false);
        });

    this.data = {};
  }

  navProf(username:string){
    if(username === this.currentUser.username){
      this.router.navigate(['/profile']);
    }
    else{
      this.router.navigate(['/profiles', username]);
    }
  }
}
