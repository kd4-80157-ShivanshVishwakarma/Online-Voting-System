import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { catchError, Observable, of } from 'rxjs';
import { Voter } from '../models/voter';
import { log } from 'console';

@Injectable({
  providedIn: 'root'
})
export class VoterService {

  private url = 'http://localhost:8080/voters';
  constructor(private http:HttpClient) { 
    
  }

  getVoters(): Observable<Voter[]> {
    return this.http.get<Voter[]>(this.url+"/all");
  }

  addVoter(voter: Voter): Observable<any> {
    return this.http.post<any>(this.url+"/register", voter,{ responseType: 'text' as 'json' });
  }

  updateVoter(id: number, voter: Voter): Observable<any> {
    return this.http.put<any>(this.url+`/update/${id}`,voter,{responseType: 'text' as 'json'});
  }

  removeVoter(id:number): Observable<any> {
    return this.http.delete<any>(this.url+`/remove/${id}`,{responseType: 'text' as 'json'});
  }
  
}
