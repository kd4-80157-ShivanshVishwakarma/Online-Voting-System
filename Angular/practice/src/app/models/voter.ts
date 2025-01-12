export class Voter {
    id:number;
    name: string;
    dob:Date;
    gender:'Male' | 'Female' | 'Other';
    address:string;
    aadhar:number;

    constructor(id:number,name:string,dob:Date,gender:'Male' | 'Female' | 'Other',address:string,aadhar:number){
        this.id = id;
        this.name = name;
        this.dob = dob;
        this.gender = gender;
        this.address = address;
        this.aadhar = aadhar;
    }
    
}
