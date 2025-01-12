export class Election {
    id:number;
    electionType:string;
    electionDate:Date;

    constructor(id:number, electionType:string, electionDate:Date) {
        this.id = id;
        this.electionType = electionType;
        this.electionDate = electionDate;
    }
    
}
