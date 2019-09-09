import { Component, OnInit } from '@angular/core';
import { Korisnik } from 'app/model/korisnik/korisnik';
import { ActivatedRoute, Router } from '@angular/router';
import { KorisnikService } from 'app/service/korisnik/korisnik.service';
import { Poruka } from 'app/model/korisnik/poruka';
import { AuthService } from 'app/service/korisnik/auth.service';
import { PorukeService } from 'app/service/korisnik/poruke.service';

@Component({
  selector: 'app-poruke',
  templateUrl: './poruke.component.html',
  styleUrls: ['./poruke.component.css']
})
export class PorukeComponent implements OnInit {

  inboxKorisnika: Korisnik[] = [];
  chat: Poruka[] = [];
  private sub: any;
  loggedUser: Korisnik = new Korisnik();
  newMessage: Poruka = new Poruka();
  p:any;

  constructor(private route: ActivatedRoute, private userService: KorisnikService, private router: Router, 
    private authService: AuthService, private chatService: PorukeService) {
    //let res: Korisnik = JSON.parse(localStorage.getItem('token'));
    let res = localStorage.getItem('token');
    if(res != null){
       this.loggedUser.mejl = this.authService.getUsername(res); //res; //
      // //this.chat.messages = [];
      // this.userService.findByEmail(this.loggedUser.mejl).subscribe(
      //     succ => {
      //       this.loggedUser = succ;
      //     }
      // );
    }
   }

  ngOnInit() {
    this.userService.findByEmail(this.loggedUser.mejl).subscribe(
      succ => {
        this.loggedUser = succ;
        //dobijem sve korisnike sa kojima je pricano
        this.chatService.getInbox(this.loggedUser.id).subscribe(
         s => {
           this.inboxKorisnika = s;
           this.inboxKorisnika.forEach(element => {
             this.chatService.getMessages(this.loggedUser.id, element.id).subscribe( //dobavi sve poruke za tog korisnika
               ss => {
                 element.porukeSaAgentom = ss;
               }
             )
           })
         }
         )
       }
       )
  }

  loggedUsersMessage(message: Poruka){
    if(message.korisnikId == this.loggedUser.id){
      return true;
    }
    return false;
  }

  createNewMessage(){
    this.chat = [];
    this.newMessage = new Poruka();
  }

  sendMessage(){
    let d = new Date();
    let datestring = d.getFullYear() + "-" + ("0"+(d.getMonth()+1)).slice(-2) + "-" + ("0" + d.getDate()).slice(-2) 
    this.newMessage.vreme = datestring;
    this.newMessage.korisnikId = this.loggedUser.id; //proveriti za ovo posto je djuka pametan pa mu korinsik i agent nisu ista klasa
    this.chat.forEach(element =>
      {
        if(element.korisnikId != this.loggedUser.id){
          this.newMessage.korisnikId = element.korisnikId;
        }
      })                          //agent, user, poruka
    this.chatService.sendMessage(this.newMessage.agentId, this.newMessage.korisnikId, this.newMessage).subscribe(
      s => {
        this.chat.push(this.newMessage);
        this.newMessage = new Poruka();
      }
    )
  }

  changeActiveChat(chat:Korisnik){  //da promeni korisnika
    this.chat = chat.porukeSaAgentom;
    //TODO: proveriti
    this.newMessage.agentId = chat.porukeSaAgentom[0].agentId;
    this.chatService.getMessages(this.loggedUser.id, chat.id).subscribe(
      s => {
        this.chat = s;
      }
    )
    // chat.porukeSaAgentom.forEach(element =>
    //   {
    //     element.opened = true;
    //   })
   }

}