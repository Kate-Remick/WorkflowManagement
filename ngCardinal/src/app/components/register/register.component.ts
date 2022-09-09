import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { User } from 'src/app/models/user';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {
  newUser: User = new User();
  registrationError: boolean = false;


  constructor(private authServ: AuthService, private router: Router) { }

  ngOnInit(): void {
  }
  register(u: User): void{
      this.authServ.register(u).subscribe({
        next: (registeredUser) => {
          if(u.username && u.password){
          this.authServ.login(u.username, u.password).subscribe({
            next:(loggedInUser)=>{
              this.router.navigateByUrl('/dashboard')
            }
          })
        }
        },
        error: (problem) => {
          console.error('HttpComponent.reload(): error registering');
          console.error(problem);
          this.registrationError = true;
        }
      });
  }

}
