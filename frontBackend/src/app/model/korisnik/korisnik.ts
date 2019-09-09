import { Poruka } from "./poruka";

export class Korisnik{

    public id: number;
	public ime: string;
	public prezime: string;
	public mejl: string;
	public lozinka: string;
    public status: string;
    
    public porukeSaAgentom: Poruka[]; 
    
    constructor(){
        this.id = 0;
        this.ime = "";
        this.prezime = "";
        this.mejl = "";
        this.lozinka = "";
        this.status = "";
        this.porukeSaAgentom = [];
    }
}