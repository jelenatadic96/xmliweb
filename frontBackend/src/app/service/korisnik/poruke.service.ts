import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Korisnik } from 'app/model/korisnik/korisnik';
import { Poruka } from 'app/model/korisnik/poruka';
import { environment } from 'environments/environment.prod';

@Injectable()
export class PorukeService { 
  baseUrl: string = environment.baseUrl + '/poruke-service/rest';

  constructor(private http: HttpClient) { }

  //preuzmi poruke sa kojima chetuje agent
  getInbox(userId: number): Observable<Korisnik[]>{
    //userId = 1;
    return this.http.get<Korisnik[]>(this.baseUrl + '/korisnici/' + userId + '/poruke');
  }

  //za svakog korisnika dovuci njegove poruke
  getMessages(userId:number, agentId:number): Observable<Poruka[]>{
    //agentId = 1; 
    //userId = 3;
    return this.http.get<Poruka[]>(this.baseUrl + '/korisnici/' + userId + '/poruke/' + agentId);
  }

  //posalji poruku korisniku
  sendMessage(agentId: number, userId: number, message: Poruka): Observable<Poruka>{
    //agentId = 3;
    return this.http.post<Poruka>(this.baseUrl + '/korisnici/' + userId + '/poruke?agent-id=' + agentId, message);
  }
}
