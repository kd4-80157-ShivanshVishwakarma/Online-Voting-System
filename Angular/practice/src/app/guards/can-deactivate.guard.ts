import { Injectable } from "@angular/core";
import {  CanDeactivate } from "@angular/router";
import { Observable } from "rxjs";
import { VoterComponent } from "../components/voter/voter.component";

export interface CanComponentDeactivate {
    canDeactivate():  boolean | Observable<boolean> | Promise<boolean>;
}

@Injectable({
    providedIn:'root'
})
export class CanDeactivateGuard implements CanDeactivate<VoterComponent> {

    canDeactivate(component: VoterComponent): boolean | Observable<boolean> | Promise<boolean> {
        return component.canDeactivate ? component.canDeactivate() : true;
    }
    
}   