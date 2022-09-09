import { CardService } from './../../services/card.service';
import { WorkspaceService } from './../../services/workspace.service';
import { ChatService } from './../../services/chat.service';
import { Router } from '@angular/router';
import { UserService } from './../../services/user.service';
import { AuthService } from './../../services/auth.service';
import { Component, OnInit } from '@angular/core';
import { User } from 'src/app/models/user';
import { Workspace } from 'src/app/models/workspace';
import { Chat } from 'src/app/models/chat';
import { Card } from 'src/app/models/card';

@Component({
  selector: 'app-user-profile',
  templateUrl: './user-profile.component.html',
  styleUrls: ['./user-profile.component.css']
})
export class UserProfileComponent implements OnInit {
  loggedInUser: User = new User();
  userChats: Chat [] = [];
  userWorkspaces: Workspace[] = [];
  userManagedWorkspaces: Workspace[] = [];
  assignments: Card[] = [];

  // ******************* Setup *********************************
  constructor(private authServ: AuthService, private userServ: UserService, private router: Router
    , private chatServ: ChatService, private workServ: WorkspaceService, private cardServ: CardService) { }

  ngOnInit(): void {
    this.getUser();
    this.getUserWorkspaces();
  }
  // ********************** Routing *****************************

  goToWorkspace(workspace: Workspace): void{
  }



  // ********************* Page Dynamics ***************************


  // ************************ Service Methods *************************

  getUser(): void{
    this.userServ.getLoggedInUser().subscribe({
      next: (user) => {
        this.loggedInUser = user;
        this.getUserChats();
        this.getAssignments();
      },
      error: (problem) => {
        console.error('Error Getting User');
        console.error(problem);
      }
    });
  }

  getUserChats(): void{
    this.chatServ.getUserChats().subscribe({
      next: (chats) => {
        this.userChats = chats;
      },
      error: (problem) => {
        console.error('Error getting user chats');
        console.error(problem);
      }
    })

  }

  getUserWorkspaces(): void{
    this.workServ.getWorkspaces().subscribe({
      next: (workspaces) => {
        this.userWorkspaces = workspaces;
        this.getUserManagedWorkspaces();
      },
      error: (problem) => {
        console.error('Error getting user workspaces');
        console.error(problem);
      }
    })
  }
  getUserManagedWorkspaces(): void{
    this.workServ.getManagedWorkspaces().subscribe({
      next: (workspaces) => {
        this.userManagedWorkspaces = workspaces;
      },
      error: (problem) => {
        console.error('Error getting user workspaces');
        console.error(problem);
      }
    })
  }
  getAssignments(): void{
    this.cardServ.getAssignedCards().subscribe({
      next: (assigned) => {
        this.assignments = assigned;

      },
      error: (problem)=> {
        console.error('error getting assigned cards');
        console.error(problem);
      }
    })
  }

}
