import { Injectable, Injector } from '@angular/core';
import {HttpInterceptor} from '@angular/common/http';
import { HttpRequest } from '@angular/common/http';
import { HttpHandler } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { HttpEvent } from '@angular/common/http';

@Injectable()
export class HInterceptorService implements HttpInterceptor{

  
  constructor(private injector: Injector) { }   //u svaki zahtev postavo heder na aplication json

  intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>>{
    if (localStorage.getItem('token') != null) {
      request = request.clone({
        setHeaders: {
          //'Content-Type': 'application/json',
          'Authorization': `Bearer ${localStorage.getItem('token')}`
        }
      });
    } else {
      request = request.clone({});
    }

    return next.handle(request);
  }

}