import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { AuthenticationService } from '../service/authentication/authentication.service';

@Component({
  selector: 'app-login-admin',
  templateUrl: './login-admin.component.html',
  styleUrls: ['./login-admin.component.css']
})
export class LoginAdminComponent implements OnInit {
  LoginForm:FormGroup | any ;
  responseMsg:any;
  isFormInvalid=false;
  areCredentialsInvalid=false;
  Username:any;
  erreur=0;
  constructor(private service:AuthenticationService,private fb:FormBuilder) { }

  ngOnInit(): void {
    this.LoginForm=this.fb.group({
      email:['',Validators.required],
      mdp:['',Validators.required],
    });
  }
  onSubmit(c:any){
    this.service.authenticateAdmin(c);
  }

}
