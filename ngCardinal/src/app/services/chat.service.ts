import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, catchError, throwError } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Chat } from '../models/chat';
import { AuthService } from './auth.service';

@Injectable({
  providedIn: 'root'
})
export class ChatService {
  private url = environment.baseUrl + "api/chats";

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

  getChatsInWorkspace(id: number):Observable<Chat[]>{
    return this.http.get<Chat[]>(this.url + "/" + id, this.getHttpOptions()).pipe(
      catchError((err: any) => {
      console.log(err);
      return throwError(
        () => new Error(' error: ' + err)
      );
      })
    );
  }

  getUserChats():Observable<Chat[]>{
    return this.http.get<Chat[]>(this.url, this.getHttpOptions()).pipe(
      catchError((err: any) => {
      console.log(err);
      return throwError(
        () => new Error(' error: ' + err)
      );
      })
    );
  }

  leaveChat(chat: Chat): Observable<Chat>{
    return this.http.get<Chat>(this.url + "/leave", this.getHttpOptions()).pipe(
      catchError((err: any) => {
      console.log(err);
      return throwError(
        () => new Error(' error: ' + err)
      );
      })
    );
  }

  addUsers(chat: Chat, id: number): Observable<Chat>{
    return this.http.get<Chat>(this.url + "/" + id + "/addUsers", this.getHttpOptions()).pipe(
      catchError((err: any) => {
      console.log(err);
      return throwError(
        () => new Error(' error: ' + err)
      );
      })
    );
  }

}
