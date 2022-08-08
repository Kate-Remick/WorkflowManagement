import { UserService } from './../../services/user.service';
import { AuthService } from './../../services/auth.service';
import { Component, OnInit } from '@angular/core';
import { User } from 'src/app/models/user';

@Component({
  selector: 'app-user-profile',
  templateUrl: './user-profile.component.html',
  styleUrls: ['./user-profile.component.css']
})
export class UserProfileComponent implements OnInit {
  loggedInUser: User = new User();
  constructor(private authServ: AuthService, private userServ: UserService) { }

  ngOnInit(): void {
    this.getUser();
  }

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
}
