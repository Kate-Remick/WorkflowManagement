import { Router } from '@angular/router';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {


  //************** Seup ********************** */

  constructor(private router: Router) { }

  ngOnInit(): void {
  }

  // ********** Navigation **************

  goToRegister():void{
    this.router.navigateByUrl('/register');
  }

  //******************** Page Dynamics ***************** */
}
