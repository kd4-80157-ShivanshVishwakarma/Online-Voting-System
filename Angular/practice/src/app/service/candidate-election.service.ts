import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { CandidateElection } from '../models/candidate-election';
import { response } from 'express';
import { map, Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class CandidateElectionService {
  private url = 'http://localhost:8080/candidatelections';
  private http:HttpClient;
    constructor(private http1:HttpClient) { 
      this.http = http1;
    }

    addCandidateElection(ce:{candidateId:number,electionId:number}): Observable<any> {
      return this.http.post<any>(this.url+'/create', ce,{responseType: 'text' as 'json'});
    }

    getCandidateElections(): Observable<CandidateElection[]> {
      return this.http.get<any[]>(this.url+"/all").pipe(
        map((data) =>
          data.map((ce) => ({
            id:ce.id,
            candidateName: ce.candidate.name,
            electionName: ce.election.electionType
          }))
        )
      );
    }
}
