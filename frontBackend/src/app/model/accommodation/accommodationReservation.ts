import { Accommodation } from "./accommodation";
import { Utisak } from "../global-parameters/utisak";

export class AccommodationReservation{
    id: number;
    prviDanRezervacije: string;
    poslednjiDanRezervacije: string;
    realizovana: boolean;
    smestajnaJedinicaDTO: Accommodation;
    ukupnaCena: number;
    utisakId: number;
    utisak: Utisak;
    korisnikId: number;


    constructor(){
        this.id = 0;
        this.prviDanRezervacije = "";
        this.poslednjiDanRezervacije = "";
        this.realizovana = false;
        this.smestajnaJedinicaDTO = new Accommodation();
        this.ukupnaCena = 0;
        this.utisakId = 0;
        this.korisnikId = 0;
        this.utisak = new Utisak();
    }
}