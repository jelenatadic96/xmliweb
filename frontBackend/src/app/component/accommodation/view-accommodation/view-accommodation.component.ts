import { Component, OnInit } from '@angular/core';
import { AccommodationSearch } from 'app/model/accommodation/accommodationSearch';
import { NgbDate } from '@ng-bootstrap/ng-bootstrap/datepicker/ngb-date';
import { Accommodation } from 'app/model/accommodation/accommodation';
import { Registracija } from 'app/model/korisnik/registracija';
import { Login } from 'app/model/korisnik/login';
import { Observable } from 'rxjs/Observable';
import {debounceTime, distinctUntilChanged, filter, map} from 'rxjs/operators';
import { ActivatedRoute, Router } from '@angular/router';
import { DatePipe } from '@angular/common';
import { AccommodationService } from 'app/service/accommodation/accommodation.service';
import { AccommodationReservation } from 'app/model/accommodation/accommodationReservation';
import { NgbDateStruct } from '@ng-bootstrap/ng-bootstrap';
import { TipSmestaja } from 'app/model/global-parameters/tipSmestaja';
import { Korisnik } from 'app/model/korisnik/korisnik';
import { AuthService } from 'app/service/korisnik/auth.service';
import { KorisnikService } from 'app/service/korisnik/korisnik.service';

@Component({
  selector: 'app-view-accommodation',
  templateUrl: './view-accommodation.component.html',
  styleUrls: ['./view-accommodation.component.css']
})
export class ViewAccommodationComponent implements OnInit {

  p: number;
  accommodationSearch: AccommodationSearch = new AccommodationSearch();
  beginDate: NgbDate;
  endDate: NgbDate;
  accommodationToShow : Accommodation[] = [];
  accomodationTypes = []
  additionalServices = []

  loggedUser: Korisnik = new Korisnik();

  maxLength: number = 30;
  now: Date = new Date();
  minDate: any;
  maxDate: any;

  categoryDropDown: String[] = [];
  accommodationTypeDropDown: String[] = [];
  additionalServiceDropDown: String[] = [];
  dropdownSettingsAdditionalService = {}
  dropdownSettings = {};
  selectedCategory: String;
  selectedAccommodationType: String;
  selectedAdditionalServices: String[] = [];

  patternHigh: any = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{12,}$";

  getAll(){
    this.accomodationService.getAll().subscribe(
      s => {
        this.accommodationToShow = s;
      }
    )

    this.accomodationService.getAllAccomodationTypes().subscribe(
      s => {
        this.accomodationTypes = s;
        let pomocna: String[] = []
        s.forEach(element => {
          pomocna.push(element.naziv)
        })

        this.accommodationTypeDropDown = pomocna;
      }
    )

    this.accomodationService.getAllAddiionalServices().subscribe(
      s => {
        this.additionalServices = s;
        let pomocna: String[] = []
        s.forEach(element => {
          pomocna.push(element.naziv)
        })

        this.additionalServiceDropDown = pomocna;
      }
    )
  
    this.categoryDropDown = ['Jedna zvezdica', 'Dve zvezdice', 'Tri zvezdice', 'Cetiri zvezdice', 'Pet zvezdica'];

  }

  constructor(private route: ActivatedRoute, private accomodationService: AccommodationService, private router: Router, public datepipe: DatePipe, 
          public authService: AuthService, public userService: KorisnikService) { 
    let res = localStorage.getItem('token');
    if(res != null){
      this.loggedUser.mejl = this.authService.getUsername(res);
    }
    else {
      this.loggedUser.mejl = ""
    }
  }

  ngOnInit() {

    this.getAll();

    this.dropdownSettings = {
      singleSelection: true,
      itemsShowLimit: 3,
      allowSearchFilter: true
    };

    this.dropdownSettingsAdditionalService = {
      singleSelection: false,
      itemsShowLimit: 3,
      allowSearchFilter: true
    };

    if(this.loggedUser.mejl != ""){
      this.userService.findByEmail(this.loggedUser.mejl).subscribe(
        e => {
          this.loggedUser = e;
        }
      );
    }
  }

  searchAccommodations(){
    this.accommodationSearch.prviDan = this.toModel(this.beginDate);
    this.accommodationSearch.poslednjiDan = this.toModel(this.endDate);

    this.accomodationTypes.forEach(element => {
      if(element.naziv == this.selectedAccommodationType){
        this.accommodationSearch.tipSmestajaDTO = element;
      }
    })

    this.additionalServices.forEach(element => {
      this.selectedAdditionalServices.forEach(selected => {
        if(element.naziv == selected){
          this.accommodationSearch.uslugeDTO.push(element);
        }
      });
    })

    this.accommodationSearch.kategorija = this.returnCategory();

    this.accomodationService.search(this.accommodationSearch).subscribe(
      s => this.accommodationToShow = s
    )
  }

  orderByType(param: string){
    // console.log(param);
    // this.accommodationSearch.orderByValue = param;
    // this.accomodationService.getAll().subscribe(
    //   s => {
    //     this.accommodationToShow = s;
    //   }
    // )
  }

  book(accommodation: Accommodation){
    let reservation : AccommodationReservation = new AccommodationReservation();
    reservation.prviDanRezervacije = this.accommodationSearch.prviDan;
    reservation.poslednjiDanRezervacije = this.accommodationSearch.poslednjiDan;
    reservation.smestajnaJedinicaDTO = accommodation;

    let res = localStorage.getItem('token');
    if(res == null){
      this.router.navigate(['login']);
    }
    else{
      this.accomodationService.book(reservation, this.loggedUser.id).subscribe(
        s => {

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

  returnCategory(): string{

    /*['Jedna zvezdica', 'Dve zvezdice', 'Tri zvezdice', 'Cetiri zvezdice', 'Pet zvezdica'] */
    if(this.selectedCategory == ""){
      return "NEKATEGORISAN";
    }
    else if(this.selectedCategory == "Jedna zvezdica"){
      return "JEDNA_ZVEZDICA";
    }
    else if(this.selectedCategory == "Dve zvezdice"){
      return "DVE_ZVEZDICE";
    }
    else if(this.selectedCategory == "Tri zvezdice"){
      return "TRI_ZVEZDICE";
    }
    else if(this.selectedCategory == "Cetiri zvezdice"){
      return "CETIRI_ZVEZDICE";
    }
    else if(this.selectedCategory == "Pet zvezdica"){
      return "PET_ZVEZDICA";
    }
  }

  vratiTrenutnuCenu(accommodation: Accommodation){
    if(accommodation.trenutnaCena == 0){
      return 100;
    }
    return accommodation.trenutnaCena;
  }
}
