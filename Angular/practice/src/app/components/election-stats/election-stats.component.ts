import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { ReactiveFormsModule } from '@angular/forms';
import { ElectionService } from '../../service/election.service';
import { Election } from '../../models/election';
import { CandidateStats } from '../../models/candidate-stats';
import { ElectionStatsService } from '../../service/election-stats.service';
import { VoterService } from '../../service/voter.service';
import { Voter } from '../../models/voter';
import { VoterStats } from '../../models/voter-stats';
import { CountStats } from '../../models/count-stats';

@Component({
  selector: 'app-election-stats',
  imports: [CommonModule,ReactiveFormsModule],
  templateUrl: './election-stats.component.html',
  styleUrl: './election-stats.component.css'
})
export class ElectionStatsComponent implements OnInit{

  elections:Election[] = [];
  voters:Voter[] = [];
  candidateStats:CandidateStats[] = [];
  voterStats:VoterStats[] = [];
  activeTab:string = "";
  countStats:CountStats = null;
  trackChanges:boolean = false;

  constructor(private electionService:ElectionService,private electionStatsService:ElectionStatsService,private voterService:VoterService) {
    
  }
  

  ngOnInit(): void {
    this.loadElections();
    this.loadVoters();
  }

  setActiveTab(tab:string):void {
    this.activeTab = tab;
    this.trackChanges = false;
  }
  
  loadElections(): void {
    this.electionService.getElections().subscribe((data) => {
      this.elections = data;
    });
  }
  
  loadVoters(): void {
    this.voterService.getVoters().subscribe((data) => {
      this.voters = data;
    });
  }

  onElectionChange(event:Event) : void {
    const target = event.target as HTMLSelectElement;
    const electionId:number = Number(target.value);
    this.trackChanges = true;

    if (this.trackChanges){

        if(this.activeTab=='electioncountstats'){
            this.electionStatsService.getCountStats(electionId).subscribe((data) => {
            this.countStats = data;
          });
        }
        this.electionStatsService.getCandidateStats(electionId).subscribe((data) => {
          this.candidateStats = data;
        });
    }
  }

  onVoterChange(event:Event) : void {
    const target = event.target as HTMLSelectElement;
    const voterId:number = Number(target.value);
    this.trackChanges = true

    if (this.trackChanges){
      this.electionStatsService.getVoterStats(voterId).subscribe((data) => {
      this.voterStats = data;
      });
    }
  }

  

  

  
  
}
