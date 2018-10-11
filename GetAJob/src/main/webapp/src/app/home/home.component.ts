import {Component, Inject, OnInit} from '@angular/core';
import {UserService, NetworkService, AlertService, TimelineService, JobService} from '../_services';
import { Post, User, Comment } from '../_models/index';
import { ActivatedRoute, Router } from '@angular/router';
import { MAT_DIALOG_DATA, MatDialog, MatDialogRef } from '@angular/material';
import {FormControl, Validators} from "@angular/forms";
import {Job, UserPOJO} from "../_models";
import {ApplicantsDialogComponent} from "../jobs/jobs.component";

@Component({
    moduleId: module.id.toString(),
    templateUrl: 'home.component.html',
    styleUrls: ['./home.component.css']
})

export class HomeComponent implements OnInit {
  user: User = {
    id: -1,
    password: "",
    email: "",
    token: "",
    url: "",
    photo_url: "",
    curr_pos: "",
    curr_inst: "",
    username : "",
    firstname : "",
    lastname : "",
    fullname : ""
  };

  currentUser: User;
  connections: number;
  public data: any = {};
  public data2: any = {};
  comment: Comment;
  post: Post;
  postList: Post[] = [];

  constructor(public dialog: MatDialog, public dialogDel: MatDialog, private networkService:NetworkService, private route: ActivatedRoute, private router: Router, private userService: UserService, private timelineService: TimelineService, private alertService: AlertService) {
    this.currentUser = JSON.parse(localStorage.getItem('currentUser'));
  }


  private getUser(username: string){
    this.userService.getByUsername(username).subscribe(user => {
      this.user = user;
    });
  }

  ngOnInit() {
    this.getUser(this.currentUser.username);
    this.loadAllFriends();
    this.getAllPosts();
  }

  private loadAllFriends(){
    this.networkService.getAllFriends(this.currentUser.username).subscribe(users => {
      this.connections = users.length;
    });
  }

  private getAllPosts(){
    this.timelineService.getAllPosts(this.currentUser.username)
      .subscribe(result => {
        this.postList =result;

        for (let entry of this.postList) {
          this.timelineService.getPostStatus(this.currentUser.username, entry.id)
            .subscribe(result => {
              entry.status = result;
            });
          this.timelineService.getLikeAmount(entry.id)
            .subscribe(result => {
              entry.likeAmount = result;
            });
        }
    });
  }

  viewLikers(id:number): void {
    let dialogRef = this.dialog.open(LikersDialogComponent, {
      width: '500px',
      data: { id: id}
    });

    dialogRef.afterClosed().subscribe(result => {
      console.log('View likers requested');
    });
  }

  openDeleteDialog(i, id): void {
    let dialogRef= this.dialogDel.open(PostsDeleteDialogComponent, {
      width: '400px'
    });

    dialogRef.afterClosed().subscribe(result => {
      // console.log(result);
      if (result == false) {
        console.log("canceled");
      }
      else{
        console.log("removed");
        this.postList.splice(i, 1);
        this.timelineService.deletePost(id)
          .subscribe(
            data => {
              this.alertService.success('Delete successful!', false);
            },
            error => {
              this.alertService.error('Delete failed!', false);
            });
      }
    });
  }

  newComment(newComment: string, post: Post){
    console.log(newComment);

    this.comment = new Comment;
    this.comment.commenter = this.currentUser.username;
    this.comment.photo_url = this.user.photo_url;
    this.comment.content = newComment;
    this.comment.post_id = post.id;

    post.comments.push(this.comment);

    this.timelineService.comment(this.comment)
      .subscribe(
        data => {
          this.alertService.success('Comment successful!', false);
        },
        error => {
          this.alertService.error('Comment failed!', false);
        });

    this.data2 = {};
  }

  newPost(newPost:string){

    this.post = new Post;
    this.post.username = this.currentUser.username;
    this.post.photo_url = this.user.photo_url;
    this.post.content = newPost;
    // this.post.likeAmount = 0;
    // this.post.status = 0;
    // this.postList.unshift(this.post);

    this.timelineService.post(this.post)
      .subscribe(
        data => {
          location.reload();
          this.alertService.success('Post successful!', false);
        },
        error => {
          this.alertService.error('Post failed!', false);
        });

    this.data = {};
  }

  like(post:Post){
    post.status = 1;
    post.likeAmount = post.likeAmount + 1;
    this.timelineService.like(this.currentUser.username, post.id)
      .subscribe(
        data => {
          // this.alertService.success('Like successful!', false);
        },
        error => {
          // this.alertService.error('Like failed!', false);
        });
  }

  navProf(username:string){
    if(username === this.currentUser.username){
      this.router.navigate(['/profile']);
    }
    else{
      this.router.navigate(['/profiles', username]);
    }
  }

}


@Component({
  moduleId: module.id.toString(),
  selector: 'likers.dialog.component',
  templateUrl: 'likers.dialog.component.html',
  styleUrls: ['home.component.css']
})

export class LikersDialogComponent {

  likersList: UserPOJO[] = [];
  currentUser: User;

  constructor(
    private router: Router,
    private route: ActivatedRoute,
    public dialogRef: MatDialogRef<LikersDialogComponent>,
    private timelineService: TimelineService,
    @Inject(MAT_DIALOG_DATA) public data: any) {
    this.currentUser = JSON.parse(localStorage.getItem('currentUser'));
  }



  ngOnInit() {
    this.timelineService.getPostLikers(this.data.id)
      .subscribe(
        data => {
          this.likersList = data;
          // this.alertService.success('', true);
        },
        error => {
          console.log(error);
          // this.alertService.error('', false);
        });
  }

  onNoClick(): void {
    this.dialogRef.close();
    console.log('View Likers exited');
  }

  closeNnav(username:string){
    this.dialogRef.close();
    if(username === this.currentUser.username){
      this.router.navigate(['/profile']);
    }
    else{
      this.router.navigate(['/profiles', username]);
    }
  }

}

@Component({
  moduleId: module.id.toString(),
  selector: 'delete.post.dialog.component',
  templateUrl: 'delete.post.dialog.component.html',
  styleUrls: ['home.component.css']
})

export class PostsDeleteDialogComponent {

  constructor(
    public dialogRef: MatDialogRef<PostsDeleteDialogComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any) { }

  ngOnInit() {
  }
}
