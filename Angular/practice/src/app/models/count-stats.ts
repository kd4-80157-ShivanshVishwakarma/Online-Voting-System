
export class CountStats{
    totalVoterCount: number;
    electionVoterCount: number;

    constructor(totalVoterCount: number,electionVoterCount: number) {
        this.totalVoterCount = totalVoterCount;
        this.electionVoterCount = electionVoterCount;
    }
    
}