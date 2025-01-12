import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { CandidateService } from '../../service/candidate.service';
import { Candidate } from '../../models/candidate';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';

@Component({
  selector: 'app-candidate',
  imports: [CommonModule,ReactiveFormsModule],
  standalone: true,
  templateUrl: './candidate.component.html',
  styleUrl: './candidate.component.css'
})
export class CandidateComponent implements OnInit {

  candidates: Candidate[] = [];
  candidateForm: FormGroup;
  isEditMode = false;
  isVisible = false;
  currentDate:string='';
  
    constructor(private frmBuildr:FormBuilder, private candidateService: CandidateService ) {
      this.candidateForm = this.frmBuildr.group({
            id:[''],
            name:['',[Validators.required, Validators.minLength, Validators.pattern(/^[A-Za-z]+$/)]],
            dob:['',Validators.required],
            gender:['',Validators.required],
            address:['',Validators.required],
            aadhar:['',[Validators.required,Validators.pattern(/^\d{12}$/)]],
            party:['',Validators.required]
          });
    }
  
  
    ngOnInit(): void {
      const date = new Date();
      this.currentDate = date.toISOString().split('T')[0];
      this.loadCandidates(); 
    }
   
    loadCandidates(): void {
      this.candidateService.getCandidates().subscribe((data) => {
        this.candidates = data;
      });
    }

    toggle():void {
      this.isVisible = true;
    }

    onSubmit(): void {
        
          const cand: Candidate = this.candidateForm.value;
          this.candidateService.addCandidate(cand).subscribe({
            next: () => {
              alert("Candidate added successfully");
              this.candidateForm.reset();
              this.isVisible = false;
              this.loadCandidates();
            },
            error: () => alert('Failed to create Candidate, Try again.'),
          });
      }
    
      onEdit(candId: number): void {
        this.toggle();
        this.isEditMode = true;

        const candToEdit = this.candidates.find((cand)=> cand.id == candId);
        if(candToEdit!=null && candToEdit!=undefined) {
          this.candidateForm.patchValue({
            id: candToEdit.id,
            name: candToEdit.name,
            dob: candToEdit.dob,
            address: candToEdit.address,
            aadhar: candToEdit.aadhar,
            gender: candToEdit.gender,
            party: candToEdit.party
          })
        }
      }
    
      onCancel():void {
        this.isEditMode = false;
        this.candidateForm.reset();
        this.isVisible = false;
      }
    
      onUpdate():void {
        const updateCandidate:Candidate = this.candidateForm.value;
        const id = updateCandidate.id;
        this.candidateService.updateCandidate(id, updateCandidate).subscribe({
          next: () => {
            alert("Candidate updated successfully");
            this.onCancel();
            this.isVisible = false;
            this.loadCandidates ();
          },
          error: () => alert('Failed to update candidate, Try again.'),
        });
      }
    
      onRemove(id:number):void {
        const cid = id;
        this.candidateService.removeCandidate(cid).subscribe({
          next: () => {
            alert("Candidate removed successfully");
            this.onCancel();
            this.loadCandidates();
          },
          error: () => alert('Failed to remove record, Try again.'),
        });
      }

}
