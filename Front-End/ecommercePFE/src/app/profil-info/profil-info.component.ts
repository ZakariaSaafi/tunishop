import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Commande } from '../model/Commande';
import { AuthenticationService } from '../service/authentication/authentication.service';
import { CommandeService } from '../service/commande/commande.service';

@Component({
  selector: 'app-profil-info',
  templateUrl: './profil-info.component.html',
  styleUrls: ['./profil-info.component.css']
})
export class ProfilInfoComponent implements OnInit {
  commande:Commande[]=[];
  loadingData:boolean=false;
  Form:FormGroup | any ;
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
  selectedRegion: any;
  constructor(public service:AuthenticationService , private route:Router,private commandeService:CommandeService,private fb:FormBuilder) { 
    if (this.service.loggedAcheteur=== false){
    this.route.navigate(['/loginAcheteur']);}
  }


  ngOnInit(): void {
    this.Form=this.fb.group({
      email:[''],
      nom :['',Validators.required],
      prenom :['',Validators.required],
      role :['',Validators.required],
      region :[''],
      codePostal :[''],
      adresse :[''],
      numTel:[''],
      
    });
    this.commandeService.getCommandeByAcheteur(this.service.currentAcheteur.code_ach).subscribe((data:any)=>{
      if(data){
        this.commande=data;
        this.loadingData=true;
        console.log(this.commande);
      }
    });
  }

  regionChange(e:any){
    this.selectedRegion=e.target.value;
  }

  logout(){
    this.service.logoutAcheteur();
  }

  updateInfo(c:any){
    this.service.updateAch(c).subscribe((data:any)=>{
      console.log("ach is: ",data);
    })
    this.service.updateUser(c).subscribe((data_:any)=>{
      console.log("user is: ",data_)
    })
  }
}
