import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { Candidate } from '../../models/candidate';
import { CandidateService } from '../../service/candidate.service';
import { ElectionService } from '../../service/election.service';
import { Election } from '../../models/election';
import { CandidateElectionService } from '../../service/candidate-election.service';
import { CandidateElection } from '../../models/candidate-election';

@Component({
  selector: 'app-candidate-election',
  imports: [CommonModule,ReactiveFormsModule],
  templateUrl: './candidate-election.component.html',
  styleUrl: './candidate-election.component.css'
})
export class CandidateElectionComponent implements OnInit {
    candidateElectionForm!: FormGroup;
    candidates:Candidate[] = [];
    elections: Election[] = [];
    candidateElections: CandidateElection[] = [];

    constructor(private frmBuildr: FormBuilder,private candidateService: CandidateService,private electionService:ElectionService,private candidateElectionService: CandidateElectionService){
      this.candidateElectionForm = this.frmBuildr.group({
          candidateId:['',Validators.required],
          electionId:['',Validators.required]
      });
    }
  ngOnInit(): void {
    this.loadCandidates(); 
    this.loadElections();
    this.loadCandidateElections();
  }

  loadCandidates(): void {
    this.candidateService.getCandidates().subscribe((data) => {
      this.candidates = data;
    });
  }

  loadElections(): void {
    this.electionService.getElections().subscribe((data) => {
      this.elections = data;
    });
  }

  onSubmit(): void {
    if(this.candidateElectionForm.valid) {
      const ce:{candidateId:number,electionId:number}= this.candidateElectionForm.value;
      if(ce.candidateId !=null && ce.electionId !=null){
        this.candidateElectionService.addCandidateElection(ce).subscribe({
          next: (response:string) => {
            alert(response);
            this.candidateElectionForm.reset();
            this.loadCandidateElections();
          },
          error: () => alert('Registration Failed, Try again.'),
        });
      }
    }
  }

  loadCandidateElections(): void {
    this.candidateElectionService.getCandidateElections().subscribe(
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
