import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Router } from '@angular/router';
import { Observable } from 'rxjs/Observable';
import { environment } from 'environments/environment';
import { Registracija } from 'app/model/korisnik/registracija';


@Injectable()
export class AuthService {
  baseUrl: string = environment.baseUrl + '/korisnici-service';

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
        .post<string>(this.baseUrl + '/rest/korisnici/login', {mejl, lozinka});
    }

    signup(registration: Registracija): Observable<{}>{
      return this.http
        .post<{}>(this.baseUrl + '/rest/korisnici/signup', registration);
    }

    signupAgent(registration: Registracija): Observable<{}>{
      return this.http
        .post<{}>(this.baseUrl + '/rest/agenti', registration);
    }

    registerAdmin(id: number, registration: Registracija): Observable<string>{
      return this.http
        .post<string>(this.baseUrl + `/users/register/${id}`, registration);
    }

    logout(): void {
      localStorage.removeItem('token');
      localStorage.removeItem('reservation');
      localStorage.removeItem('beginDate');
      localStorage.removeItem('endDate');
      this.router.navigate(['login']);
    }

}