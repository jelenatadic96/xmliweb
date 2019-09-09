import { Adresa } from "../global-parameters/adresa";

export class Registracija{

    id: number;
    firstName: string;
	lastName: string;
	password: string;
    repeatPassword: string;
    workCertificateNumber: string;
    adresaDTO: Adresa;
    email: string;


    constructor(){
        this.id = 0;
        this.firstName = "";
        this.lastName = "";
        this.password = "";
        this.repeatPassword = "";
        this.workCertificateNumber = "";
        this.email = ""; 
        this.adresaDTO = new Adresa();
    }

}