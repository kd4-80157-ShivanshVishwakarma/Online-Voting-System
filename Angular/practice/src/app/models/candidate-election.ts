export class CandidateElection {
    id:number;
    candidateName:string;
    electionName:string;

    constructor(candidateName:string, electionName:string, id:number) {
        this.candidateName = candidateName;
        this.electionName = electionName; 
        this.id = id;
    }
    
}