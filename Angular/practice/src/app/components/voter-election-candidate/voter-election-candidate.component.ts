import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { VoterService } from '../../service/voter.service';
import { ElectionService } from '../../service/election.service';
import { CandidateElectionService } from '../../service/candidate-election.service';
import { Voter } from '../../models/voter';
import { Election } from '../../models/election';
import { CandidateElection } from '../../models/candidate-election';
import { VoterElectionCandidateService } from '../../service/voter-election-candidate.service';
import { Vote } from '../../models/Vote';

@Component({
  selector: 'app-voter-election-candidate',
  imports: [CommonModule,ReactiveFormsModule],
  templateUrl: './voter-election-candidate.component.html',
  styleUrl: './voter-election-candidate.component.css'
})
export class VoterElectionCandidateComponent implements OnInit {
  
  voterElectionCandidateForm: FormGroup;
  voters:Voter[] = [];
  elections:Election[] = [];
  candidateElections:CandidateElection[] = [];
  votes:Vote[] = [];
  showElectionError:boolean = false;

  


  constructor(private frmBuildr:FormBuilder,private voterService:VoterService,private electionService:ElectionService,private candidateElectionService:CandidateElectionService,private voterElectionCandidateService:VoterElectionCandidateService) {
    this.voterElectionCandidateForm = this.frmBuildr.group({
      voter:['', Validators.required],
      election:['', Validators.required],
      candidateElection:['', Validators.required]
    })
  } 

  ngOnInit(): void {
    this.loadVoterElectionCandidates();
    this.loadElections();
    this.loadVoters();
  }

  loadVoters(): void {
    this.voterService.getVoters().subscribe((data) => {
      this.voters = data;
    });
  }

  loadElections(): void {
    this.electionService.getElections().subscribe((data) => {
      this.elections = data;
    });
  }
  onElectionChange(event: Event): void {
    const election = (event.target as HTMLSelectElement).value;

    if (election) {
        this.showElectionError = false; 
        this.loadCandidateElections(); 
    } else {
        this.showElectionError = true;
    }
}

  loadCandidateElections(): void {
    const eid =  this.voterElectionCandidateForm.value.election;
    if(eid){
      
      this.voterElectionCandidateService.getCandidateElectionsByEid(eid).subscribe(
        {
          next: (data) => {
            this.candidateElections = data; 
          },
          error: (err) => { 
            console.error('Error fetching candidate elections:', err);
            
          }
        }
      );
    }
  }

  loadVoterElectionCandidates(): void {
    this.voterElectionCandidateService.getVotes().subscribe(
      {
        next: (data) => {
          data.sort((a, b) => a.id - b.id);
          this.votes = data; 
        },
        error: (err) => { 
          console.error('Error fetching candidate elections:', err);
          
        }
      }
    );
  }

  onSubmit(): void {
    if(this.voterElectionCandidateForm.valid) {
      let vce:{voter:number,election:number,candidateElection:number}= this.voterElectionCandidateForm.value;
      if(vce.candidateElection !=null && vce.election !=null && vce.voter !=null){
        this.voterElectionCandidateService.addVoterElectionCandidate(vce).subscribe({
          next: (response:string) => {
            alert(response);
            // this.voterElectionCandidateForm.reset();
            this.loadVoterElectionCandidates();
          },
          error: () => alert('Voting Failed, Try again.'),
        });
      }
    }
  }

}
