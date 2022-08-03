import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, catchError, throwError } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Message } from '../models/message';
import { AuthService } from './auth.service';

@Injectable({
  providedIn: 'root'
})
export class MessageService {

  private url = environment.baseUrl + "api/messages";

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

  getMessagesInChat(id: number):Observable<Message[]>{
    return this.http.get<Message[]>(this.url + "/" + id, this.getHttpOptions()).pipe(
      catchError((err: any) => {
      console.log(err);
      return throwError(
        () => new Error(' error: ' + err)
      );
      })
    );
  }
  getMessagesInChatAfter(id: number, date: Date):Observable<Message[]>{
    return this.http.post<Message[]>(this.url + "/" + id + "/after", date,this.getHttpOptions()).pipe(
      catchError((err: any) => {
      console.log(err);
      return throwError(
        () => new Error(' error: ' + err)
      );
      })
    );
  }
  createMessage(id: number, message: Message):Observable<Message[]>{
    return this.http.post<Message[]>(this.url + "/" + id , message,this.getHttpOptions()).pipe(
      catchError((err: any) => {
      console.log(err);
      return throwError(
        () => new Error(' error: ' + err)
      );
      })
    );
  }
  editMessage(id: number, message: Message):Observable<Message[]>{
    return this.http.put<Message[]>(this.url + "/" + id , message,this.getHttpOptions()).pipe(
      catchError((err: any) => {
      console.log(err);
      return throwError(
        () => new Error(' error: ' + err)
      );
      })
    );
  }
  deleteMessage(id: number):Observable<void>{
    return this.http.put<void>(this.url + "/" + id ,this.getHttpOptions()).pipe(
      catchError((err: any) => {
      console.log(err);
      return throwError(
        () => new Error(' error: ' + err)
      );
      })
    );
  }
}
