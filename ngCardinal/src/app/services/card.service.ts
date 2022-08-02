import { AuthService } from './auth.service';
import { HttpClient } from '@angular/common/http';
import { environment } from 'src/environments/environment';
import { Injectable } from '@angular/core';
import { identifierName } from '@angular/compiler';
import { catchError, throwError, Observable } from 'rxjs';
import { Card } from '../models/card';

@Injectable({
  providedIn: 'root'
})
export class CardService {

  private url = environment.baseUrl + "api/cards";

  constructor(private http: HttpClient, private auth: AuthService) { }

  getHttpOptions() {
    let options = {
      headers: {
        Authorization: 'Basic ' + this.auth.getCredentials(),
        'X-Requested-With': 'XMLHttpRequest',
      },
    };
    return options;
  }

  getCredentials(): string | null {
    return localStorage.getItem('credentials');
  }

  getCardsInDeck(id: number):Observable<Card[]>{
    return this.http.get<Card[]>(this.url + "/" + id, this.getHttpOptions()).pipe(
      catchError((err: any) => {
      console.log(err);
      return throwError(
        () => new Error(' error: ' + err)
      );
      })
    );
  }
  getAssignedCards():Observable<Card[]>{
    return this.http.get<Card[]>(this.url + "/assigned", this.getHttpOptions()).pipe(
      catchError((err: any) => {
      console.log(err);
      return throwError(
        () => new Error(' error: ' + err)
      );
      })
    );
  }
  createCard(card : Card):Observable<Card>{
    return this.http.post<Card>(this.url,card, this.getHttpOptions()).pipe(
      catchError((err: any) => {
      console.log(err);
      return throwError(
        () => new Error(' error: ' + err)
      );
      })
    );
  }
  deleteCard(id: number):Observable<void>{
    return this.http.delete<void>(this.url + "/" + id, this.getHttpOptions()).pipe(
      catchError((err: any) => {
      console.log(err);
      return throwError(
        () => new Error(' error: ' + err)
      );
      })
    );
  }
  updateCard(card: Card, id : number):Observable<Card>{
    return this.http.put<Card>(this.url + "/" + id, card ,this.getHttpOptions()).pipe(
      catchError((err: any) => {
      console.log(err);
      return throwError(
        () => new Error(' error: ' + err)
      );
      })
    );
  }




}
