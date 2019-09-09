import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Korisnik } from 'app/model/korisnik/korisnik';
import { Observable } from 'rxjs/Observable';
import { Poruka } from 'app/model/korisnik/poruka';

@Injectable()
export class PorukeService {

  constructor(private http: HttpClient) { }

  // getInbox(userId: number): Observable<Chat[]>{
  //   return this.http.get<Chat[]>('inbox/' + userId);
  // }

  // getChat(userId:number, chatId:number): Observable<Message[]>{
  //   return this.http.get<Message[]>('inbox/' + userId + '/chat/' + chatId);
  // }

  // sendMessage(chatId: number, hotelId: number, message: Message): Observable<boolean>{
  //   return this.http.post<boolean>('inbox/' + chatId + "/hotel/" + hotelId, message);
  // }

  //preuzmi poruke sa kojima chetuje agent
  getInbox(userId: number): Observable<Korisnik[]>{
    //userId = 1;
    return this.http.get<Korisnik[]>('/rest/agenti/' + userId + '/poruke');
  }

  //za svakog korisnika dovuci njegove poruke
  getMessages(agentId:number, userId:number): Observable<Poruka[]>{
    //agentId = 1; 
    //userId = 3;
    return this.http.get<Poruka[]>('/rest/agenti/' + agentId + '/poruke/' + userId);
  }

  //posalji poruku korisniku
  sendMessage(agentId: number, userId: number, message: Poruka): Observable<Poruka>{
    //agentId = 3;
    return this.http.post<Poruka>('/rest/agenti/' + agentId + '/poruke?korisnik-id=' + userId, message);
  }
}
