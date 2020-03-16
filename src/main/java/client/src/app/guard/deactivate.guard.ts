import { Injectable } from '@angular/core';
import {CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot, UrlTree, CanDeactivate} from '@angular/router';
import { Observable } from 'rxjs';
import {ChatComponent} from "../chat/chat.component";

@Injectable({
  providedIn: 'root'
})
export class DeactivateGuard implements CanDeactivate<ChatComponent> {
  canDeactivate(component: ChatComponent) {
    return component.canDeactivate();
  }

}
