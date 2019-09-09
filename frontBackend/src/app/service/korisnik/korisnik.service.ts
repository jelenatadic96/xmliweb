import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/Observable';
import { environment } from 'environments/environment.prod';
import { HttpClient } from '@angular/common/http';
import { Korisnik } from 'app/model/korisnik/korisnik';

@Injectable()
export class KorisnikService {

  constructor(private http: HttpClient) { }

  baseUrl: string = environment.baseUrl + '/korisnici-service/rest/korisnici';

  findAll(): Observable<Korisnik[]> {
    return this.http.get<Korisnik[]>(this.baseUrl);
  }

  findById(id: number): Observable<Korisnik> {
    id = 1;
    return this.http.get<Korisnik>(this.baseUrl + '/' + id);
  }

  findByEmail(email: string): Observable<Korisnik> {
    return this.http.get<Korisnik>(this.baseUrl + `/email/${email}`);
  }

  changeStateOfUser(id: number, korisnikState: string): Observable<Korisnik> {
    return this.http.put<Korisnik>(`${this.baseUrl}/${id}?status=${korisnikState}`, {});
  }

  
}
