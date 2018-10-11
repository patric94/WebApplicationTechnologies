import { Component, OnInit } from '@angular/core';
import { UserService, AlertService } from '../_services';
import { User, UserPOJO, UserAdminPOJO } from '../_models/index';
import { Router } from '@angular/router';
import { SelectionModel } from '@angular/cdk/collections';
import { MatTableDataSource } from '@angular/material';

@Component({
  moduleId: module.id.toString(),
  templateUrl: './adminHome.component.html',
  styleUrls: ['./adminHome.component.css']
})

export class AdminHomeComponent implements OnInit {
  private loadAllUsers() {
    this.userService.getAll().subscribe(users => {
      this.users = users;

      for(let entry of this.users){
        entry.checked = false;
      }
    });
  }

  currentUser: User;
  users: UserAdminPOJO[] = [];
  exportList: UserAdminPOJO[] = [];

  constructor(private router: Router, private userService: UserService, private alertService: AlertService) {
    this.currentUser = JSON.parse(localStorage.getItem('currentUser'));
  }

  ngOnInit() {
    this.loadAllUsers();
  }

  export(){
    for(let entry of this.users){
      if(entry.checked == true){
        console.log(entry);
        this.exportList.push(entry);

        this.userService.exportXML(this.exportList).subscribe(
          data => {
            this.alertService.success('Export successful!', false);
          },
          error => {
            this.alertService.error('Export failed!', false);
          });
      }
    }
  }

  exportAll(){

    this.userService.exportAllXML().subscribe(
      data => {
        this.alertService.success('Export successful!', false);
      },
      error => {
        this.alertService.error('Export failed!', false);
      });
  }

  ban(user:UserAdminPOJO){
    this.userService.ban(user).subscribe(
      data => {
        this.alertService.success('Ban successful!', false);
      },
      error => {
        this.alertService.error('Ban failed!', false);
      });
  }
}

