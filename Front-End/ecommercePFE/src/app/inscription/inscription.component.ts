import { Component, NgModule, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, NgForm, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { first, Observable } from 'rxjs';
import { Acheteur } from '../model/Acheteur';
import { UserModel } from '../model/UserModel';
import { AuthenticationService } from '../service/authentication/authentication.service';

@Component({
  selector: 'app-inscription',
  templateUrl: './inscription.component.html',
  styleUrls: ['./inscription.component.css']
})
export class InscriptionComponent implements OnInit {
  InscriForm:FormGroup | any ;
  region:any=[
    {nom: "Tunis"},
    {nom: "Ariana"},
    {nom: "Ben arous"},
    {nom: "Manouba"},
    {nom: "Bizerte"},
    {nom: "Zaghouan"},
    {nom: "Nabeul"},
    {nom: "Beja"},
    {nom: "Jendouba"},
    {nom: "Le Kef"},
    {nom: "Siliana"},
    {nom: "Monastir"},
    {nom: "Mahdia"},
    {nom: "Sousse"},
    {nom: "Sfax"},
    {nom: "Kairouan"},
    {nom: "Kasserine"},
    {nom: "Medenine"},
    {nom: "Gabes"},
    {nom: "Tataouine"},
    {nom: "Tozeur"},
    {nom: "Gafsa"},
    {nom: "Kebili"}
  ]
  isEmailInvalid=false;//vérification de l'exisatnce du mail saisie
  isMdpInvalid=false;//vérification de l'exisatnce du mdp saisie
  estInscri=false;
  error=null;
  selectedRole:any;
  selectedRegion:any;
  constructor(private service:AuthenticationService, private route:Router,private fb:FormBuilder) { }

  ngOnInit() {
    this.InscriForm=this.fb.group({
      email:['',Validators.required],
      mdp:['',Validators.required],
      nom :['',Validators.required],
      prenom :['',Validators.required],
      confirmMdp :['',Validators.required],
      role :['',Validators.required],
      region :[''],
      codePostal :[''],
      adresse :[''],
      numTel:[''],
      
    });
    this.isEmailInvalid=false;
    this.isMdpInvalid=false;
    
  }

  roleChange(e:any){
    console.log(e.target.value);
    this.selectedRole=e.target.value;
  }

  regionChange(e:any){
    this.selectedRegion=e.target.value;
  }
  onSubmit(c:any){
    this.get(c['email']);
    this.verifMdp(c);
    
    if(this.isMdpInvalid===false && this.isEmailInvalid===false)
      {
              this.service.signUp(c).subscribe(
              (data) => { 
                alert("inscription avec succés! Veuillez vérifier votre boîte e-mail")
              this.InscriForm.reset();
              if(this.selectedRole==="ACHETEUR"){this.route.navigate(["/loginAcheteur"]);}
              if(this.selectedRole==="VENDEUR"){this.route.navigate(["/loginVendeur"]);}
              if(this.selectedRole==="LIVREUR"){this.route.navigate(["/loginLivreur"]);}
              }, error=>{
                console.log(error);
                this.error=error.message;
              }
              );
              
      }


     
    
 
}

private verifExist(c:any): boolean{
  if (this.service.existByEmail(c))
  {this.isEmailInvalid=true;
    return this.estInscri=true;}
  else{
    return this.estInscri=false;}
}

get(c:any): void {
  this.service.existByEmail(c).subscribe(resp => {
    if (resp) {
      console.log('yayyy');
      this.isEmailInvalid=true;
    } else {
      console.log('nooo');
    }
  });
}

 private verifMdp(c:any){
  if(c['mdp']!==c['confirmMdp']){
    this.isMdpInvalid=true;
  }
}

return(){
  this.route.navigate([""]);
}

}


