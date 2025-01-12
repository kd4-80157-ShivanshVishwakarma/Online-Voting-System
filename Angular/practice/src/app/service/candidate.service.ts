import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Candidate } from '../models/candidate';

@Injectable({
  providedIn: 'root'
})
export class CandidateService {

  private url = 'http://localhost:8080/candidates';
  private http:HttpClient;
    constructor(private http1:HttpClient) { 
      this.http = http1;
    }
  
    getCandidates(): Observable<Candidate[]> {
      return this.http.get<Candidate[]>(this.url+"/all");
    }

    addCandidate(candidate:Candidate): Observable<any> {
      return this.http.post<any>(this.url+"/create", candidate,{ responseType: 'text' as 'json' });
    }
    
    updateCandidate(id: number, candidate:Candidate): Observable<any> {
      return this.http.put<any>(this.url+`/update/${id}`,candidate,{responseType: 'text' as 'json'});
    }
    
    removeCandidate(id:number): Observable<any> {
      return this.http.delete<any>(this.url+`/remove/${id}`,{responseType: 'text' as 'json'});
    }
}
