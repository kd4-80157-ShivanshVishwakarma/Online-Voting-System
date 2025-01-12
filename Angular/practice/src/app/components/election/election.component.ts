import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { Election } from '../../models/election';
import { ElectionService } from '../../service/election.service';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';

@Component({
  selector: 'app-election',
  imports: [CommonModule,ReactiveFormsModule],
  standalone: true,
  templateUrl: './election.component.html',
  styleUrl: './election.component.css'
})
export class ElectionComponent implements OnInit{
    elections: Election[] = [];
    electionForm: FormGroup;
    isEditMode = false;
    isVisible = false;

        constructor(private frmBuildr:FormBuilder, private electionService: ElectionService ) {
          this.electionForm = this.frmBuildr.group({
                      id:[''],
                      electionType:['',[Validators.required, Validators.minLength, Validators.pattern(/^[A-Za-z]+$/)]],
                      electionDate:['',Validators.required],
          });
        }
      
      
        ngOnInit(): void {
          this.loadElections(); 
        }
       
        toggle():void {
          this.isVisible = true;
        }
        loadElections(): void {
          this.electionService.getElections().subscribe((data) => {
            this.elections = data;
          });
        }
        onSubmit(): void {
                
          const e: Election = this.electionForm.value;
          this.electionService.addElection(e).subscribe({
            next: () => {
              alert("Election added successfully");
              this.electionForm.reset();
              this.isVisible = false;
              this.loadElections();
            },
            error: () => alert('Failed to create Election, Try again.'),
          });
        }
            
        onEdit(eId: number): void {
          this.toggle();
          this.isEditMode = true;
        
          const electionToEdit = this.elections.find((e)=> e.id == eId);
          if(electionToEdit!=null && electionToEdit!=undefined) {
            this.electionForm.patchValue({
              id: electionToEdit.id,
              electionType: electionToEdit.electionType,
              electionDate: electionToEdit.electionDate
            })
          }
        }
            
        onCancel():void {
          this.isEditMode = false;
          this.electionForm.reset();
          this.isVisible = false;
        }
            
        onUpdate():void {
          const updateElection:Election = this.electionForm.value;
          const id = updateElection.id;
          this.electionService.updateElection(id, updateElection).subscribe({
            next: () => {
              alert("Election updated successfully");
              this.onCancel();
              this.isVisible = false;
              this.loadElections();
            },
            error: () => alert('Failed to update Election, Try again.'),
          });
        }
            
        onRemove(id:number):void {
          const eid = id;
          this.electionService.removeElection(eid).subscribe({

            next: () => {
              alert("Election removed successfully");
              this.onCancel();
              this.loadElections();
            },
            error: () => alert('Failed to remove record, Try again.'),
          });
        }

        
}
