import { TipSmestaja } from "../global-parameters/tipSmestaja";
import { Adresa } from "../global-parameters/adresa";
import { Cenovnik } from "./cenovnik";
import { Usluga } from "../global-parameters/usluge";

export class Accommodation{

    id: number;
    opis: string;
    kapacitet: number;
    brojDanaZaOtkazivanje: number;
    ocena: number;
    kategorija: string;
    adresaDTO: Adresa;
    cenovnici: Cenovnik[];
    tipDTO: TipSmestaja;
    usluge: Usluga[];
    dozvoljenoOtkazivanje: boolean;

    constructor(){
        this.id = 0;
        this.opis = "";
        this.brojDanaZaOtkazivanje = 0;
        this.kapacitet = 0;
        this.tipDTO = new TipSmestaja();
        this.ocena = 0;
        this.kategorija = ""
        this.adresaDTO = new Adresa();
        this.cenovnici = [];
        this.usluge = [];
        this.dozvoljenoOtkazivanje = false;
    }
}