import { Injectable } from '@angular/core';
import { HttpClient, HttpRequest, HttpEvent } from '@angular/common/http';

import { Observable } from 'rxjs/Observable';

import { Chat, UserPOJO, Message } from '../_models/index';

@Injectable()
export class UploadFileService {
  constructor(private http: HttpClient) {
  }

  pushFileToStorage(file: File, username:string): Observable<HttpEvent<{}>> {
    const formdata: FormData = new FormData();

    formdata.append('file', file);

    const req = new HttpRequest('PUT', '/api/upload/profile/photo/' + username, formdata, {
      reportProgress: true,
      responseType: 'text'
    });

    return this.http.request(req);
  }
}
