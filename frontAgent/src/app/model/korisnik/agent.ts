import { Adresa } from "../global-parameters/adresa";

export class Agent{

    public id: number;
	public ime: string;
	public prezime: string;
	public mejl: string;
	public lozinka: string;
    public poslovniMaticniBroj: string;
    public adresaDTO: Adresa;
    
    constructor(){
        this.id = 0;
        this.ime = "";
        this.prezime = "";
        this.mejl = "";
        this.lozinka = "";
        this.poslovniMaticniBroj = "";
        this.adresaDTO = new Adresa();
    }
}