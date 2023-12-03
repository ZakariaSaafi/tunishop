import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthenticationService } from '../service/authentication/authentication.service';

@Component({
  selector: 'app-login-vendeur',
  templateUrl: './login-vendeur.component.html',
  styleUrls: ['./login-vendeur.component.css']
})
export class LoginVendeurComponent implements OnInit {
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

  onSubmit(c:any){
    this.service.authenticateVend(c);
  }

 
  

  //************************************************************************************************************* */


 
}
