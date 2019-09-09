
export class Registracija{

    id: number;
    firstName: string;
	lastName: string;
	password: string;
    repeatPassword: string;
    workCertificateNumber: string;
    email: string;


    constructor(){
        this.id = 0;
        this.firstName = "";
        this.lastName = "";
        this.password = "";
        this.repeatPassword = "";
        this.workCertificateNumber = "";
        this.email = "";
    }

}