import { Component, OnInit, ViewEncapsulation } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import {UserService, NotificationService, AlertService} from "../_services";
import {FriendRequest, User} from "../_models";
import { FormControl } from '@angular/forms';
import { Observable } from 'rxjs';
import { map, startWith } from 'rxjs/operators';
import { LikeNotification, CommentNotification } from "../_models/index";

@Component({
  selector: 'app-notifications',
  templateUrl: './notifications.component.html',
  styleUrls: ['./notifications.component.css'],
  encapsulation: ViewEncapsulation.None
})
export class NotificationsComponent implements OnInit {
  currentUser: User;
  friendRequestList: FriendRequest[] = [];
  likeNotificationList: LikeNotification[] = [];
  commentNotificationList: CommentNotification[] = [];

  constructor(private alertService: AlertService, private router: Router, private route: ActivatedRoute, private userService: UserService, private notificationService: NotificationService) {
  }

  private getAllFriendRequests(username: string) {
    this.notificationService.getAllFriendRequests(username).subscribe(users => {
      this.friendRequestList = users;
    });
  }

  private getAllLikeNotifications(username: string) {
    this.notificationService.getAllLikeNotifications(username).subscribe(result => {
      this.likeNotificationList = result;
    });
  }

  private getAllCommentNotifications(username: string) {
    this.notificationService.getAllCommentNotifications(username).subscribe(result => {
      this.commentNotificationList = result;
    });
  }

  ngOnInit() {
    this.currentUser = JSON.parse(localStorage.getItem('currentUser'));
    this.getAllFriendRequests(this.currentUser.username);
    this.getAllLikeNotifications(this.currentUser.username);
    this.getAllCommentNotifications(this.currentUser.username);
  }

  accept(i:number, accepted_username:string){
    this.friendRequestList.splice(i, 1);

    this.notificationService.acceptFriend(this.currentUser.username, accepted_username)
        .subscribe(
          data => {
            this.alertService.success('Connection successful!', true);
            this.router.navigate(['/network']);
          },
          error => {
            this.alertService.error('Connection failed!', false);
          });


  }

  decline(i:number, accepted_username:string){
    this.friendRequestList.splice(i, 1);

    this.notificationService.declineFriend(this.currentUser.username, accepted_username)
      .subscribe(
        data => {
          this.alertService.success('You have declined this connection!', false);
        },
        error => {
          this.alertService.error('Decline failed!', false);
        });
  }

  viewLike(id:number, i:number){
    this.likeNotificationList.splice(i, 1);
    this.notificationService.viewLike(id)
      .subscribe(
        data => {
          // this.alertService.success('You have declined this connection!', false);
        },
        error => {
          // this.alertService.error('Decline failed!', false);
        });
  }

  viewComment(id:number, i:number){
    this.commentNotificationList.splice(i, 1);
    this.notificationService.viewComment(id)
      .subscribe(
        data => {
          // this.alertService.success('You have declined this connection!', false);
        },
        error => {
          // this.alertService.error('Decline failed!', false);
        });
  }

  navProf(username: string){
    if(username === this.currentUser.username){
      this.router.navigate(['/profile']);
    }
    else{
      this.router.navigate(['/profiles', username]);
    }
  }
}









