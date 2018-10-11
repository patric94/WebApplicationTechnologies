import { Component, OnInit } from '@angular/core';
import { HttpClient, HttpResponse, HttpEventType } from '@angular/common/http';
import { UploadFileService } from '../_services/index';
import { User } from '../_models/user';

@Component({
  selector: 'form-upload',
  templateUrl: './form-upload.component.html',
  styleUrls: ['./profile.component.css']
})
export class FormUploadComponent implements OnInit {

  selectedFiles: FileList;
  currentFileUpload: File;
  currentUser: User;
  progress: { percentage: number } = { percentage: 0 };

  constructor(private uploadService: UploadFileService) {
  }

  ngOnInit() {
    this.currentUser = JSON.parse(localStorage.getItem('currentUser'));
  }

  selectFile(event) {
    this.selectedFiles = event.target.files;
  }

  upload() {
    this.progress.percentage = 0;

    this.currentFileUpload = this.selectedFiles.item(0);
    this.uploadService.pushFileToStorage(this.currentFileUpload, this.currentUser.username).subscribe(event => {
      if (event.type === HttpEventType.UploadProgress) {
        this.progress.percentage = Math.round(100 * event.loaded / event.total);
      } else if (event instanceof HttpResponse) {
        console.log('File is completely uploaded!');
        location.reload();
      }
    });

    this.selectedFiles = undefined;
  }
}
