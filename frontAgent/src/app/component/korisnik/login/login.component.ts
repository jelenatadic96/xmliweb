import { Component, OnInit } from '@angular/core';
import { Login } from 'app/model/korisnik/login';
import { Korisnik } from 'app/model/korisnik/korisnik';
import { AuthService } from 'app/service/korisnik/auth.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  user: Login = new Login();
  loggedUser: Korisnik = new Korisnik();
  constructor(private auth: AuthService, private router: Router/*, private userService: KorisnikService*/, private authService: AuthService) { 
  }

  ngOnInit() {
    if(localStorage.getItem('token') != null){
    //  this.loggedUser.mejl = this.authService.getUsername(localStorage.getItem('token'));
      // let isAdmin = false;
      // this.userService.findByEmail(this.loggedUser.email).subscribe(
      //   s=>{
      //     this.loggedUser = s;          
      //     this.loggedUser.role.forEach(element =>
      //       {

              
      //       if(element.roleName == "ROLE_ADMIN"){
      //         isAdmin = true;
      //           }
      //         }
      //       )
      //       if(!isAdmin){
      //         if(localStorage.getItem('reservation') != null){
      //           let acc : AccommodationReservation = JSON.parse(localStorage.getItem('reservation'))

      //           if(acc.roomDTO.id != null && acc.roomDTO.id != undefined){
      //             this.router.navigate(['bookAccommodation/' + acc.roomDTO.id]);
      //           }
      //           else{
      //             this.router.navigate(['address']);
      //           }
      //         }
      //         else{
      //           this.router.navigate(['address']);
      //         }
      //       }
      //       else{
      //         this.router.navigate(['users']);
      //       }
      //   }
      // )

    }
  }

  login(){
  localStorage.removeItem('token');
  this.auth.login(this.user.mejl, this.user.lozinka)
      .subscribe(
        res => 
        {
          console.log(res);
          //alert("evo ga")
          localStorage.setItem('token', JSON.stringify(res));
          //let role = this.auth.getRoles(res);
          this.router.navigate(['poruke']);
          
          // //samo da bi postavio ulogo naog korisnika
          // this.userService.findByEmail(this.user.email).subscribe(
          //   s => {
          //     this.loggedUser = s;
          //     window.location.reload();
              
          //   },
          //   err =>{
          //     console.log("err za pronalazenje po mailu");
          //   } 
          // );

        },
        err => {
          alert("Wrong email or password");
          console.log("usao kao error za logovanje");
        }
      );
  }
 

}