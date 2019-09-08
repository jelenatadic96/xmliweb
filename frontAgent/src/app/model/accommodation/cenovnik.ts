export class Cenovnik{

    id: number;
    prviDanVazenja: Date;
    poslednjiDanVazenja: Date;
    cenaPoNoci: number;
    
    constructor(){
        this.id = 0;
        this.prviDanVazenja = new Date();
        this.poslednjiDanVazenja = new Date();
        this.cenaPoNoci = 0;
    }
}