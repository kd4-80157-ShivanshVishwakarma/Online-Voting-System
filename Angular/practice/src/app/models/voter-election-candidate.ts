import { CandidateElection } from "./candidate-election";

export class VoterElectionCandidate {
    id:number;
    voterId:number;
    candidateElection:CandidateElection;
    electionId:number;

    constructor(candidateId:number, electionId:number, id:number,voteId:number,candidateElection:CandidateElection) {
        this.candidateElection = candidateElection;
        this.electionId = electionId; 
        this.id = id;
        this.voterId = voteId;
    }
}