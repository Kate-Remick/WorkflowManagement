import { Deck } from './deck';
import { User } from './user';
export class Card {

  id: number;
  name: string;
  description: string;
  dueDate: Date | null;
  completed: boolean;
  completedAt: Date | null;
  deck: Deck | null;
  assignedUser: User | null;

  constructor(id : number = 0,
    name: string = '',
    description: string = '',
    completed: boolean = false,
    dueDate: Date | null = null,
    completedAt: Date | null = null,
    deck: Deck | null,
    assignedUser: User | null
    ){
    this.id = id;
    this.name = name;
    this.description = description;
    this.completed = completed;
    this.dueDate = dueDate;
    this.completedAt = completedAt;
    this.deck = deck;
    this.assignedUser = assignedUser;
    }
}
