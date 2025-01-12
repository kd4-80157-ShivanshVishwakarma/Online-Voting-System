import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { map, Observable } from 'rxjs';
import { CandidateElection } from '../models/candidate-election';
import { Vote } from '../models/Vote';

@Injectable({
  providedIn: 'root'
})
export class VoterElectionCandidateService {

  http:HttpClient;
  private url:string = 'http://localhost:8080/voter-election-candidates'
  constructor(private httpClient:HttpClient) {
      this.http = httpClient;
   }

   addVoterElectionCandidate(vce:any): Observable<any> {
    const data = {
      voterId:vce.voter,
      electionId:vce.election,
      candidateElectionId:vce.candidateElection
    }
    return this.http.post<any>(this.url + '/cast',data,{responseType: 'text' as 'json'})
   }

   getCandidateElectionsByEid(electionId:number): Observable<CandidateElection[]> {
    return this.http.get<any[]>(this.url+`/candidates/${electionId}`).pipe(
      map((data: any[]) =>
        data.map((ce)=>({
          id:ce.id,
            candidateName: ce.candidate.name,
            electionName: ce.election.electionType
        }))
      )
    );
   }

   getVotes():Observable<Vote[]> {
    return this.http.get<any[]>(this.url+"/get/votes");
   }
}
