import { Component, OnInit } from '@angular/core';
import { TipSmestaja } from 'app/model/global-parameters/tipSmestaja';
import { Usluga } from 'app/model/global-parameters/usluge';
import { AccommodationService } from 'app/service/accommodation/accommodation.service';

@Component({
  selector: 'app-upravljanje-katalogom',
  templateUrl: './upravljanje-katalogom.component.html',
  styleUrls: ['./upravljanje-katalogom.component.css']
})
export class UpravljanjeKatalogomComponent implements OnInit {

  accommodationTypes: TipSmestaja[] = []
  additionalServices: Usluga[] = []
  newAccommodationType: TipSmestaja = new TipSmestaja();
  newAdditionalService: Usluga = new Usluga();
  p1:any;
  p2:any;
  constructor(private accommodationService: AccommodationService) {
   }

  ngOnInit() {
    this.accommodationService.getAllAccomodationTypes().subscribe(
      s =>{
        this.accommodationTypes = s;
      }
    )
    this.accommodationService.getAllAddiionalServices().subscribe(
      s =>{
        this.additionalServices = s;
      }
    )
  }

  addAccommodationType(){
    if(this.newAccommodationType.id == 0){
      this.accommodationService.createAccommodationType(this.newAccommodationType).subscribe(
        s => {
          this.accommodationTypes.push(s);
        }
      )
    }
    else{
      this.accommodationService.updateAccomodationType(this.newAccommodationType).subscribe(
        s => { 
          this.accommodationTypes.forEach(element =>{
            if(element.id == s.id){
              element.naziv = s.naziv;
              element.opis = s.opis
            }
          }
        )}
      )
    }
    this.newAccommodationType = new TipSmestaja();
  }

  deleteAccommodationType(accommodationType: TipSmestaja){
    this.accommodationService.deleteAccommodationType(accommodationType.id).subscribe(
      s => {
          let pomAccommodationType: TipSmestaja = new TipSmestaja();
          this.accommodationTypes.forEach(element => {
            if(element.id === accommodationType.id){
              pomAccommodationType = element;
            }
          })

          this.accommodationTypes.splice(this.accommodationTypes.indexOf(pomAccommodationType), 1)
      }
    )
  }

  editAccommodationType(accommodationType: TipSmestaja){
    this.newAccommodationType.naziv = accommodationType.naziv;
    this.newAccommodationType.id = accommodationType.id;
    this.newAccommodationType.opis = accommodationType.opis;
  }

  addAdditionalService(){
    if(this.newAdditionalService.id == 0){
      this.accommodationService.createAdditionalService(this.newAdditionalService).subscribe(
        s => {
          this.additionalServices.push(s);          
        }
      )
    }
    else{
      this.accommodationService.updateAddiionalService(this.newAdditionalService).subscribe(
        s => {
          this.additionalServices.forEach(element =>{
            if(element.id == s.id){
              element.naziv = s.naziv;
              element.opis = s.opis
            }
          }
        )        
        }
      )
    }
    this.newAdditionalService = new Usluga();
  }

  deleteAdditionalService(additionalService: Usluga){
    this.accommodationService.deleteAdditionalService(additionalService.id).subscribe(
      s => {
        let pomAdditionalService: Usluga = new Usluga();
        this.additionalServices.forEach(element => {
          if(element.id === additionalService.id){
            pomAdditionalService = element;
          }
        })
    
        this.additionalServices.splice(this.additionalServices.indexOf(pomAdditionalService), 1)
      }
    )
  }

  editAdditionalService(additionalService: Usluga){
    this.newAdditionalService.id = additionalService.id;
    this.newAdditionalService.naziv = additionalService.naziv;
    this.newAdditionalService.opis = additionalService.opis;
  }

}
