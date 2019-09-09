import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/Observable';
import { environment } from 'environments/environment.prod';
import { HttpClient } from '@angular/common/http';
import { Korisnik } from 'app/model/korisnik/korisnik';

@Injectable()
export class KorisnikService {

  constructor(private http: HttpClient) { }

  baseUrl: string = environment.baseUrl + '/smestaj-service/rest/smestaj';

  findAll(): Observable<any[]> {
    return this.http.get<any[]>(this.baseUrl);
  }

  findById(): Observable<Korisnik> {
    return this.http.get<Korisnik>(environment.baseUrl + '/korisnici-service/rest/korisnici/1');
  }
}
