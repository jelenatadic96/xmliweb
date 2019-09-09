import { Component, OnInit } from '@angular/core';
import { Korisnik } from 'app/model/korisnik/korisnik';
import { Accommodation } from 'app/model/accommodation/accommodation';
import { TipSmestaja } from 'app/model/global-parameters/tipSmestaja';
import { Usluga } from 'app/model/global-parameters/usluge';
import { Router } from '@angular/router';
import { AuthService } from 'app/service/korisnik/auth.service';
import { KorisnikService } from 'app/service/korisnik/korisnik.service';
import { Agent } from 'app/model/korisnik/agent';
import { Image } from 'app/model/global-parameters/image';
import { AccommodationService } from 'app/service/accommodation.service';

@Component({
  selector: 'app-dodavanje-smestaja',
  templateUrl: './dodavanje-smestaja.component.html',
  styleUrls: ['./dodavanje-smestaja.component.css']
})
export class DodavanjeSmestajaComponent implements OnInit {

  dropdownSettingsAdditionalServices;
  dropdownSettingsAccommodationType;
  additionalServices: Usluga[] = [];
  accommodationTypes: TipSmestaja[] = [];
  additionalServicesDropDown: string[] = [];
  accommodationTypeDropDown: string[] = [];
  selectAdditionalServices: string[] = [];
  selectedAccommodationType: string;

  accommodation: Accommodation = new Accommodation();
  loggedUser: Agent = new Agent();

  selectedFiles: FileList;
  currentFileUpload: File;
  newImageUrl: string = "";

  constructor(private accommodationService: AccommodationService, private router: Router, private authService: AuthService, 
    private userService: KorisnikService) { 
    let res = localStorage.getItem('token');
  //   if(res != null){
  //     this.loggedUser.mejl = this.authService.getUsername(res);
  // }
}

  ngOnInit() {
    // this.userService.findByEmail(this.loggedUser.mejl).subscribe(
    //   e => {
    //     this.loggedUser = e;
    //   })
    if(localStorage.getItem('newAccommodation') != null){
      this.accommodation = JSON.parse(localStorage.getItem('newAccommodation'));
      this.accommodation.adresaDTO.zemlja
    }
    this.dropdownSettingsAdditionalServices = {
      singleSelection: false,
      itemsShowLimit: 3,
      allowSearchFilter: true
    };
    this.dropdownSettingsAccommodationType = {
      singleSelection: true,
      itemsShowLimit: 3,
      allowSearchFilter: true
    };

    this.accommodationService.findAllUsluge().subscribe(
      s => {
        this.additionalServices = s;
        let pom = [];
        s.forEach(element => {
          pom.push(element.naziv);
        })
        this.additionalServicesDropDown = pom;
      }
    )
    this.accommodationService.findAllTipovi().subscribe(
      s => {
        this.accommodationTypes = s;
        let pom = [];
        s.forEach(element => {
          pom.push(element.naziv);
        })
        this.accommodationTypeDropDown = pom;
      }
    )
   }

  managePrices(){  //pozove da sacuva
    if(this.inputOk()){
      this.accommodationTypes.forEach(element => {
        if(element.naziv == this.selectedAccommodationType){
          this.accommodation.tipDTO = element;
        }
      })

      this.additionalServices.forEach(additionalService => {
        this.selectAdditionalServices.forEach(stringAdditionalService => {
          if(additionalService.naziv == stringAdditionalService){
            this.accommodation.usluge.push(additionalService);
          }
        })
      })

      if(this.accommodation.brojDanaZaOtkazivanje == 0){
        this.accommodation.dozvoljenoOtkazivanje = false
      }
      else{
        this.accommodation.dozvoljenoOtkazivanje = true;
      }

      localStorage.setItem('newAccommodation', JSON.stringify(this.accommodation));
      this.router.navigate(['managePrices']);
    }
  }

  addPictureToAccommodation(){
    if(this.checkURL(this.newImageUrl)){
      let image = new Image();
      image.filePath = this.newImageUrl;
      //TODO: proveriti ovo
      this.accommodation.putanjaDoSlike = this.newImageUrl;
      this.accommodation.imageDTO.push(image);
    }
    else{
      alert("Url you entered does not contain image");
    }
    this.newImageUrl = "";
  }

  checkURL(url) {
    return(url.match(/\.(jpeg|jpg|gif|png)$/) != null);
  }

  inputOk(){

    if(this.accommodation.adresaDTO.zemlja == ""){
      alert("You must enter country for accommodation");
      return false;
    }
    if(this.accommodation.adresaDTO.grad == ""){
      alert("You must enter city for accommodation");
      return false;
    }
    // if(this.accommodation.adresaDTO.geografskaDuzina == 0){
    //   alert("You must enter longitude for accommodation");
    //   return false;
    // }
    // if(this.accommodation.adresaDTO.geografskaSirina == 0){
    //   alert("You must enter latitude for accommodation");
    //   return false;
    // }
    if(this.accommodation.kapacitet == 0){
      alert("You must enter capacity for accommodation");
      return false;
    }
    if(this.selectedAccommodationType == undefined || this.selectedAccommodationType == ""){
      alert("You must select accommodation type for accommodation");
      return false;
    }
    // if(this.accommodation.putanjaDoSlike.length == 0){
    //   alert("You must add at least one image for accommodation");
    //   return false;
    // }

    return true;
  }

  removeImage(image: Image){
    let pomImage: Image = new Image();
    this.accommodation.imageDTO.forEach(element => {
      if(element.filePath === image.filePath){
        pomImage = element;
      }
    })

    this.accommodation.imageDTO.splice(this.accommodation.imageDTO.indexOf(pomImage), 1)
  }
}

