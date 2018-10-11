import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { UserService, AlertService } from "../_services";
import { User } from "../_models";
import { FormControl, FormGroup, FormBuilder, Validators } from '@angular/forms';

@Component({
  selector: 'app-settings',
  templateUrl: './settings.component.html',
  styleUrls: ['./settings.component.css']
})
export class SettingsComponent implements OnInit {
  public data: any = {};
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
  email = new FormControl('', [Validators.required, Validators.email]);

  registrationFormGroup: FormGroup;
  passwordFormGroup: FormGroup;


  getErrorMessageEmail() {
    return this.email.hasError('required') ? 'You must enter a value' :
      this.email.hasError('email') ? 'Not a valid email' :
          '';
  }

  private getUser(username: string){
    this.userService.getByUsername(username).subscribe(user => {
      this.user = user;
    });
  }

  constructor(private formBuilder: FormBuilder, private router: Router, private route: ActivatedRoute, private userService: UserService, private alertService: AlertService) {
    this.currentUser = JSON.parse(localStorage.getItem('currentUser'));


    this.passwordFormGroup = this.formBuilder.group({
      password: ['', Validators.required],
      newPassword: ['', Validators.compose([Validators.required, Validators.minLength(8)])],
      repeatPassword: ['', Validators.required]
    }, {
      validator: SettingsComponent.validate.bind(this)
    });
    this.registrationFormGroup = this.formBuilder.group({
      passwordFormGroup: this.passwordFormGroup
    });
  }

  static validate(registrationFormGroup: FormGroup) {
    let newPassword = registrationFormGroup.controls.newPassword.value;
    let repeatPassword = registrationFormGroup.controls.repeatPassword.value;

    if (repeatPassword.length <= 0) {
      return null;
    }

    if (repeatPassword !== newPassword) {
      return {
        doesMatchPassword: true
      };
    }

    return null;

  }

  ngOnInit() {
    this.getUser(this.currentUser.username);
  }

  updateEmail(newEmail: string){
    this.userService.updateEmail(this.currentUser.username, newEmail)
      .subscribe(
        data => {
          this.alertService.success('Success! Your email has been changed!', true);
          location.reload();
        },
        error => {
          this.alertService.error('Error! This email is already taken!', false);
        });
  }

  changePassword(){
    this.userService.updatePassword(this.currentUser.username, this.passwordFormGroup.value.password, this.passwordFormGroup.value.newPassword)
      .subscribe(
        data => {
          this.alertService.success('Success! Your password has been changed!', true);
          location.reload();
        },
        error => {
          this.alertService.error('Error! Incorrect current password!', false);
        });
  }
}
