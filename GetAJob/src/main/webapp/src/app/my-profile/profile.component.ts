import { Component, Inject, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { UserService } from "../_services";
import { Education, Job, User, UserPOJO, Work, Skill} from "../_models";
import { MAT_DIALOG_DATA, MatDialog, MatDialogRef } from '@angular/material';
import { FormControl, Validators } from "@angular/forms";
import { saveAs } from 'file-saver/FileSaver';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {

  private getUser(username: string){
    this.userService.getByUsername(username).subscribe(user => {
      this.user = user;
      console.log(user.photo_url);
    });
  }

  private getEducation(username: string){
    this.userService.getEducation(username).subscribe(data => {
      this.educationList = data;
    });
  }

  private getWork(username: string){
    this.userService.getWork(username).subscribe(data => {
      this.workList = data;
    });
  }

  private getSkills(username: string){
    this.userService.getSkills(username).subscribe(data => {
      this.skillList = data;
    });
  }

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
  name: string;
  startYear: string;
  endYear: string;
  education: Education;
  educationList: Education[] = [];
  work: Work;
  workList: Work[] = [];
  skill: Skill;
  skillList: Skill[] = [];

  constructor(public dialog: MatDialog, public dialogEdu: MatDialog,  public dialogWork: MatDialog, public dialogSkill: MatDialog,  private router: Router, private route: ActivatedRoute, private userService: UserService) {
    this.currentUser = JSON.parse(localStorage.getItem('currentUser'));
  }

  ngOnInit() {
    this.getUser(this.currentUser.username);
    this.getEducation(this.currentUser.username);
    this.getWork(this.currentUser.username);
    this.getSkills(this.currentUser.username);
  }

  //education

  makeEducationPublic(education: Education){
    console.log("Public");
    console.log(education.id);
    console.log(education.public);
    if(education.public === false){
      console.log("Make it public");
      education.public = true;
      this.userService.setEducationPublic(education.id)
        .subscribe(
          data => {
            // this.userService.success('Set public of education successful', true);
          },
          error => {
            // console.log(error);
            // this.userService.error('Set public of education failed!', false);
          });
    }
  }

  makeEducationPrivate(education: Education){
    console.log("Private");
    console.log(education.id);
    console.log(education.public);
    if(education.public === true){
      console.log("Make it private");
      education.public = false;
      this.userService.setEducationPrivate(education.id)
      .subscribe(
          data => {
            // this.userService.success('Set public of education successful', true);
          },
          error => {
            // console.log(error);
            // this.userService.error('Set public of education failed!', false);
          });
    }
  }

  addEducation(): void {
    let dialogRef = this.dialogEdu.open(EducationDialogComponent, {
      width: '450px',
      data: {name: '', startYear:'', endYear:''}
    });

    dialogRef.afterClosed().subscribe(result => {
      console.log('Add education requested');
      console.log(result);


      this.name = result.name;
      this.startYear = result.startYear;
      this.endYear = result.endYear;

      this.education = new Education();
      this.education.name = this.name;
      this.education.startYear = this.startYear;
      this.education.endYear = this.endYear;
      this.education.public = true;
      this.educationList.push(this.education);

      this.userService.addEducation(this.currentUser.username, this.education)
        .subscribe(
          data => {
            // this.userService.success('Creation of education successful', true);
          },
          error => {
            // console.log(error);
            // this.userService.error('Creation of education failed!', false);
          });
    });


  }

  //work
  makeWorkPublic(work: Work){
    console.log("Public");
    console.log(work.id);
    console.log(work.public);
    if(work.public === false){
      console.log("Make it public");
      work.public = true;
      this.userService.setWorkPublic(work.id)
        .subscribe(
          data => {
            // this.userService.success('Set public of education successful', true);
          },
          error => {
            // console.log(error);
            // this.userService.error('Set public of education failed!', false);
          });
    }
  }

  makeWorkPrivate(work: Work){
    console.log("Private");
    console.log(work.id);
    console.log(work.public);
    if(work.public === true){
      console.log("Make it private");
      work.public = false;
      this.userService.setWorkPrivate(work.id)
        .subscribe(
          data => {
            // this.userService.success('Set public of education successful', true);
          },
          error => {
            // console.log(error);
            // this.userService.error('Set public of education failed!', false);
          });
    }
  }

  addWork(): void {
    let dialogRef = this.dialogWork.open(WorkDialogComponent, {
      width: '450px',
      data: {name: '', startYear:'', endYear:''}
    });

    dialogRef.afterClosed().subscribe(result => {
      console.log('Add work requested');
      console.log(result);


      this.name = result.name;
      this.startYear = result.startYear;
      this.endYear = result.endYear;

      this.work = new Work();
      this.work.name = this.name;
      this.work.startYear = this.startYear;
      this.work.endYear = this.endYear;
      this.work.public = true;
      this.workList.push(this.work);

      this.userService.addWork(this.currentUser.username, this.work)
        .subscribe(
          data => {
            // this.userService.success('Creation of education successful', true);
          },
          error => {
            // console.log(error);
            // this.userService.error('Creation of education failed!', false);
          });
    });


  }

  //skill
  makeSkillPublic(skill: Skill){
    console.log("Public");
    console.log(skill.id);
    console.log(skill.public);
    if(skill.public === false){
      console.log("Make it public");
      skill.public = true;
      this.userService.setSkillPublic(skill.id)
        .subscribe(
          data => {
            // this.userService.success('Set public of education successful', true);
          },
          error => {
            // console.log(error);
            // this.userService.error('Set public of education failed!', false);
          });
    }
  }

  makeSkillPrivate(skill: Skill){
    console.log("Private");
    console.log(skill.id);
    console.log(skill.public);
    if(skill.public === true){
      console.log("Make it private");
      skill.public = false;
      this.userService.setSkillPrivate(skill.id)
        .subscribe(
          data => {
            // this.userService.success('Set public of education successful', true);
          },
          error => {
            // console.log(error);
            // this.userService.error('Set public of education failed!', false);
          });
    }
  }

  addSkill(): void {
    let dialogRef = this.dialogSkill.open(SkillDialogComponent, {
      width: '450px',
      data: {name: ''}
    });

    dialogRef.afterClosed().subscribe(result => {
      console.log('Add skill requested');
      console.log(result);


      this.name = result.name;

      this.skill = new Skill();
      this.skill.name = this.name;
      this.skill.public = true;
      this.skillList.push(this.skill);

      this.userService.addSkill(this.currentUser.username, this.skill)
        .subscribe(
          data => {
            // this.userService.success('Creation of education successful', true);
          },
          error => {
            // console.log(error);
            // this.userService.error('Creation of education failed!', false);
          });
    });


  }

  //upload photo
  openDialog(): void {
    let dialogRef = this.dialog.open(PhotoDialogComponent, {
      width: '550px',
      data: {photo: ''}
    });

    dialogRef.afterClosed().subscribe(result => {
      console.log('Add photo requested');
      console.log(result);
    });
  }
}


@Component({
  moduleId: module.id.toString(),
  selector: 'photo.dialog.component',
  templateUrl: 'photo.dialog.component.html',
  styleUrls: ['profile.component.css']
})

export class PhotoDialogComponent {

  constructor(
    public dialogRef: MatDialogRef<PhotoDialogComponent>,
    private userService: UserService,
    @Inject(MAT_DIALOG_DATA) public data: any) { }

  ngOnInit() {

  }

  onNoClick(): void {
    this.dialogRef.close();
    console.log('Add photo cancelled');
  }

}

//education
@Component({
  moduleId: module.id.toString(),
  selector: 'education.dialog.component',
  templateUrl: 'education.dialog.component.html',
  styleUrls: ['profile.component.css']
})

export class EducationDialogComponent {
  name = new FormControl('', [Validators.required]);
  startYear = new FormControl('', [Validators.required]);
  endYear = new FormControl('', [Validators.required]);


  getErrorMessageName() {
    return this.name.hasError('required') ? 'Please fill out this field' :
      '';
  }

  getErrorMessageStartYear() {
    return this.startYear.hasError('required') ? 'Please fill out this field' :
      '';
  }

  getErrorMessageEndYear() {
    return this.endYear.hasError('required') ? 'Please fill out this field' :
      '';
  }

  constructor(
    public dialogRef: MatDialogRef<EducationDialogComponent>,
    private userService: UserService,
    @Inject(MAT_DIALOG_DATA) public data: any) { }

  ngOnInit() {

  }

  onNoClick(): void {
    this.dialogRef.close();
    console.log('Add education cancelled');
  }

}

//work
@Component({
  moduleId: module.id.toString(),
  selector: 'work.dialog.component',
  templateUrl: 'work.dialog.component.html',
  styleUrls: ['profile.component.css']
})

export class WorkDialogComponent {
  name = new FormControl('', [Validators.required]);
  startYear = new FormControl('', [Validators.required]);
  endYear = new FormControl('', [Validators.required]);


  getErrorMessageName() {
    return this.name.hasError('required') ? 'Please fill out this field' :
      '';
  }

  getErrorMessageStartYear() {
    return this.startYear.hasError('required') ? 'Please fill out this field' :
      '';
  }

  getErrorMessageEndYear() {
    return this.endYear.hasError('required') ? 'Please fill out this field' :
      '';
  }

  constructor(
    public dialogRef: MatDialogRef<WorkDialogComponent>,
    private userService: UserService,
    @Inject(MAT_DIALOG_DATA) public data: any) { }

  ngOnInit() {

  }

  onNoClick(): void {
    this.dialogRef.close();
    console.log('Add work cancelled');
  }

}

//skill
//education
@Component({
  moduleId: module.id.toString(),
  selector: 'skill.dialog.component',
  templateUrl: 'skill.dialog.component.html',
  styleUrls: ['profile.component.css']
})

export class SkillDialogComponent {
  name = new FormControl('', [Validators.required]);

  getErrorMessageName() {
    return this.name.hasError('required') ? 'Please fill out this field' :
      '';
  }

  constructor(
    public dialogRef: MatDialogRef<SkillDialogComponent>,
    private userService: UserService,
    @Inject(MAT_DIALOG_DATA) public data: any) { }

  ngOnInit() {

  }

  onNoClick(): void {
    this.dialogRef.close();
    console.log('Add skill cancelled');
  }

}
