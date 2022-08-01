import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor() { }

  getCredentials(): string | null {
    return localStorage.getItem('credentials');
  }
}
