import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { AlertService, AuthenticationService } from '../../_services/index';


@Component({
    moduleId: module.id.toString(),
    templateUrl: 'login.component.html',
    styleUrls: ['../user.component.css']
})

export class LoginComponent implements OnInit {
    model: any = {};
    headers: any = {};
    loading = false;
    // returnUrl: string;

    constructor(
        private route: ActivatedRoute,
        private router: Router,
        private authenticationService: AuthenticationService,
        private alertService: AlertService) { }

    ngOnInit() {
        // reset login status
        this.authenticationService.logout();

        // // get return url from route parameters or default to '/'
        // this.returnUrl = this.route.snapshot.queryParams['returnUrl'] || '/';
    }
    loginFunc() {
        this.loading = true;
        this.authenticationService.login(this.model.username, this.model.password)
            .subscribe(
                data => {
                    console.log(data.url);
                    this.router.navigate([data.url]);
                },
                error => {
                    console.log(error.error);
                    this.alertService.error('Login failed! ', false);
                    this.loading = false;
                });
    }
}
