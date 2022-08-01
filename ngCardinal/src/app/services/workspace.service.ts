import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class WorkspaceService {

  constructor() { }

  getCredentials(): string | null {
    return localStorage.getItem('credentials');
  }
}
