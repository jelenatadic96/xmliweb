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

const states = ['Maglic', 'Novi Sad', 'Beograd', 'Arizona', 'Arkansas', 'California', 'Colorado',
  'Connecticut', 'Delaware', 'District Of Columbia', 'Federated States Of Micronesia', 'Florida', 'Georgia',
  'Guam', 'Hawaii', 'Idaho', 'Illinois', 'Indiana', 'Iowa', 'Kansas', 'Kentucky', 'Louisiana', 'Maine',
  'Marshall Islands', 'Maryland', 'Massachusetts', 'Michigan', 'Minnesota', 'Mississippi', 'Missouri', 'Montana',
  'Nebraska', 'Nevada', 'New Hampshire', 'New Jersey', 'New Mexico', 'New York', 'North Carolina', 'North Dakota',
  'Northern Mariana Islands', 'Ohio', 'Oklahoma', 'Oregon', 'Palau', 'Pennsylvania', 'Puerto Rico', 'Rhode Island',
  'South Carolina', 'South Dakota', 'Tennessee', 'Texas', 'Utah', 'Vermont', 'Virgin Islands', 'Virginia',
  'Washington', 'West Virginia', 'Wisconsin', 'Wyoming'];

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

  userRegistration: Registracija = new Registracija();
  userLogin: Login = new Login();
  maxLength: number = 30;
  mailNotUnique: boolean = true;
  now: Date = new Date();
  minDate: any;
  maxDate: any;

  privilegesDropDown: String[] = [];
  dropdownSettings = {};
  selectedPrivilege: String;

  patternHigh: any = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{12,}$";

  search = (text$: Observable<string>) =>
    text$.pipe(
      debounceTime(200),
      distinctUntilChanged(),
      map(term => term.length < 2 ? []
        : states.filter(v => v.toLowerCase().indexOf(term.toLowerCase()) > -1).slice(0, 10))
    )

  getAll(){
  }

  constructor(private route: ActivatedRoute, private accomodationService: AccommodationService, private router: Router, public datepipe: DatePipe) { 
      this.minDate = { year: this.now.getFullYear(), month: this.now.getMonth() + 1, day: this.now.getDate() };  
      this.maxDate = { year: this.now.getFullYear() + 50, month: 1, day: 1 }; 
    }

  ngOnInit() {

    /*this.accommodationSearch = JSON.parse(localStorage.getItem('search'))

    let beginDateString = this.datepipe.transform(this.accommodationSearch.beginDate, 'yyyy-MM-dd');
    let endDateString =this.datepipe.transform(this.accommodationSearch.endDate, 'yyyy-MM-dd');

    let beginDateStruct = this.fromModel(beginDateString);
    let endDateStruct = this.fromModel(endDateString);
    this.beginDate = new NgbDate(beginDateStruct.year, beginDateStruct.month, beginDateStruct.day);
    this.endDate = new NgbDate(endDateStruct.year, endDateStruct.month, endDateStruct.day);
*/

    this.accomodationService.getAll().subscribe(
      s => {
        this.accommodationToShow = s;
      }
    )

    this.dropdownSettings = {
      singleSelection: true,
      itemsShowLimit: 3,
      allowSearchFilter: true
    };

    this.privilegesDropDown = ['no category', 'one star', 'two star']

    let accom = new Accommodation()

    this.accommodationToShow.push(accom)
    this.accommodationToShow.push(accom)
    this.accommodationToShow.push(accom)
    this.accommodationToShow.push(accom)
    this.accommodationToShow.push(accom)
    this.accommodationToShow.push(accom)
    this.accommodationToShow.push(accom)

  }

  getSelectedOptions() { // right now: ['1','3']
    return this.additionalServices
              .filter(opt => opt.checked)
              .map(opt => opt.name)
  }

  advancedSearch(){
    // console.log("otkazivanje: " + this.accommodationSearch.cancelationAllowed);
    // console.log("razdaljina: " + this.accommodationSearch.distance);
    // console.log("broj dana : " + this.accommodationSearch.cancellationDays);
    // if(this.checkIfInputIsValid()){
    //   // this.accommodationToShow.forEach(element => {
    //   //   element.imageDTO
    //   // });
    //   this.accommodationSearch.beginDate = this.toModel(this.beginDate);
    //   this.accommodationSearch.endDate = this.toModel(this.endDate);
    //   this.accommodationSearch.additionalService = this.getSelectedOptions();
    //   this.accommodationSearch.advance = true;
    //   this.accomodationService.getAll().subscribe(
    //     s => this.accommodationToShow = s
    //   )
    // }
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

  beginBooking(accommodation: Accommodation){
    // let reservation : AccommodationReservation = new AccommodationReservation();
    // reservation.beginDate = new Date(this.accommodationSearch.beginDate)
    // reservation.endDate = new Date(this.accommodationSearch.endDate)
    // reservation.roomDTO = accommodation;
    
    // localStorage.setItem('beginDate', JSON.stringify(this.accommodationSearch.beginDate));
    // localStorage.setItem('endDate', JSON.stringify(this.accommodationSearch.endDate));
    // localStorage.setItem('reservation', JSON.stringify(reservation));
    // let res = localStorage.getItem('token');
    // if(res == null){
    //   this.router.navigate(['login']);
    // }
    // else{
    //   this.router.navigate(['bookAccommodation/' + accommodation.id]);
    // }
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

  checkIfInputIsValid(){
    if(this.accommodationSearch.grad == ""){
      alert("Enter your destination");
      return false;
    }
    if(this.beginDate == null){
      alert("Enter begin date of your trip");
      return false;
    }
    if(this.endDate == null){
      alert("Enter end date of your trip");
      return false;
    }
    if(this.accommodationSearch.brojOsoba == 0){
      alert("Enter number of people");
      return false;
    }
    return true;
  }

  convertFromRatingToCategory(rating: number): string{
    if(rating <1 || rating===undefined){
      return "NO STAR";
    }
    else if(rating <2){
      return "ONE STAR";
    }
    else if(rating <3){
      return "TWO STAR";
    }
    else if(rating <4){
      return "THREE STAR";
    }
    else if(rating <5){
      return "FOUR STAR";
    }
    else if(rating ==5){
      return "FIVE STAR";
    }
  }
}
