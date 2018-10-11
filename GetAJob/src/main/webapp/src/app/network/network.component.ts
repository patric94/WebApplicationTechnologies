import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { UserService, NetworkService } from "../_services";
import { User , UserPOJO } from "../_models";
import { FormControl } from '@angular/forms';
import { Observable } from 'rxjs';
import { map, startWith } from 'rxjs/operators';

@Component({
  selector: 'app-network',
  templateUrl: './network.component.html',
  styleUrls: ['./network.component.css']
})
export class NetworkComponent implements OnInit {
  currentUser: User;
  myControl = new FormControl();
  options: UserPOJO[] = [];
  friends: UserPOJO[] = [];
  connections: number;
  filteredOptions: Observable<UserPOJO[]>;

  constructor(private router: Router, private route: ActivatedRoute, private userService: UserService, private networkService: NetworkService) {
    this.currentUser = JSON.parse(localStorage.getItem('currentUser'));
  }

  ngOnInit() {
    this.loadOtherUsers();

    this.filteredOptions = this.myControl.valueChanges
      .pipe(
        startWith<string | UserPOJO>(''),
        map(value => typeof value === 'string' ? value : value.fullname),
        map(name => name ? this._filter(name) : this.options.slice())
      );

    this.loadAllFriends();
  }

  displayFn(user?: User): string | undefined {
    return user ? user.fullname : undefined;
  }

  private _filter(fullname: string): UserPOJO[] {
    const filterValue = fullname.toLowerCase();

    return this.options.filter(option => option.fullname.toLowerCase().indexOf(filterValue) === 0);
  }

  private loadOtherUsers() {
    this.userService.getOtherUsers(this.currentUser.username).subscribe(users => {
      this.options = users;
    });
  }

  private loadAllFriends(){
    this.networkService.getAllFriends(this.currentUser.username).subscribe(users => {
      this.friends = users;
      this.connections = this.friends.length;

    });
  }

  showProfile(){
    this.router.navigate(['/profiles']);
  }
}




