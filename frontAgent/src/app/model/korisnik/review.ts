
import { AccommodationReservation } from "../accommodation/accommodationReservation";
import { Accommodation } from "../accommodation/accommodation";

export class Review{
    id: number;
    ocena: number;
    komentar: string;
    timeStamp: Date;
    roomDTO: Accommodation;
    //systemUserInfoDTO: Korisnik;
    komentarOdobren: boolean;
    rezervacijaDTO: AccommodationReservation;

    constructor(){
        this.id = 0;
        this.ocena = 0;
        this.komentar = "";
        this.timeStamp = new Date();
        this.komentarOdobren = false;
        this.rezervacijaDTO = new AccommodationReservation();
        this.roomDTO = new Accommodation();
    }
}