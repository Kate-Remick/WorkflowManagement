import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, catchError, throwError } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Chat } from '../models/chat';
import { Deck } from '../models/deck';
import { AuthService } from './auth.service';

@Injectable({
  providedIn: 'root'
})
export class DeckService {

  private url = environment.baseUrl + "api/decks";

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

  getDecksInWorkspace(id: number):Observable<Deck[]>{
    return this.http.get<Deck[]>(this.url + "/workspace/" + id, this.getHttpOptions()).pipe(
      catchError((err: any) => {
      console.log(err);
      return throwError(
        () => new Error(' error: ' + err)
      );
      })
    );
  }

  getDeckById(id: number):Observable<Deck>{
    return this.http.get<Deck>(this.url + "/" + id, this.getHttpOptions()).pipe(
      catchError((err: any) => {
      console.log(err);
      return throwError(
        () => new Error(' error: ' + err)
      );
      })
    );
  }

  createDeck(workspaceId: number, deck: Deck):Observable<Deck>{
    return this.http.post<Deck>(this.url + "/workspace/" + workspaceId, deck ,this.getHttpOptions()).pipe(
      catchError((err: any) => {
      console.log(err);
      return throwError(
        () => new Error(' error: ' + err)
      );
      })
    );
  }
  editDeck(id: number, deck: Deck):Observable<Deck>{
    return this.http.post<Deck>(this.url + "/" + id, deck ,this.getHttpOptions()).pipe(
      catchError((err: any) => {
      console.log(err);
      return throwError(
        () => new Error(' error: ' + err)
      );
      })
    );
  }
  deleteDeck(id: number):Observable<void>{
    return this.http.delete<void>(this.url + "/" + id,this.getHttpOptions()).pipe(
      catchError((err: any) => {
      console.log(err);
      return throwError(
        () => new Error(' error: ' + err)
      );
      })
    );
  }


}
