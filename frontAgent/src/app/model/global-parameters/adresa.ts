export class Adresa{

    id: number;
    zemlja: string;
    grad: string;
    geografskaDuzina: number;
    geografskaSirina: number;

    constructor(){
        this.id = 0;
        this.zemlja = "";
        this.grad = "";
        this.geografskaDuzina = 0;
        this.geografskaSirina = 0;
    }

}