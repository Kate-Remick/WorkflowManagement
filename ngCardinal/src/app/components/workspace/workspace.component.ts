import { CardService } from './../../services/card.service';
import { identifierName } from '@angular/compiler';
import { DeckService } from './../../services/deck.service';
import { ChatService } from './../../services/chat.service';
import { WorkspaceService } from './../../services/workspace.service';
import { UserService } from './../../services/user.service';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { User } from 'src/app/models/user';
import { Workspace } from 'src/app/models/workspace';
import { AuthService } from 'src/app/services/auth.service';
import { Chat } from 'src/app/models/chat';
import { Deck } from 'src/app/models/deck';
import { Card } from 'src/app/models/card';

@Component({
  selector: 'app-workspace',
  templateUrl: './workspace.component.html',
  styleUrls: ['./workspace.component.css']
})
export class WorkspaceComponent implements OnInit {
  loggedInUser : User | null = null;
  workspace: Workspace | null = null;
  chats : Chat[] = [];
  userIsManager : boolean = false;

  newDeck: Deck | null = null;
  newCard: Card | null = null;
  editCard: Card | null = null;

  // ********************** Setup *********************************

  constructor(private authServ: AuthService, private router: Router, private userServ: UserService,
    private workServ: WorkspaceService, private chatServ: ChatService,
    private deckServ: DeckService, private cardServ: CardService) { }

  ngOnInit(): void {
    this.getUser();
  }

  // ********************** Page Dynamics *************************
  startNewDeck(){
    this.newDeck = new Deck();
  }

  startNewCard(){
    this.newCard = new Card();
  }

  completeCard(card : Card){
    card.completed = true;
    this.updateCard(card);
  }
  startEditCard(card: Card){
    this.editCard = card;
  }
  moveCard(card: Card, deck: Deck){
    card.deck = deck;
    this.updateCard(card);
  }







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
    if(this.workspace?.manager?.id == this.loggedInUser?.id){
      this.userIsManager = true;
    }
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

  addDeck(): void{
    if(this.workspace && this.newDeck){
      this.deckServ.createDeck(this.workspace?.id, this.newDeck).subscribe({
        next: (deck)=> {
          this.newDeck = null;
          this.updateWorkspace();
        }
      })
    }
  }

  addCard(): void{
    if(this.newCard){
      this.cardServ.createCard(this.newCard).subscribe({
        next: (card) => {
          this.newCard = null;
          this.updateWorkspace();
        },
        error: (problem) => {
          console.error('error creating new card');
          console.error(problem);
        }
      });
    }
  }

  updateCard(card : Card): void{
    this.cardServ.updateCard(card, card.id).subscribe({
      next: (card) => {
        this.editCard = null;
        this.updateWorkspace();
      },
      error: (problem) => {
        console.error('error updating card');
        console.error(problem);
      }
    })
  }
  deleteCard(card: Card): void{
    this.cardServ.deleteCard(card.id).subscribe({
      next: () => {
        this.updateWorkspace();
      },
      error: (problem) => {
        console.error('error deleting card');
        console.error(problem);
      }
    })
  }

  updateWorkspace():void{
    if(this.workspace){
      this.workServ.getWorkspaceById(this.workspace?.id).subscribe({
        next: (updated) => {
          this.workServ.setCurrentWorkspace(updated);
          this.workspace = this.workServ.getCurrentWorkspace();
        },
        error: (problem) => {
          console.error("error updating workspace");
          console.error(problem);
        }
      })
    }
  }


}
