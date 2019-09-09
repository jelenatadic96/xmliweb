export class Utisak{ 
    /*
    private Long id;
    private String komentar;
	private boolean komentarOdobren;
    private int ocena;
    private Long rezervacijaId;
     */

     id:number;
     komentar: string;
     komentarOdobren: boolean;
     ocena: number;
     rezervacijaId: number

     constructor(){

        this.id = 0;
        this.komentar = "";
        this.komentarOdobren = false;
        this.ocena = 0;
        this.rezervacijaId = 0;

     }
}