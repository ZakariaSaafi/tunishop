import { Component, OnInit } from '@angular/core';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Categorie } from '../model/Categorie';
import { Panier } from '../model/Panier';
import { Produit } from '../model/Produit';
import { AuthenticationService } from '../service/authentication/authentication.service';
import { CategorieService } from '../service/categorie/categorie.service';
import { PanierService } from '../service/panier/panier.service';
import { ProduitServiceService } from '../service/produit/produit-service.service';

@Component({
  selector: 'app-list-produit',
  templateUrl: './list-produit.component.html',
  styleUrls: ['./list-produit.component.css']
})
export class ListProduitComponent implements OnInit {
  public produit:Produit[]=[];
  public categorie:Categorie[]=[];
  public errorMessage:string='';
  public loadingData:boolean=false;
  public id:any;
  public verif=false;
  public panier:Panier;
  
  constructor(public service:AuthenticationService,private categorieService:CategorieService,
    private produitService:ProduitServiceService,private panierService:PanierService, private route: ActivatedRoute) { }

  ngOnInit(): void {
    this.produitService.publicGetProduit().subscribe(
      (data:any) => {
          this.loadingData=true;
          //this.produitService.patchValue(data);
          this.produit=data;
         },(error)=>{
           this.errorMessage=error;
           this.loadingData=false;
         }
    );

    this.categorieService.publicGetCategorie().subscribe(
      (data:any) => {
          this.loadingData=true;
          //this.produitService.patchValue(data);
          this.categorie=data;
         },(error)=>{
           this.errorMessage=error;
           this.loadingData=false;
         }
    );

  }

ajouterAuPanier(id_ach:bigint,id_produit:any){
  /* this.verifPanier();
  if (this.verifPanier()){
   */  this.panierService.ajouterAuPanier(id_ach,id_produit).subscribe((data:any)=>{
    this.panier=data;  
    console.log(data);

        if(this.produit[id_produit-1].vendu)
        alert("produit indisponible");
        else{
          alert("produit ajouté avec succées");
        }

    })
  //}
}

updatePanier(id_ach:bigint,id_produit:any){
  /* this.verifPanier();
  if(!this.verifPanier()){
   */  this.panierService.updatePanier(id_ach,id_produit).subscribe((data:any)=>{
      console.log(data);
      if(this.produit[id_produit-1].vendu){
      alert("produit indisponible");}
      else{
        alert("produit ajouté avec succées");
      }
    })
  //}
}

verifPanier():boolean{
if(this.service.currentAcheteur.produits.length===0){
  this.verif=true;
}
return this.verif;
}



}
