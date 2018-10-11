import { Component, OnInit, ViewEncapsulation, Inject } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialog, MatDialogRef } from '@angular/material';
import { Router, ActivatedRoute } from '@angular/router';
import { UserService, AlertService, JobService } from "../_services";
import {User, Job, UserPOJO} from "../_models";
import { FormControl, Validators } from "@angular/forms";


@Component({
  selector: 'app-jobs',
  templateUrl: './jobs.component.html',
  styleUrls: ['./jobs.component.css'],
  encapsulation: ViewEncapsulation.None
})
export class JobsComponent implements OnInit {
  currentUser: User;
  title: string;
  description: string;
  job: Job;
  jobList: Job[] = [];
  friendJobList: Job[] = [];
  model: any = {};

  constructor(public dialog: MatDialog, public dialogApplicants: MatDialog, private jobService: JobService, private alertService: AlertService, private router: Router, private route: ActivatedRoute, private userService: UserService) {
  }

  ngOnInit() {
    this.currentUser = JSON.parse(localStorage.getItem('currentUser'));
    this.loadMyJobs();
    this.loadFriendJobs();
  }

  private loadFriendJobs(){
    this.jobService.getFriendJobs(this.currentUser.username)
      .subscribe(friendJobList => {
        this.friendJobList = friendJobList;

        for (let entry of friendJobList) {
          this.jobService.getFriendJobsStatus(this.currentUser.username, entry.id)
            .subscribe(result => {
             entry.status = result;
            });
        }
      });

  }
  private loadMyJobs() {
    this.jobService.getMyJobs(this.currentUser.username).subscribe(jobList => { this.jobList = jobList; });
  }

  apply(job:Job){
    this.jobService.applyJob(this.currentUser.username, job.id)
      .subscribe(
        data => {
          job.status = 1;
          this.alertService.success('You applied for the job!', true);
        },
        error => {
          this.alertService.error('Apply request failed!', false);
        });
  }

  openDialog(): void {
    let dialogRef = this.dialog.open(JobsDialogComponent, {
      width: '500px',
      data: { title: '', description: ''}
    });

    dialogRef.afterClosed().subscribe(result => {
      console.log('Add job requested');

      this.title = result.title;
      this.description = result.description;

      this.job = new Job;
      this.job.title = this.title;
      this.job.description = this.description;
      this.jobList.push(this.job);


      //post
      // this.model.title = this.job.title;
      // this.model.description = this.job.description;


      this.jobService.create(this.currentUser.username, this.job)
        .subscribe(
          data => {
            this.alertService.success('Creation of job successful', true);
          },
          error => {
            console.log(error);
            this.alertService.error('Creation of job failed!', false);
          });
    });

    this.title = '';
    this.description = '';
  }

  viewApplicants(id:number): void {
    let dialogRef = this.dialogApplicants.open(ApplicantsDialogComponent, {
      width: '500px',
      data: { id: id}
    });

    dialogRef.afterClosed().subscribe(result => {
      console.log('View applicants requested');
    });
  }
}


@Component({
  moduleId: module.id.toString(),
  selector: 'jobs.dialog.component',
  templateUrl: 'jobs.dialog.component.html',
  styleUrls: ['jobs.component.css']
})

export class JobsDialogComponent {
  title = new FormControl('', [Validators.required]);
  description = new FormControl('', [Validators.required]);


  getErrorMessageTitle() {
    return this.title.hasError('required') ? 'Please fill out this field' :
      '';
  }

  getErrorMessageDescription() {
    return this.description.hasError('required') ? 'Please fill out this field' :
      '';
  }

  constructor(
    public dialogRef: MatDialogRef<JobsDialogComponent>,
    private userService: UserService,
    @Inject(MAT_DIALOG_DATA) public data: any) { }

  ngOnInit() {

  }

  onNoClick(): void {
    this.dialogRef.close();
    console.log('Add job cancelled');
  }

}

@Component({
  moduleId: module.id.toString(),
  selector: 'applicants.dialog.component',
  templateUrl: 'applicants.dialog.component.html',
  styleUrls: ['jobs.component.css']
})

export class ApplicantsDialogComponent {

  applicantList: UserPOJO[] = [];

  constructor(
    private router: Router,
    private route: ActivatedRoute,
    public dialogRef: MatDialogRef<ApplicantsDialogComponent>,
    private jobService: JobService,
    @Inject(MAT_DIALOG_DATA) public data: any) { }



  ngOnInit() {
    this.jobService.getJobApplicants(this.data.id)
      .subscribe(
        data => {
          this.applicantList = data;
          // this.alertService.success('', true);
        },
        error => {
          console.log(error);
          // this.alertService.error('', false);
        });
  }

  onNoClick(): void {
    this.dialogRef.close();
    console.log('View Applicants exited');
  }

  closeNnav(username:string){
    this.dialogRef.close();
    this.router.navigate(['/profiles', username]);
  }

}






