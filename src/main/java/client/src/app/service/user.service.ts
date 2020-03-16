import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {User} from "../user";

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private url = 'http://34.89.232.247:8080/spring-websocket/';
  private getUserUrl = this.url + '/user';

  constructor(private http: HttpClient) { }

  public getUser(): Observable<User> {
    return this.http.get<User>(this.getUserUrl);
  }
}
