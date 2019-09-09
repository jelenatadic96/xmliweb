import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { AuthService } from 'app/service/korisnik/auth.service';
import { KorisnikService } from 'app/service/korisnik/korisnik.service';
import { PorukeService } from 'app/service/korisnik/poruke.service';
import { Poruka } from 'app/model/korisnik/poruka';
import { Agent } from 'app/model/korisnik/agent';
import { Korisnik } from 'app/model/korisnik/korisnik';

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

  constructor(private route: ActivatedRoute, private userService: KorisnikService, private router: Router, private authService: AuthService, private chatService: PorukeService) {
    let res: Korisnik = JSON.parse(localStorage.getItem('token'));
    if(res != null){
       this.loggedUser = res; //this.authService.getUsername(res);
      // //this.chat.messages = [];
    }
   }

  ngOnInit() {
    // this.userService.findByEmail(this.loggedUser.mejl).subscribe(
    //   e => {
    //     this.loggedUser = e;
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
      // })
  }

  loggedUsersMessage(message: Poruka){
    if(message.agentDTO.id == this.loggedUser.id){
      return true;
    }
    return false;
  }

  sendMessage(){
    let d = new Date();
    let datestring = d.getFullYear() + "-" + ("0"+(d.getMonth()+1)).slice(-2) + "-" + ("0" + d.getDate()).slice(-2) 
    this.newMessage.vreme = datestring;
    this.newMessage.agentDTO.id = this.loggedUser.id; //proveriti za ovo posto je djuka pametan pa mu korinsik i agent nisu ista klasa
    this.chat.forEach(element =>
      {
        if(element.korisnikDTO.id != this.loggedUser.id){
          this.newMessage.korisnikDTO = element.korisnikDTO;
        }
      })                          //agent, user, poruka
    this.chatService.sendMessage(this.newMessage.agentDTO.id, this.newMessage.korisnikDTO.id, this.newMessage).subscribe(
      s => {
        this.chat.push(this.newMessage);
        this.newMessage = new Poruka();
      }
    )
  }

  changeActiveChat(chat:Korisnik){  //da promeni korisnika
    this.chat = chat.porukeSaAgentom;
    this.newMessage.korisnikDTO = chat.porukeSaAgentom[0].korisnikDTO;
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