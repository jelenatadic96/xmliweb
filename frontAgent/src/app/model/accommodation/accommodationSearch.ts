import { Usluga } from "../global-parameters/usluge";
import { TipSmestaja } from "../global-parameters/tipSmestaja";

export class AccommodationSearch{

    grad: string;
    zemlja: string;
	prviDan: string;
	poslednjiDan: string;
	brojOsoba: number;
	kategorija: string;
    uslugeDTO: Usluga[];
    tipSmestajaDTO: TipSmestaja;

    
    constructor(){
        this.grad = "";
        this.zemlja = "";
        this.prviDan = "";
        this.poslednjiDan = "";
        this.brojOsoba = 0;
        this.kategorija = "";
        this.uslugeDTO = [];
        this.tipSmestajaDTO = new TipSmestaja();
    }
	
}