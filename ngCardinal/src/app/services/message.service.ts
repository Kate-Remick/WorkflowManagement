import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class MessageService {

  constructor() { }

  getCredentials(): string | null {
    return localStorage.getItem('credentials');
  }
}
