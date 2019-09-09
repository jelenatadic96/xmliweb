import { Component, OnInit } from '@angular/core';
import { Registracija } from 'app/model/korisnik/registracija';
import { AuthService } from 'app/service/korisnik/auth.service';
import { Router, ActivatedRoute } from '@angular/router';
import { Korisnik } from 'app/model/korisnik/korisnik';

@Component({
  selector: 'app-registracija',
  templateUrl: './registracija.component.html',
  styleUrls: ['./registracija.component.css']
})
export class RegistracijaComponent implements OnInit {

  user: Registracija = new Registracija();
  maxLength: number = 30;
  mailNotUnique: boolean = true;

  patternHigh: any = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{12,}$";

id: number;
postojiDugme: boolean = true;
private sub: any;
isDataAvailable: boolean;
loggedUser: Korisnik = new Korisnik();

  constructor(private auth: AuthService, private router: Router, private route: ActivatedRoute, private authService: AuthService) {
    this.user = new Registracija();
    let res = localStorage.getItem('token');
    if(res != null){
       this.loggedUser.mejl = this.authService.getUsername(res);
    }
  }

  ngOnInit() {
    this.sub = this.route.params.subscribe(params => {
      this.isDataAvailable = false;
      if(params['id'] != null){
        this.id = +params['id']; // (+) konvertuje string 'id' u broj
        //id postavljamo kao path parametar pomocu interpolacije stringa
        if(this.id != null )
        { 
          this.postojiDugme = false; //znaci da hoce da doda agenta
        }
      }
   });
  }

  register(){
    let message: string = this.proveriUnosKorisnik();
    if(message === "OK")
    {
      if(this.id == null){
      this.auth.signup(this.user)
          .subscribe(
            res => 
            {
              this.router.navigate(['login'])
            },
            err => {
              console.log("usao kao error");
              if (err.status === 400) { // bad request losi kredencijali
                console.log("usao 400");
                this.mailNotUnique = false;
            }
          }
        )
      }
      else{   //registruj agenta
        this.auth.signupAgent(this.user).subscribe(
          ss =>{
            if(this.loggedUser.mejl == ""){
                this.router.navigate(['login']);
            }
            else {
              this.router.navigate(['users']);
            }
          },
          e => {
            alert('error');
          }
        )
      }
    }
    else{
      alert(message);
    }
  }

  validateEmail(email: string) 
  {
    let re = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
    return re.test(String(email).toLowerCase());
  }
  

  validatePasswords(){
    if(this.user.password === this.user.repeatPassword){
      return true;
    }
    else{
      return false;
    }
  }

proveriUnosKorisnik(): string{
  if(this.user.email === "" || this.user.email.length > 60){
    return "Morate popuniti validnu e-mail adresu koja mora biti kraca od " + 60 + " karaktera";
  }

  if(!this.validateEmail(this.user.email)){
    return "Morate uneti validnu e-mail adresu";
  }
  
  if(this.user.password === "" || this.user.password.length > this.maxLength){
    return "Morate popuniti lozinku koja mora biti kraca od " + this.maxLength + " karaktera";
  }

  if(this.user.repeatPassword === "" || this.user.repeatPassword.length > this.maxLength){
    return "Morate popuniti ponovo lozinku koja mora biti kraca od " + this.maxLength + " karaktera";
  }

  if(!this.validatePasswords()){
    return "lozinka i potvrda lozinke moraju da se poklapaju!";
  }

  if(this.user.firstName === "" || this.user.firstName.length > this.maxLength){
    return "Morate popuniti ime koja mora biti kraca od " + this.maxLength + " karaktera";
  }

  if(this.user.lastName === "" || this.user.lastName.length > this.maxLength){
    return "Morate popuniti prezime koja mora biti kraca od " + this.maxLength + " karaktera";
  }

  return "OK";
}

}
