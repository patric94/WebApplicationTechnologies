import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

import { Post, UserPOJO, Comment} from '../_models/index';

@Injectable()
export class TimelineService {
  constructor(private http: HttpClient) { }

  post(post:Post) {
    var create  = {
      'username': post.username,
      'content': post.content,
    };
    return this.http.post('/api/add/post', create);
  }

  getAllPosts(username: string){
    return this.http.get<Post[]>('/api/get/sorted/posts/' + username);
  }

  like(username: string, id:number){
    return this.http.post('/api/like/post/' + id + '/' + username, '');
  }

  getUserPosts(username: string){
    return this.http.get<Post[]>('/api/get/posts/' + username);
  }

  getPostStatus(username: string, id:number) {
    return this.http.get<number>('/api/like/status/post/' + id + '/' + username);

  }

  comment(comment: Comment){
    var create  = {
      'commenter': comment.commenter,
      'content': comment.content,
    };
    return this.http.post('/api/comment/post/' + comment.post_id , create);
  }

  getLikeAmount(id:number){
    return this.http.get<number>('/api/get/amount/like/post/' + id );
  }

  getPostLikers(id:number){
    return this.http.get<UserPOJO[]>('/api/get/like/users/post/' + id );
  }

  deletePost(id:number){
    return this.http.delete('/api/delete/post/' + id );
  }
}

