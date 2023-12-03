import { Component, OnInit } from '@angular/core';
import { LoginComponent } from '../login/login.component';
import { Commande } from '../model/Commande';
import { Panier } from '../model/Panier';
import { Produit } from '../model/Produit';
import { AuthenticationService } from '../service/authentication/authentication.service';
import { CommandeService } from '../service/commande/commande.service';
import { PanierService } from '../service/panier/panier.service';
import { ProduitServiceService } from '../service/produit/produit-service.service';

@Component({
  selector: 'app-header-user',
  templateUrl: './header-user.component.html',
  styleUrls: ['./header-user.component.css']
})
export class HeaderUserComponent implements OnInit {
 panier:Panier;
 produitsPanier:Produit[]=[]; 
 prenom:string ="";
 public loadingDataHeader:boolean=false;
 commande:Commande[]=[];
loadingData:boolean=false;

  constructor(private service:AuthenticationService,private commandeService:CommandeService,private panierService:PanierService) {}

  ngOnInit(): void {
    this.prenom=this.service.getAchPrenom();
    /* this.produitService.publicGetProduitByAcheteurId(this.service.currentAcheteur.code_ach).subscribe((data:any)=>
    this.produitsPanier=data
    ); */

      this.panierService.getPanier(this.service.currentAcheteur.code_ach).subscribe((data:any)=>{
        if(data){
          this.loadingDataHeader=true;
          this.panier=data;
      }
    });

    this.commandeService.getCommandeByAcheteur(this.service.currentAcheteur.code_ach).subscribe((data:any)=>{
      if(data){
        this.commande=data;
        this.loadingData=true;
      }
    });
    
  }

  logoutAcheteur(){
    this.service.logoutAcheteur();
  }



}
