import {Component, OnDestroy, OnInit} from '@angular/core';
import * as Stomp from 'stompjs';
import * as SockJS from 'sockjs-client';
import $ from 'jquery';
import {AuthService} from "../service/auth.service";

@Component({
  selector: 'app-chat',
  templateUrl: './chat.component.html',
  styleUrls: ['./chat.component.css']
})
export class ChatComponent implements OnInit {
  private serverUrl = 'http://34.89.232.247:8080/spring-websocket/socket';
  message = '';
  public username: string;
  private stompClient;
  private unsub;

  constructor(private authService: AuthService) {
  }

  initializeWebSocketConnection() {
    let ws = new SockJS(this.serverUrl);
    this.stompClient = Stomp.over(ws);
    let that = this;
    this.stompClient.connect({}, function(frame) {
      that.unsub = that.stompClient.subscribe("/chat", (message) => {
        if (message.body) {
          $(".chat").append("<div class='message'>" +
            "<p>" + message.body + "</p>" +
            "</div>");
        }
      });
    });
  }

  sendMessage() {
    this.stompClient.send("/app/send/message", {}, this.username + ": " + this.message);
    $('#input').val('');
    this.message = '';
  }

  ngOnInit(): void {
    this.username = this.authService.currentUserValue.username;
    this.initializeWebSocketConnection();
  }

  canDeactivate() {
    this.unsub.unsubscribe();
    console.log('unsubscribed');
    return true;
  }
}
