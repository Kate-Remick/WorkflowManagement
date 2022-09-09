import { ChatService } from './../../services/chat.service';
import { WorkspaceService } from './../../services/workspace.service';
import { UserService } from './../../services/user.service';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { User } from 'src/app/models/user';
import { Workspace } from 'src/app/models/workspace';
import { AuthService } from 'src/app/services/auth.service';
import { Chat } from 'src/app/models/chat';

@Component({
  selector: 'app-workspace',
  templateUrl: './workspace.component.html',
  styleUrls: ['./workspace.component.css']
})
export class WorkspaceComponent implements OnInit {
  loggedInUser : User | null = null;
  workspace: Workspace | null = null;
  chats : Chat[] = [];

  // ********************** Setup *********************************

  constructor(private authServ: AuthService, private router: Router, private userServ: UserService,
    private workServ: WorkspaceService, private chatServ: ChatService) { }

  ngOnInit(): void {
    this.getUser();
  }

  // ********************** Page Dynamics *************************

  //************************ Service Methods *********************** */

  getUser():void{
    this.userServ.getLoggedInUser().subscribe({
      next: (user) => {
        this.loggedInUser = user;
        this.getWorkspace();
      },
      error: (problem) => {
        console.error('HttpComponent.loadProducts(): error loading products:');
        console.error(problem);
      }
    });
  }

  getWorkspace():void{
    this.workspace = this.workServ.getCurrentWorkspace();
  }

  getChats():void{
    if(this.workspace){
      this.chatServ.getChatsInWorkspace(this.workspace.id).subscribe({
        next: (chats) => {
          this.chats = chats;
        },
        error: (problem) => {
          console.error('error getting chats');
          console.error(problem);
        }
      })
    }
  }



}
