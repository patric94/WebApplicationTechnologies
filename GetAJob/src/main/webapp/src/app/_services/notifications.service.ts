import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import {User, LikeNotification, CommentNotification, FriendRequest} from "../_models";
import { ActivatedRoute, Router } from "@angular/router";

@Injectable()
export class NotificationService {
  currentUser: User;
  constructor(private http: HttpClient, private route: ActivatedRoute, private router: Router) {
    this.currentUser = JSON.parse(localStorage.getItem('currentUser'));
  }

  getAllFriendRequests(username:string){
    return this.http.get<FriendRequest[]>('/api/friend/notification/' + username);
  }

  acceptFriend(my_username:string, accepted_username:string){
    return this.http.put('/api/friend/accept/' + my_username + '/' + accepted_username, '');
  }

  declineFriend(my_username:string, accepted_username:string){
    return this.http.delete('/api/friend/decline/' + my_username + '/' + accepted_username);
  }

  getNotificationNumber(username: string){
    return this.http.get<number>('/api/notifications/amount/' + username);
  }

  viewLike(id:number){
    return this.http.delete('/api/delete/post/notification/' + id);
  }

  viewComment(id:number){
    return this.http.delete('/api/delete/post/notification/' + id);
  }

  getAllLikeNotifications(username: string){
    return this.http.get<LikeNotification[]>('/api/get/like/notification/' + username);
  }

  getAllCommentNotifications(username: string){
    return this.http.get<CommentNotification[]>('/api/get/comment/notification/' + username);
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
