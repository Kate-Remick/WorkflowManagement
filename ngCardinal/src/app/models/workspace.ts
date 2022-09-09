import { Chat } from "./chat";
import { Deck } from "./deck";
import { User } from "./user";

export class Workspace {

  id: number;
  name: string;
  description: string;
  createdAt: Date | null;
  goalDate: Date | null;
  active: boolean;
  users: User [];
  decks: Deck [];
  chats: Chat [];
  manager: User | null;

  constructor(
    id: number = 0,
    name: string = '',
    description: string = '',
    createdAt: Date | null = null,
    goalDate: Date | null = null,
    active: boolean = false,
    users: User [] = [],
    decks: Deck [] = [],
    chats: Chat [] = [],
    manager: User | null = null
    ){
    this.id = id;
    this.name = name;
    this.description = description;
    this.createdAt = createdAt;
    this.goalDate = goalDate;
    this.active = active;
    this.users = users;
    this.decks = decks;
    this.chats = chats;
    this.manager = manager;

  }
}
