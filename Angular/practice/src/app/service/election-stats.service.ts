import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { CandidateStats } from '../models/candidate-stats';
import { Observable } from 'rxjs';
import { VoterStats } from '../models/voter-stats';
import { CountStats } from '../models/count-stats';

@Injectable({
  providedIn: 'root'
})
export class ElectionStatsService {

  private url = 'http://localhost:8080/';
    private http:HttpClient;
      constructor(private http1:HttpClient) { 
        this.http = http1;
      }
    
      getCandidateStats(electionId:number): Observable<CandidateStats[]> {
        return this.http.get<any[]>(this.url+`candidates/candidateStats/${electionId}`);
      }

      getVoterStats(voterId:number): Observable<VoterStats[]> {
        return this.http.get<any[]>(this.url+`voters/voter-stats/${voterId}`);
      }

      getCountStats(electionId:number): Observable<CountStats> {
        return this.http.get<any>(this.url+`elections/${electionId}/count-stats`);
      }
}