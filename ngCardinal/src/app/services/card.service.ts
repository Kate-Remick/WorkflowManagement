import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class CardService {

  constructor() { }

  getCredentials(): string | null {
    return localStorage.getItem('credentials');
  }
}
