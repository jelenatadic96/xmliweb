import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from 'app/service/korisnik/auth.service';
import { KorisnikService } from 'app/service/korisnik/korisnik.service';
import { Korisnik } from 'app/model/korisnik/korisnik';

@Component({
  selector: 'app-svi-korisnici',
  templateUrl: './svi-korisnici.component.html',
  styleUrls: ['./svi-korisnici.component.css']
})
export class SviKorisniciComponent implements OnInit {


  users: Korisnik[];
  selectedUser: Korisnik;
  dropdownSettings = {};
  p: any;
  loggedUser: Korisnik = new Korisnik();

  constructor(private authService: AuthService, private userService : KorisnikService, 
    private router: Router) {
    let res = localStorage.getItem('token');
    if(res == null){
      this.router.navigate(['login']);
    }
    if(res != null){
      this.loggedUser.mejl = this.authService.getUsername(res);
    }
  }

  ngOnInit() {
    
    this.userService.findByEmail(this.loggedUser.mejl).subscribe(
      e => {
        this.loggedUser = e;
      }
    );
    this.userService.findAll().subscribe(
      s => {
        this.users = s
      }
    );

    this.dropdownSettings = {
      singleSelection: true,
      itemsShowLimit: 3,
      allowSearchFilter: true
    };

  }

  changeStateOfUser(user:Korisnik, boolState: string){
    user.status = boolState;
    this.userService.changeStateOfUser(user.id, boolState).subscribe(
      s => {
        this.userService.findAll().subscribe(
          novi => {
            this.users = novi
          }
        );
      }
    );
  }

  // removeUser(user: Korisnik){
  //   this.userService.deleteUser(user.id).subscribe(
  //     s =>{
  //       this.userService.findAll().subscribe(
  //         novi => {
  //           this.users = novi
  //         }
  //       );
  //     }
  //   );
  // }
}

