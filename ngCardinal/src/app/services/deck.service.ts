import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class DeckService {

  constructor() { }

  getCredentials(): string | null {
    return localStorage.getItem('credentials');
  }
}
