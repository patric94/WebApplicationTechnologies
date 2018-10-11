import { Component } from '@angular/core';
import { Router } from '@angular/router';

import { AlertService, UserService } from '../../_services/index';

@Component({
    moduleId: module.id.toString(),
    templateUrl: 'register.component.html',
    styleUrls: ['../user.component.css']
})

export class RegisterComponent {
    model: any = {};
    loading = false;
    emailPattern = '^[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,4}$' ;
    hide = true;

    constructor(
        private router: Router,
        private userService: UserService,
        private alertService: AlertService) { }

    register() {
        // this.model.url = "/home/melina/Downloads/prof.jpg";
        this.loading = true;
        this.userService.create(this.model)
            .subscribe(
                data => {
                    this.alertService.success('Registration successful!', true);
                    this.router.navigate(['/login']);
                },
                error => {
                    this.alertService.error('Registration failed! ' + error.error, false);
                    this.loading = false;
                });
    }
}
