import { Component, OnInit } from '@angular/core';
import { Review } from 'app/model/korisnik/review';
import { AccommodationReservation } from 'app/model/accommodation/accommodationReservation';
import { AccommodationService } from 'app/service/accommodation/accommodation.service';
import { KorisnikService } from 'app/service/korisnik/korisnik.service';
import { AuthService } from 'app/service/korisnik/auth.service';
import { DatePipe } from '@angular/common';
import { Korisnik } from 'app/model/korisnik/korisnik';
import { Router } from '@angular/router';
import { UtisakService } from 'app/service/utisak/utisak.service';
import { Utisak } from 'app/model/global-parameters/utisak';
import { Accommodation } from 'app/model/accommodation/accommodation';

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
  activeReservations: AccommodationReservation[] = [];
  userReviews: Review[] = [];
  pomReviews: Review[] = [];
  pomKomentari: string[] = [];
  currentRate = 4;
  loggedUser: Korisnik = new Korisnik();
  p:any;

  constructor(private accommodationService: AccommodationService, private userService: KorisnikService,private router: Router,
               private authService: AuthService, private datePipe: DatePipe, private utisakService: UtisakService) { 
    let res = localStorage.getItem('token');
    if(res != null){
      this.loggedUser.mejl = this.authService.getUsername(res);
    }
    else{
      this.router.navigate(['login']);
    }
  }

  ngOnInit() {

    this.userService.findByEmail(this.loggedUser.mejl).subscribe(
      e => {
        this.loggedUser = e;
        this.accommodationService.getUserHistory(this.loggedUser.id).subscribe(
          s => {
            this.accommodationToShow = s;
            this.accommodationToShow.forEach(element => {
              element.utisak = new Utisak()
              this.pomKomentari.push("");
            })
            this.utisakService.getAll().subscribe(
              ss => {
                let index = 0;
                ss.forEach(element => {
                  this.accommodationToShow.forEach(reservation => {
                    if(element.rezervacijaId === reservation.id){
                      reservation.utisak = element;
                      this.pomKomentari[index] = reservation.utisak.komentar;
                      index++
                    }
                  })
                })
              }
            )
          }
        )
        this.accommodationService.getUserReservations(this.loggedUser.id).subscribe(
          s => {
            this.activeReservations = s;
          }
        )
      })
  }

  addComment(accommodation: AccommodationReservation){
    if(this.selectedTextBox != -1 && this.selectedTextBox == accommodation.id){
      this.selectedTextBox = -1;
    }
    else{
      this.selectedTextBox = accommodation.id;
    }
    
    this.newReview = new Review();
  }

  otkaziRezervaciju(accommodation: AccommodationReservation){
    if(confirm("Otkazi rezervaciju?")) {
    this.accommodationService.cancel(accommodation.id).subscribe(
      s => {
        let pomReservation: AccommodationReservation = new AccommodationReservation();
        this.activeReservations.forEach(element => {
          if(element.id === pomReservation.id){
            pomReservation = element;
          }
        })

        this.activeReservations.splice(this.activeReservations.indexOf(pomReservation), 1)
      },
      err => {
        if(err.code == 409){
          alert("Nije moguce otkazati");
        }
      }
    )
    }
  }

  saveRate(review: Utisak, accommodation: AccommodationReservation){
    review.rezervacijaId = accommodation.id;
    this.utisakService.postaviOcena(review, this.loggedUser.id.toString()).subscribe(
      s => {
        let ocena =  0;
        let brojUtisaka = 0;
        accommodation.utisak = review;
        this.accommodationToShow.forEach(element => {
          if(element.smestajnaJedinicaDTO.id == accommodation.smestajnaJedinicaDTO.id){
            ocena += element.utisak.ocena;
            brojUtisaka++;
          }
        })
        ocena /= brojUtisaka;
        accommodation.smestajnaJedinicaDTO.ocena = ocena;
        this.accommodationService.updateRating(accommodation.smestajnaJedinicaDTO.id, ocena.toString()).subscribe(
          s => {
            s.ocena = ocena;
          }
        )
      }
    )
  }

  dodajKomentar(accommodationReservation: AccommodationReservation, i: number){
    accommodationReservation.utisak.komentar = this.pomKomentari[i];
    accommodationReservation.utisak.komentarOdobren = false;
    accommodationReservation.utisak.rezervacijaId = accommodationReservation.id;
    this.utisakService.postaviKomentar(accommodationReservation.utisak, this.loggedUser.id.toString()).subscribe(
      s => {
        s.komentarOdobren = false;
        accommodationReservation.utisakId = s.id;
      }
    );

  }

  neMozeUnetiKomentar(rezervacija: AccommodationReservation){
    if(rezervacija.utisak.komentar == "" && rezervacija.realizovana){
      return false
    }
    return true;
  }

  neMozeOceniti(rezervacija: AccommodationReservation){
    if(rezervacija.utisak.ocena == 0 && rezervacija.realizovana){
      return false
    }
    return true;
  }

}
