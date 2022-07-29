import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { CardComponent } from './components/card/card.component';
import { ChatComponent } from './components/chat/chat.component';
import { DeckComponent } from './components/deck/deck.component';
import { MessageComponent } from './components/message/message.component';
import { UserComponent } from './components/user/user.component';
import { WorkspaceComponent } from './components/workspace/workspace.component';


@NgModule({
  declarations: [
    AppComponent,
    CardComponent,
    ChatComponent,
    DeckComponent,
    MessageComponent,
    UserComponent,
    WorkspaceComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
