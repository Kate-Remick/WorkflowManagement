import { Router } from '@angular/router';
import { UserService } from './../../services/user.service';
import { WorkspaceService } from './../../services/workspace.service';
import { Component, OnInit } from '@angular/core';
import { Deck } from 'src/app/models/deck';
import { User } from 'src/app/models/user';
import { Workspace } from 'src/app/models/workspace';

@Component({
  selector: 'app-workspace-builder',
  templateUrl: './workspace-builder.component.html',
  styleUrls: ['./workspace-builder.component.css']
})
export class WorkspaceBuilderComponent implements OnInit {
  newWorkspace: Workspace  = new Workspace();
  loggedInUser: User = new User();
  invited : string [] = [];
  decks: Deck [] = [];




  // ****************************** Setup ************************

  constructor(private workServ: WorkspaceService, private userServ: UserService,
    private router: Router) { }

  ngOnInit(): void {
  }

  // ************************ Page Dynamics *********************
  addDeck(deck: Deck): void{
    this.decks.push(deck);
  }
  removeDeck(deck: Deck): void{
    for(let i = 0; i < this.decks.length; i++){
      if(this.decks[i].id == deck.id){
        this.decks.splice(i,1);
        break;
      }
    }
  }




  // ************************ Service Methods ***********************

  createWorkspace(): void{
    this.workServ.createWorkspace(this.newWorkspace).subscribe({
      next: (workspace) => {
        this.workServ.setCurrentWorkspace(workspace);
        this.router.navigateByUrl('/workspace');
      },
      error: (problem) => {
        console.error('Error creating workspace');
        console.error(problem);
      }
    })
  }

}
