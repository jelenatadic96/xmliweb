import { Korisnik } from "./korisnik";
import { Agent } from "./agent";

export class Poruka{

    public id: number;
	public vreme: string;
    public sadrzaj: string;
   // public korisnikDTO: Korisnik;
    //public agentDTO: Agent;
    public korisnikId: number;
    public agentId: number;

    constructor(){
        this.id = 0;
        this.vreme = "";
        this.sadrzaj = "";
        // this.korisnikDTO = new Korisnik();
        // this.agentDTO = new Agent();
        this.korisnikId = 0;
        this.agentId = 0;
    }
}