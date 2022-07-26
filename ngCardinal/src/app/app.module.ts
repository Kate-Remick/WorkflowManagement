import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HomeComponent } from './components/home/home.component';
import { LoginComponent } from './components/login/login.component';
import { LogoutComponent } from './components/logout/logout.component';
import { WorkspaceComponent } from './components/workspace/workspace.component';
import { UserProfileComponent } from './components/user-profile/user-profile.component';
import { RegisterComponent } from './components/register/register.component';
import { HelpComponent } from './components/help/help.component';
import { NavbarComponent } from './components/navbar/navbar.component';
import { ChatComponent } from './components/chat/chat.component';
import { WorkspaceBuilderComponent } from './components/workspace-builder/workspace-builder.component';



@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    LoginComponent,
    LogoutComponent,
    WorkspaceComponent,
    UserProfileComponent,
    RegisterComponent,
    HelpComponent,
    NavbarComponent,
    ChatComponent,
    WorkspaceBuilderComponent,
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
