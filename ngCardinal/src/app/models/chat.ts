import { Message } from "./message";
import { User } from "./user";
import { Workspace } from "./workspace";

export class Chat {

  id: number;
  workspace: Workspace | null;
  messages: Message [];
  users: User [];

  constructor(
    id: number = 0,
    workspace: Workspace | null = null,
    messages: Message [] = [],
    users: User [] = []
  ){
    this.id = id;
    this.workspace = workspace;
    this.messages = messages;
    this.users = users;
  }
}
