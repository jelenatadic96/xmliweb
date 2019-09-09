import { Component, OnInit } from '@angular/core';
import { Review } from 'app/model/korisnik/review';
import { AccommodationReservation } from 'app/model/accommodation/accommodationReservation';
import { Korisnik } from 'app/model/korisnik/korisnik';
import { AccommodationService } from 'app/service/accommodation.service';
import { AuthService } from 'app/service/korisnik/auth.service';
import { DatePipe } from '@angular/common';
import { KorisnikService } from 'app/service/korisnik/korisnik.service';
import { Router } from '@angular/router';
import { Agent } from 'app/model/korisnik/agent';

@Component({
  selector: 'app-vidi-rezervacije',
  templateUrl: './vidi-rezervacije.component.html',
  styleUrls: ['./vidi-rezervacije.component.css']
})
export class VidiRezervacijeComponent implements OnInit {

  selectedRate: number = -1;
  selectedTextBox: number = -1;
  selectedTextBoxMessage: number = -1;
  newReview: Review = new Review();
  accommodationToShow: AccommodationReservation[] = [];
  userReviews: Review[] = [];
  pomReviews: Review[] = [];
  currentRate = 4;
  loggedUser: Korisnik = new Korisnik();
  p:any;

  constructor(private accommodationService: AccommodationService, private userService: KorisnikService,private router: Router,
               private authService: AuthService, private datePipe: DatePipe) { 
    let res : Korisnik = JSON.parse(localStorage.getItem('token'));
    if(res != null){
      this.loggedUser.mejl = res.mejl;
    }
    else{
      this.router.navigate(['login']);
    }
  }

  ngOnInit() {
    let res : Korisnik = JSON.parse(localStorage.getItem('token'));
    /*this.userService.findByEmail(this.loggedUser.mejl).subscribe(
      e => {*/
        
        this.loggedUser = res;
        this.accommodationService.getUserReservations(this.loggedUser.id).subscribe(
          s => {
            this.accommodationToShow = s;
          }
        )
      //})

    // let accomodationReservation = new AccommodationReservation();
    // accomodationReservation.prviDanRezervacije = "02/01/2012"
    // accomodationReservation.poslednjiDanRezervacije = "02/01/2014"
    // accomodationReservation.ukupnaCena = 300

    // this.accommodationToShow.push(accomodationReservation)
    // this.accommodationToShow.push(accomodationReservation)
    // this.accommodationToShow.push(accomodationReservation)
    // this.accommodationToShow.push(accomodationReservation)
    // this.accommodationToShow.push(accomodationReservation)

  }

  realised(accommodation: AccommodationReservation){
    if(confirm("Reservation realised?")){
      accommodation.realizovana = true;
      this.accommodationService.updateResevation(accommodation).subscribe(
        s => {
          let pomReservation: AccommodationReservation = new AccommodationReservation();
          this.accommodationToShow.forEach(element => {
            if(element.id === accommodation.id){
              pomReservation = element;
            }
          })
      
          this.accommodationToShow.splice(this.accommodationToShow.indexOf(pomReservation), 1)
        }
      )
    }
  }
}
