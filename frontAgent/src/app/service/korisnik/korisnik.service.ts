import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { Korisnik } from 'app/model/korisnik/korisnik';

@Injectable()
export class KorisnikService {


  constructor(private http: HttpClient) { }

  findOne(id: number): Observable<Korisnik> {
    return this.http.get<Korisnik>(`/api/user/${id}`);
  }

  findAll(): Observable<Korisnik[]> {
    return this.http.get<Korisnik[]>(`/api/users`);
  }

  findByEmail(email: string): Observable<Korisnik> {
    return this.http.get<Korisnik>(`api/user/email/${email}`);
  }
}