import { Card } from "./card";
import { Workspace } from "./workspace";

export class Deck {

  id: number;
  name: string;
  description: string;
  cards: Card [];
  workspace: Workspace | null;

  constructor(
    id: number = 0,
    name: string = '',
    description: string = '',
    cards: Card [] = [],
    workspace: Workspace | null = null
  ){
  this.id = id;
  this.name = name;
  this.description = description;
  this.cards = cards;
  this.workspace = workspace
  }
}
