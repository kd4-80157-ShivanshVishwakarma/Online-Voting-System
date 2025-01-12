export class Vote {
        id: number;
        electionId: number;
        candidateId: number;
        voterId: number;
        voterName: string;
        candidateName: string;
        electionType: string; 

        constructor(id: number, electionId: number, candidateId: number, voterId: number, voterName: string, candidateName: string, electionType: string) {
            this.id = id;
            this.electionId = electionId;
            this.candidateId = candidateId;
            this.voterId = voterId;
            this.voterName = voterName;
            this.candidateName = candidateName;
            this.electionType = electionType;
            
        }
        
      
}