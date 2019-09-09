export class Cenovnik{

    id: number;
    prviDanVazenja: string;
    poslednjiDanVazenja: string;
    cenaPoNoci: number;
    
    constructor(){
        this.id = 0;
        this.prviDanVazenja = "";
        this.poslednjiDanVazenja = "";
        this.cenaPoNoci = undefined;
    }
}