import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Korisnik } from 'app/model/korisnik/korisnik';
import { AuthService } from 'app/service/korisnik/auth.service';
import { KorisnikService } from 'app/service/korisnik/korisnik.service';


@Component({
  selector: 'app-meni-bar',
  templateUrl: './meni-bar.component.html',
  styleUrls: ['./meni-bar.component.css']
})
export class MeniBarComponent implements OnInit {

  public loggedIn: boolean = false;
  isAdmin = false;
  loggedUser: Korisnik = new Korisnik();

  constructor(private authService: AuthService, private userService: KorisnikService, private router: Router){
    let res = localStorage.getItem('token');
    if(res != null){
      this.loggedIn = true;
      this.loggedUser.mejl = this.authService.getUsername(res);
      this.userService.findByEmail(this.loggedUser.mejl).subscribe(
        s=>{
          this.loggedUser = s;
          let roles = this.authService.getRoles(res) 
          // roles.forEach(element =>
          //   {
          //   if(element.roleName == "ROLE_ADMIN"){
          //     this.isAdmin = true;
          //       }
          //     }
          //   )
          if(roles == "ROLE_ADMIN"){
            this.isAdmin = true;
          }
        }
      )

    }
  }

  ngOnInit(){
    
  }

  logOut(){
    this.authService.logout();
    this.loggedIn = false;
    this.router.navigate(['login']);
  }

  goToHome(){
    if(this.isAdmin){
      this.router.navigate(['users']);
    }
    else{
      this.router.navigate(['address']);
    }
  }
  
}

