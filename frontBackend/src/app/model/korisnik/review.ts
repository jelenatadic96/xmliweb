
import { AccommodationReservation } from "../accommodation/accommodationReservation";
import { Accommodation } from "../accommodation/accommodation";

export class Review{

    /*
    private Long id;
    private String komentar;
	private boolean komentarOdobren;
    private int ocena;
    private Long rezervacijaId;
    */
    id: number;
    ocena: number;
    komentar: string;
    komentarOdobren: boolean;
    rezervacijaId: number;

    constructor(){
        this.id = 0;
        this.ocena = 0;
        this.komentar = "";
        this.komentarOdobren = false;
        this.rezervacijaId = 0;
    }
}