import { Workspace } from './../../models/workspace';
import { Message } from './../../models/message';
import { Chat } from './../../models/chat';
import { MessageService } from './../../services/message.service';
import { ChatService } from './../../services/chat.service';
import { Component, OnInit } from '@angular/core';
import { User } from 'src/app/models/user';
import { AuthService } from 'src/app/services/auth.service';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-chat',
  templateUrl: './chat.component.html',
  styleUrls: ['./chat.component.css']
})
export class ChatComponent implements OnInit {
  loggedInUser: User = new User();
  chats: Chat[] = [];
  messages: Message [] = [];
  workspace: Workspace | null = null;

  //************************************* Setup ************************************************ */
  constructor(private authServ: AuthService, private userServ: UserService
    , private chatServ: ChatService, private messageServ: MessageService) { }

  ngOnInit(): void {
    this.getUser();
  }
  //****************************** Page Dynamics Methods ***************************************/


  //****************************** Service Methods ***************************************/
  getUser(){
    this.userServ.getLoggedInUser().subscribe({
      next: (user) => {
        this.loggedInUser = user;
      },
      error: (problem) => {
        console.error('HttpComponent.loadProducts(): error loading products:');
        console.error(problem);
      }
    });
  }
  getUserChats(){
    this.chatServ.getUserChats().subscribe({
      next: (chats) => {
        this.chats = chats;
      },
      error: (problem) => {
        console.error('HttpComponent.loadProducts(): error loading products:');
        console.error(problem);
      }
    });
  }
  getWorkspaceChats(id: number){
    this.chatServ.getChatsInWorkspace(id ).subscribe({
      next: (chats) => {
        this.chats = chats;
      },
      error: (problem) => {
        console.error('HttpComponent.loadProducts(): error loading products:');
        console.error(problem);
      }
    });
  }
}
