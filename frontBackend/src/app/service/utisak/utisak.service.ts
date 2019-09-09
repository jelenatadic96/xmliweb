import { Injectable } from '@angular/core';
import { environment } from 'environments/environment.prod';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Utisak } from 'app/model/global-parameters/utisak';

@Injectable()
export class UtisakService {
  baseUrl: string = environment.baseUrl + '/utisak-service/rest/utisci';

  constructor(private http: HttpClient) { }

  getAll(): Observable<Utisak[]> {
    return this.http.get<Utisak[]>(this.baseUrl + "/svi");
  }

  postaviKomentar(utisak: Utisak, korisnikId: string): Observable<Utisak> {
    let params = new HttpParams();
    params = params.append('korisnik', korisnikId);
    return this.http.post<Utisak>(this.baseUrl + "/komentar", utisak, {params: params});
  }

  postaviOcena(utisak: Utisak, korisnikId: string): Observable<Utisak> {
    let params = new HttpParams();
    params = params.append('korisnik', korisnikId);
    return this.http.post<Utisak>(this.baseUrl + "/ocena", utisak, {params: params});
  }

  updateReview(utisak: Utisak): Observable<Utisak> {
    return this.http.get<Utisak>(this.baseUrl + `/${utisak.id}`);
  }
  
  getUnapprovedUserReviews(): Observable<Utisak[]> {
    return this.http.get<Utisak[]>(this.baseUrl + "/neodobreni");
  }

}
