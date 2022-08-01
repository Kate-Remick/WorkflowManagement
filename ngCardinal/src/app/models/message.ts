import { NgIf } from "@angular/common";
import { Chat } from "./chat";
import { User } from "./user";

export class Message {

  id: number;
  createdAt: Date | null;
  content: string;
  chat: Chat | null;
  user: User | null;


  constructor(
    id: number = 0,
    createdAt: Date | null = null,
    content: string = '',
    chat: Chat | null = null,
    user: User | null = null
  ){
  this.id = id;
  this.createdAt = createdAt;
  this.content = content;
  this.chat = chat;
  this.user = user
  }
}
