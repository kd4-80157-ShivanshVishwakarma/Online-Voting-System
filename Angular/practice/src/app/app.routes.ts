import { Routes } from '@angular/router';
import { ElectionComponent } from './components/election/election.component';
import { VoterComponent } from './components/voter/voter.component';
import { CandidateComponent } from './components/candidate/candidate.component';
import { CandidateElectionComponent } from './components/candidate-election/candidate-election.component';
import { VoterElectionCandidateComponent } from './components/voter-election-candidate/voter-election-candidate.component';
import { CanDeactivateGuard } from './guards/can-deactivate.guard';
import { CandidateStats } from './models/candidate-stats';
import { ElectionStatsComponent } from './components/election-stats/election-stats.component';
import { HomeComponent } from './components/home/home.component';

export const appRoutes: Routes = [
    { path: 'election', component: ElectionComponent, canDeactivate: [CanDeactivateGuard] },
  { path: 'voter', component: VoterComponent,canDeactivate: [CanDeactivateGuard] },
  { path: 'candidate', component: CandidateComponent,canDeactivate: [CanDeactivateGuard] },
  { path: 'candidate-election', component: CandidateElectionComponent },
  { path: 'voter-election-candidate', component: VoterElectionCandidateComponent },
  { path: 'stats', component: ElectionStatsComponent },
  { path: 'home', component: HomeComponent },
  { path: '', redirectTo: '/home', pathMatch: 'full' }, 
  { path: '**', redirectTo: '/home' },
  
];
