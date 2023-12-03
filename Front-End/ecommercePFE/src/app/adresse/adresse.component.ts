import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Commande } from '../model/Commande';
import { Panier } from '../model/Panier';
import { AuthenticationService } from '../service/authentication/authentication.service';
import { CommandeService } from '../service/commande/commande.service';
import { PanierService } from '../service/panier/panier.service';

@Component({
  selector: 'app-adresse',
  templateUrl: './adresse.component.html',
  styleUrls: ['./adresse.component.css']
})
export class AdresseComponent implements OnInit {
  constructor(public service:AuthenticationService,private route:Router,private commandeService:CommandeService ) { }
  commande:Commande[]=[];
  loadingData:boolean=false;
  ngOnInit(): void {
    if (this.service.isLogged=== false)
    this.route.navigate(['/loginAcheteur']);

    this.commandeService.getCommandeByAcheteur(this.service.currentAcheteur.code_ach).subscribe((data:any)=>{
      if(data){
        this.commande=data;
        this.loadingData=true;
        console.log(this.commande);
      }
    });
  }
  logout(){
    this.service.logoutAcheteur();
  }
}
