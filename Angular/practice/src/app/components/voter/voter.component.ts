import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { VoterService } from '../../service/voter.service';
import { Voter } from '../../models/voter';
import { CommonModule } from '@angular/common';
import { NavigationStart, Router } from '@angular/router';
import { CanComponentDeactivate } from '../../guards/can-deactivate.guard';
import { Observable } from 'rxjs';


@Component({
  selector: 'app-voter',
  imports: [CommonModule,ReactiveFormsModule],
  standalone: true,
  templateUrl: './voter.component.html',
  styleUrl: './voter.component.css'
})
export class VoterComponent implements OnInit{

  voters: Voter[] = [];
  voterForm: FormGroup;
  isEditMode = false;
  isVisible = false;
  currentDate:string='';
  isformDirty:boolean=false;

  constructor(private frmBuildr:FormBuilder, private voterService: VoterService) {
    this.voterForm = this.frmBuildr.group({
      id:[''],
      name:['',[Validators.required, Validators.minLength, Validators.pattern(/^[A-Za-z]+$/)]],
      dob:['',Validators.required],
      gender:['',Validators.required],
      address:['',Validators.required],
      aadhar:['',[Validators.required,Validators.pattern(/^\d{12}$/)]]
    });
  }


  ngOnInit(): void {
    const date = new Date();
    this.currentDate = date.toISOString().split('T')[0];
    this.loadVoters(); 

    this.voterForm.valueChanges.subscribe(()=> {
      this.isformDirty = this.voterForm.dirty;
    })
  }

  canDeactivate(): boolean | Observable<boolean> | Promise<boolean> {
    
    if(this.voterForm.dirty) {
      return confirm("You have unsaved changes. Do you really want to leave?");
    }
    return true;
  }
 
  loadVoters(): void {
    this.voterService.getVoters().subscribe((data) => {
      this.voters = data;
    });
  }

  toggle():void {
    this.isVisible = true;
  }
  onSubmit(): void {
      const vtr: Voter = this.voterForm.value;
      this.voterService.addVoter(vtr).subscribe({
        next: () => {
          alert("Voter added successfully");
          this.voterForm.reset();
          this.isVisible = false;
          this.loadVoters();
        },
        error: () => alert('Failed to add voter, Try again.'),
      });
  }

  onEdit(voterId: number): void {
    this.toggle();
    this.isEditMode = true;
  
    const voterToEdit = this.voters.find((voter)=> voter.id == voterId);
    if(voterToEdit!=null && voterToEdit!=undefined) {
      this.voterForm.patchValue({
        id: voterToEdit.id,
        name: voterToEdit.name,
        dob: voterToEdit.dob,
        address: voterToEdit.address,
        aadhar: voterToEdit.aadhar,
        gender: voterToEdit.gender
      })
    }
  }

  onCancel():void {
    this.isEditMode = false;
    this.voterForm.reset();
    this.isVisible = false;
  }

  onUpdate():void {
    const updatedVoter:Voter = this.voterForm.value;
    const id = updatedVoter.id;
    this.voterService.updateVoter(id, updatedVoter).subscribe({
      next: () => {
        alert("Voter updated successfully");
        this.onCancel();
        this.isVisible = false;
        this.loadVoters();
      },
      error: () => alert('Failed to update voter, Try again.'),
    });
  }

  onRemove(id:number):void {
    const vid = id;
    this.voterService.removeVoter(vid).subscribe({
      next: () => {
        alert("Voter removed successfully");
        this.onCancel();
        this.loadVoters();
      },
      error: () => alert('Failed to remove record, Try again.'),
    });
  }
  

  
}
