import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

import { Job, UserPOJO } from '../_models/index';

@Injectable()
export class JobService {
  constructor(private http: HttpClient) { }

  getMyJobs(username: string){
    return this.http.get<Job[]>('/api/get/jobs/' + username);
  }

  create(username: string, job: Job) {
    return this.http.post('/api/post/job/' + username, job);
  }

  getFriendJobs(username: string){
    return this.http.get<Job[]>('/api/get/friend/jobs/' + username);
  }

  applyJob(username: string, id:number){
    return this.http.post('/api/apply/job/' + id + '/' + username, '');
  }

  getFriendJobsStatus(username: string, id:number){
    return this.http.get<number>('/api/get/applied/to/job/' + id + '/' + username);
  }

  getJobApplicants(id:number){
    return this.http.get<UserPOJO[]>('/api/get/job/applicants/' + id);
  }
}
