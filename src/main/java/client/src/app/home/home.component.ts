import { Component, OnInit } from '@angular/core';
import {User} from "../user";
import {UserService} from "../service/user.service";
import {first} from "rxjs/operators";
import {AuthService} from "../service/auth.service";

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  currentUser: User;

  constructor(
    private userService: UserService,
    private authenticationService: AuthService
  ) {
    this.currentUser = this.authenticationService.currentUserValue;
  }

  ngOnInit() {

  }
}
