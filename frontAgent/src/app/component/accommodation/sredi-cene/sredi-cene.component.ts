import { Component, OnInit } from '@angular/core';
import { Accommodation } from 'app/model/accommodation/accommodation';
import { Router } from '@angular/router';
import { AuthService } from 'app/service/korisnik/auth.service';
import { KorisnikService } from 'app/service/korisnik/korisnik.service';
import { Agent } from 'app/model/korisnik/agent';
import { Cenovnik } from 'app/model/accommodation/cenovnik';

@Component({
  selector: 'app-sredi-cene',
  templateUrl: './sredi-cene.component.html',
  styleUrls: ['./sredi-cene.component.css']
})
export class SrediCeneComponent implements OnInit {

  dropDownSettings;
  years: string[] = ["2019"/*, "2020", "2021", "2022", "2023"*/];
  priceList: Cenovnik[] = [];
  unitPriceList: Cenovnik[] = [];
  selectedYear: string;
  selectedPriceList: Cenovnik[] = [];
  accommodation: Accommodation;
  loggedUser: Agent = new Agent();
  id: number;

  constructor(/*private accommodationService: AccommodationService,*/ private router: Router, private authService: AuthService, private userService: KorisnikService) {
    let res = localStorage.getItem('token');
    if(res != null){
      this.loggedUser.mejl = this.authService.getUsername(res);
    }
    for(let i = 0; i < 12; i++){
      let newPriceList = new Cenovnik();
      let newUnitPriceList = new Cenovnik(); 
     // newPriceList.unitPriceInformationDTO.push(newUnitPriceList);
      this.selectedPriceList.push(newPriceList);
      this.selectedPriceList[0].cenaPoNoci
    }
   }

  ngOnInit() {
    // this.userService.findByEmail(this.loggedUser.mejl).subscribe(
    //   e => {
    //     this.loggedUser = e;
    //   });
    if(localStorage.getItem('newAccommodation') != null){
      this.accommodation = JSON.parse(localStorage.getItem('newAccommodation'));
    }
    this.dropDownSettings = {
      singleSelection: true,
      itemsShowLimit: 3,
      allowSearchFilter: true
    };

    this.years.forEach(element =>{
      for(let i = 1; i <= 12; ++i ){
          let newPriceList = new Cenovnik();
          newPriceList.prviDanVazenja = new Date(i + "-01-" + element);
          newPriceList.poslednjiDanVazenja = new Date(i + "-31-"+ element);
          let unitPrice = new Cenovnik;
          //unitPrice.roomDTO = this.accommodation;
          this.accommodation.cenovnici.push(newPriceList)
          //newPriceList.unitPriceInformationDTO.push(unitPrice);
          this.priceList.push(newPriceList);
        }
      }
    )
  }

  onSelectedYear(item: any){
    this.selectedPriceList = [];
    this.priceList.forEach(element =>{
      let year = element.prviDanVazenja.getFullYear().toString();
        if(year == this.selectedYear){
          this.selectedPriceList.push(element);
        }
      }
    )
  }

  saveAccommodation(){

    // this.accommodationService.create(this.loggedUser.id, this.accommodation).subscribe(
    //   s => {
    //     this.id = s.id;
    //      this.priceList.forEach(element =>{
    //        this.accommodationService.createPriceList(this.loggedUser.id, element).subscribe(
    //          ss => {
    //            this.accommodationService.getRoom(this.id).subscribe(
    //              room => {
    //                element.unitPriceInformationDTO[0].roomDTO = room;
    //                this.accommodationService.createPriceListUnit(ss.id, element.unitPriceInformationDTO[0]).subscribe(
    //                  sss => {
    //                  }
    //                )
    //              }
    //            )
    //          }
    //       )
    // //     })
    //     this.router.navigate(['address']);
    //   }
    // )
  }

}