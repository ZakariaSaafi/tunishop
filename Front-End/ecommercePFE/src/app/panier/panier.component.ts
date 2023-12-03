import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Panier } from '../model/Panier';
import { Produit } from '../model/Produit';
import { AuthenticationService } from '../service/authentication/authentication.service';
import { CommandeService } from '../service/commande/commande.service';
import { PanierService } from '../service/panier/panier.service';
import { ProduitServiceService } from '../service/produit/produit-service.service';


@Component({
  selector: 'app-panier',
  templateUrl: './panier.component.html',
  styleUrls: ['./panier.component.css']
})
export class PanierComponent implements OnInit {
public panier:Panier;
public currentProduit:any;
public loadingData:boolean=false;
public id_panier:bigint;

  
  constructor(public service:AuthenticationService ,private route:Router,private panierService:PanierService
    ,private commandeService:CommandeService) { 
    if (this.service.isLogged=== false)
    this.route.navigate(['/loginAcheteur']);
  }

  ngOnInit(): void {
     this.panierService.getPanier(this.service.currentAcheteur.code_ach).subscribe((data:any)=>{
      if(data){
        this.loadingData=true;
        this.panier=data;
        console.log(this.id_panier);
      }
    });
    
  }

  passerCommande(){
    this.commandeService.creerCommande(this.service.currentAcheteur.code_ach).subscribe((data:any)=>{
      console.log(data);
    });
    alert("commande enregistré avec succés");
  }

  

}
