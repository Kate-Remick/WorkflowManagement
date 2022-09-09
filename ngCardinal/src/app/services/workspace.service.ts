import { Workspace } from './../models/workspace';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, catchError, throwError } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Deck } from '../models/deck';
import { AuthService } from './auth.service';

@Injectable({
  providedIn: 'root'
})
export class WorkspaceService {

  private url = environment.baseUrl + "workspaces";

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

  getWorkspaceById(id: number): Observable<Workspace>{
    return this.http.get<Workspace>(this.url + "/" + id, this.getHttpOptions()).pipe(
      catchError((err: any)=>{
        console.log(err);
        return throwError(
          () => new Error('error:' + err)
        );
      })
    );
  }

  getManagedWorkspaces():Observable<Workspace[]>{
    return this.http.get<Workspace[]>(this.url + "/managed", this.getHttpOptions()).pipe(
      catchError((err: any) => {
      console.log(err);
      return throwError(
        () => new Error(' error: ' + err)
      );
      })
    );
  }
  getWorkspaces():Observable<Workspace[]>{
    return this.http.get<Workspace[]>(this.url, this.getHttpOptions()).pipe(
      catchError((err: any) => {
      console.log(err);
      return throwError(
        () => new Error(' error: ' + err)
      );
      })
    );
  }
  createWorkspace(workspace: Workspace):Observable<Workspace>{
    return this.http.post<Workspace>(this.url,workspace,  this.getHttpOptions()).pipe(
      catchError((err: any) => {
      console.log(err);
      return throwError(
        () => new Error(' error: ' + err)
      );
      })
    );
  }
  editWorkspace(workspace: Workspace, id: number):Observable<Workspace>{
    return this.http.put<Workspace>(this.url+ "/" + id,workspace,  this.getHttpOptions()).pipe(
      catchError((err: any) => {
      console.log(err);
      return throwError(
        () => new Error(' error: ' + err)
      );
      })
    );
  }
  deleteWorkspace( id: number):Observable<void>{
    return this.http.put<void>(this.url+ "/" + id,  this.getHttpOptions()).pipe(
      catchError((err: any) => {
      console.log(err);
      return throwError(
        () => new Error(' error: ' + err)
      );
      })
    );
  }

  setCurrentWorkspace(workspace : Workspace): void{
    let key = 'currentWorkspace';
    localStorage.setItem(key, JSON.stringify(workspace) );
  }
  getCurrentWorkspace(): Workspace | null{
    let stringWorkspace =  localStorage.getItem('currentWorkspace');
    if(stringWorkspace){
      return JSON.parse(stringWorkspace);
    }
    return null;
  }
}
