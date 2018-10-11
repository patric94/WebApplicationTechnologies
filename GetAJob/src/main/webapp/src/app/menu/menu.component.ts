import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { User } from '../_models/index';
import { NotificationService, UserService } from '../_services/index';

@Component({
  moduleId: module.id.toString(),
  selector: 'app-menu',
  templateUrl: './menu.component.html',
  styleUrls: ['./menu.component.css']
})

export class MenuComponent implements OnInit{
  currentUser: User;
  notifications: number;

  constructor(private router: Router, private userService: UserService, private notificationService: NotificationService) {
    this.currentUser = JSON.parse(localStorage.getItem('currentUser'));
  }

  ngOnInit() {
    this.getNotificationNumber();
  }

  private getNotificationNumber(){
    this.notificationService.getNotificationNumber(this.currentUser.username).subscribe(data => {
      this.notifications = data;
      // console.log(this.notifications);
    });
  }

  navHome(){
    this.router.navigate(['']);
  }
}
