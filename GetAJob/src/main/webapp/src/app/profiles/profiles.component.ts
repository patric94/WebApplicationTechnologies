import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import {AlertService, NetworkService, UserService} from "../_services";
import { User, UserPOJO, Work, Skill, Education } from "../_models";

@Component({
  selector: 'app-profiles',
  templateUrl: './profiles.component.html',
  styleUrls: ['./profiles.component.css']
})
export class ProfilesComponent implements OnInit {
  user: UserPOJO = {
    username : "",
    firstname : "",
    lastname : "",
    photo_url : "",
    curr_pos : "",
    curr_inst : "",
    fullname : ""};

  username: string;
  currentUser: User;
  education: Education;
  educationList: Education[] = [];
  work: Work;
  workList: Work[] = [];
  skill: Skill;
  skillList: Skill[] = [];
  status: number;
  private sub: any;

  constructor(private alertService: AlertService, private router: Router, private route: ActivatedRoute, private userService: UserService, private networkService: NetworkService) {
    this.currentUser = JSON.parse(localStorage.getItem('currentUser'));
  }

  ngOnInit() {
    this.sub = this.route.params.subscribe(params => {
      this.username = params['username'];
    });

    this.loadUser();
    this.getStatus();
  }

  private loadUser() {
    this.userService.loadUser(this.username).subscribe(user => {
      this.user = user;
    });
  }

  private getStatus(){
    this.networkService.getStatus(this.currentUser.username, this.username).subscribe(data => {
      // console.log(data);
      this.status = data;
      console.log(this.status);

          if(this.status == 1){  //friends can see everything
            this.userService.getEducation(this.username).subscribe(data => {
              this.educationList = data;
            });
          }
          else{ //not friends; get only public info
            this.userService.getPublicEducation(this.username).subscribe(data => {
              this.educationList = data;
            });
          }


          if(this.status == 1){  //friends can see everything
            this.userService.getWork(this.username).subscribe(data => {
              this.workList = data;
            });
          }
          else{ //not friends; get only public info
            this.userService.getPublicWork(this.username).subscribe(data => {
              this.workList = data;
            });
          }

          if(this.status == 1){  //friends can see everything
            this.userService.getSkills(this.username).subscribe(data => {
              this.skillList = data;
            });
          }
          else{ //not friends; get only public info
            this.userService.getPublicSkills(this.username).subscribe(data => {
              this.skillList = data;
            });
          }
    });
  }


  connect(){
    this.networkService.connect(this.currentUser.username, this.username)
      .subscribe(
        data => {
          console.log("FriendEntity request sent");
          this.status = 0;
          this.alertService.success('Request to connect sent!', true);
        },
        error => {
          this.alertService.error('Request to connect failed!', false);
          console.log("FriendEntity request failed");
        });
  }
}


