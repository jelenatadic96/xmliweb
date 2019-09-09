import { Injectable } from '@angular/core';
import { HttpHeaders, HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { Router } from '@angular/router';

@Injectable()
export class AuthService {

  constructor(private http: HttpClient,
    private router: Router) { }

    getRoles(token: string) {
      let jwtData = token.split('.')[1];
      let decodedJwtJsonData = window.atob(jwtData);
      let decodedJwtData = JSON.parse(decodedJwtJsonData);
      //console.log(decodedJwtData);
      //console.log(decodedJwtData.auth[0].authority);
      //console.log(decodedJwtData.sub); //username
      return decodedJwtData.auth[0].authority; //you can access role or username
    }
  
    getUsername(token: string) : string{
      let jwtData = token.split('.')[1];
      let decodedJwtJsonData = window.atob(jwtData);
      let decodedJwtData = JSON.parse(decodedJwtJsonData);
      //console.log(decodedJwtData);
      //console.log(decodedJwtData.auth[0].authority);
      //console.log(decodedJwtData.sub); //username
      return decodedJwtData.sub; //you can access role or username
    }
  
    login(mejl:string, lozinka:string): Observable<string>{
      const headers = new HttpHeaders().set('Content-Type', 'text/plain; charset=utf-8');
      return this.http
      .post<string>('/login', {mejl, lozinka});
    }

   /* signup(registration: Registration): Observable<string>{
      return this.http
        .post<string>('/api/signup', registration);
    }

    registerAdmin(id: number, registration: Registration): Observable<string>{
      return this.http
        .post<string>(`/api/register/${id}`, registration);
    }*/

    logout(): void {
      localStorage.removeItem('token');
      localStorage.removeItem('newAccommodation');
      this.router.navigate(['login']);
    }

}