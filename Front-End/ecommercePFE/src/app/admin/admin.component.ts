import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Route, Router } from '@angular/router';
import { Acheteur } from '../model/Acheteur';
import { Commande } from '../model/Commande';
import { Livreur } from '../model/Livreur';
import { User } from '../model/User';
import { Vendeur } from '../model/Vendeur';
import { AuthenticationService } from '../service/authentication/authentication.service';

@Component({
  selector: 'app-admin',
  templateUrl: './admin.component.html',
  styleUrls: ['./admin.component.css']
})
export class AdminComponent implements OnInit {
public users:User[]=[];
public commande:Commande[]=[];
loadingData:boolean=false;
public acheteur:Acheteur[]=[];
public vendeur:Vendeur[]=[];
public livreur:Livreur[]=[];
public nbrAch:number=0;
public nbrVend:number=0;
public nbrLiv:number=0;
public nbrLivraison:number=0;
public nbrPendingLivraison:number=0;
  constructor(private service:AuthenticationService,private route:Router) {
    if (this.service.loggedAdmin=== false){
      this.route.navigate(['/loginAdmin']);}
   }

  ngOnInit(): void {
    this.service.listAllUsers().subscribe((data:any)=>{
      this.loadingData=true;
      this.users=data;
    });
    this.service.listAllach().subscribe((data:any)=>{
      this.acheteur=data
    })
    this.service.listAllvend().subscribe((data:any)=>{
      this.vendeur=data
    })
    this.service.listAllliv().subscribe((data:any)=>{
      this.livreur=data;
    })
    
  }

}
