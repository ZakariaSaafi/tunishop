import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, NgForm, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { first } from 'rxjs';
import { SignInData } from '../model/signInData';
import { UserModel } from '../model/UserModel';
import { AuthenticationService } from '../service/authentication/authentication.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  
  LoginForm:FormGroup | any ;
  responseMsg:any;
  isFormInvalid=false;
  areCredentialsInvalid=false;
  Username:any;
  erreur=0;
 

  constructor(private service:AuthenticationService, private route:Router,private fb:FormBuilder) { }


  ngOnInit(): void {
    this.LoginForm=this.fb.group({
      email:['',Validators.required],
      mdp:['',Validators.required],
    });
  }

 /*  onSubmit(c:any){
    this.service.authenticate(c);
  } */

 
  

  //************************************************************************************************************* */


 
}
