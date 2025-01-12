import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Election } from '../models/election';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ElectionService {

  private url = 'http://localhost:8080/elections';

    
      constructor(private http:HttpClient) { 
        
      }
    
      getElections(): Observable<Election[]> {
        return this.http.get<Election[]>(this.url+"/all");
      }

      addElection(election:Election): Observable<any> {
            return this.http.post<any>(this.url+"/create", election,{ responseType: 'text' as 'json' });
      }
          
      updateElection(id: number, election:Election): Observable<any> {
        return this.http.put<any>(this.url+`/update/${id}`,election,{responseType: 'text' as 'json'});
      }
          
      removeElection(id:number): Observable<any> {
        return this.http.delete<any>(this.url+`/remove/${id}`,{responseType: 'text' as 'json'});
      }
}
