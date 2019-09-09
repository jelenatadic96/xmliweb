import { Component, OnInit } from '@angular/core';
import { NgbDateStruct } from '@ng-bootstrap/ng-bootstrap';
import { ActivatedRoute, Router } from '@angular/router';
import { AuthService } from 'app/service/korisnik/auth.service';
import { KorisnikService } from 'app/service/korisnik/korisnik.service';
import { AccommodationService } from 'app/service/accommodation.service';
import { DatePipe } from '@angular/common';
import { AccommodationSearch } from 'app/model/accommodation/accommodationSearch';
import { NgbDate } from '@ng-bootstrap/ng-bootstrap/datepicker/ngb-date';
import { Accommodation } from 'app/model/accommodation/accommodation';
import { Korisnik } from 'app/model/korisnik/korisnik';
import { Observable } from 'rxjs/Observable';
import { AccommodationReservation } from 'app/model/accommodation/accommodationReservation';
import {debounceTime, distinctUntilChanged, filter, map} from 'rxjs/operators';
import { Agent } from 'app/model/korisnik/agent';


const states = ['Maglic', 'Novi Sad', 'Beograd', 'Arizona', 'Arkansas', 'California', 'Colorado',
  'Connecticut', 'Delaware', 'District Of Columbia', 'Federated States Of Micronesia', 'Florida', 'Georgia',
  'Guam', 'Hawaii', 'Idaho', 'Illinois', 'Indiana', 'Iowa', 'Kansas', 'Kentucky', 'Louisiana', 'Maine',
  'Marshall Islands', 'Maryland', 'Massachusetts', 'Michigan', 'Minnesota', 'Mississippi', 'Missouri', 'Montana',
  'Nebraska', 'Nevada', 'New Hampshire', 'New Jersey', 'New Mexico', 'New York', 'North Carolina', 'North Dakota',
  'Northern Mariana Islands', 'Ohio', 'Oklahoma', 'Oregon', 'Palau', 'Pennsylvania', 'Puerto Rico', 'Rhode Island',
  'South Carolina', 'South Dakota', 'Tennessee', 'Texas', 'Utah', 'Vermont', 'Virgin Islands', 'Virginia',
  'Washington', 'West Virginia', 'Wisconsin', 'Wyoming'];

@Component({
  selector: 'app-vidi-smestaj',
  templateUrl: './vidi-smestaj.component.html',
  styleUrls: ['./vidi-smestaj.component.css']
})
export class VidiSmestajComponent implements OnInit {

  p: number;
  accommodationSearch: AccommodationSearch = new AccommodationSearch();
  beginDate: NgbDate;
  endDate: NgbDate;
  accommodationToShow : Accommodation[] = [];

  loggedUser: Korisnik = new Korisnik();

  maxLength: number = 30;
  now: Date = new Date();
  minDate: any;
  maxDate: any;

  search = (text$: Observable<string>) =>
    text$.pipe(
      debounceTime(200),
      distinctUntilChanged(),
      map(term => term.length < 2 ? []
        : states.filter(v => v.toLowerCase().indexOf(term.toLowerCase()) > -1).slice(0, 10))
    )

  getAll(){
    this.accomodationService.getAll(this.loggedUser.id).subscribe(
      s => {
        this.accommodationToShow = s;
      }
    )
  }

  constructor(private route: ActivatedRoute, private accomodationService: AccommodationService, private router: Router, public datepipe: DatePipe, 
          public authService: AuthService, public userService: KorisnikService) { 
    this.loggedUser = JSON.parse(localStorage.getItem('token'));
    if(this.loggedUser != null){
      this.loggedUser.mejl = this.loggedUser.mejl//this.authService.getUsername(res);
    }
    else {
      this.loggedUser.mejl = ""
    }
  }

  ngOnInit() {

    if(this.loggedUser.mejl != ""){
       this.getAll();
    }
  }


  book(accommodation: Accommodation){
    let reservation : AccommodationReservation = new AccommodationReservation();
    reservation.prviDanRezervacije = this.toModel(this.beginDate);
    reservation.poslednjiDanRezervacije = this.toModel(this.endDate);
    reservation.smestajnaJedinicaDTO = accommodation;

    let res : Agent = JSON.parse(localStorage.getItem('token'));

    if(res == null){
      this.router.navigate(['login']);
    }
    else{
      this.accomodationService.book(reservation).subscribe(
        succ => {

        }
      )
    }
  }

  fromModel(date: string): NgbDateStruct {

    const parsedDate = /(\d\d\d\d)-(\d\d)-(\d\d)/.exec(date);
    if (parsedDate) {
      return <NgbDateStruct>{ year: Number(parsedDate[1]), month: Number(parsedDate[2]), day: Number(parsedDate[3]) };
    } else {
      return null;
    }
  }

  toModel(date: NgbDateStruct): string {
    if (date) {
      let dateString = date.year + '-' + date.month + '-' + date.day;
      return this.datepipe.transform(dateString, 'yyyy-MM-dd')
    } else {
      return null;
    }
  }
}
