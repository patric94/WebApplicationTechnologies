import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { UserPOJO } from "../_models";

@Injectable()
export class NetworkService {
  constructor(private http: HttpClient) { }

  connect(sender: string, receiver:string){
    return this.http.post('/api/friend/request/' + sender + '/' + receiver, '');
  }

  getAllFriends(username: string){
    return this.http.get<UserPOJO[]>('/api/friend/list/' + username);
  }

  getStatus(current: string, username: string){
    return this.http.get<number>('/api/relationship/status/' + current + '/' + username);
  }
}
