import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Categorie } from '../model/Categorie';
import { Panier } from '../model/Panier';
import { Produit } from '../model/Produit';
import { AuthenticationService } from '../service/authentication/authentication.service';
import { PanierService } from '../service/panier/panier.service';
import { ProduitServiceService } from '../service/produit/produit-service.service';

@Component({
  selector: 'app-productsingle',
  templateUrl: './productsingle.component.html',
  styleUrls: ['./productsingle.component.css']
})
export class ProductsingleComponent implements OnInit {

public produit:Produit;
public errorMessage:string='';
public loadingData:boolean=false;
public id:any  
public cat:Categorie;
verif:boolean=false
panier:Panier;
constructor(private produitService:ProduitServiceService,private router:Router, private route: ActivatedRoute,public service:AuthenticationService,private panierService:PanierService) { }

  ngOnInit(): void {
    this.id = this.route.snapshot.paramMap.get('id'); //pour recuperer l'id 
    console.log('this.id',this.id);
    
    if (this.id) { 
 
      this.produitService.publicGetProduitById(this.id).subscribe(
        (data:any) => {
            this.loadingData=true;
            console.log(data);
            this.produit=data;
           },(error)=>{
             this.errorMessage=error;
             this.loadingData=false;
           }
      );
  }

}


ajouterAuPanier(id_ach:bigint,id_produit:any){
  /* this.verifPanier();
  if (this.verifPanier()){
   */  this.panierService.ajouterAuPanier(id_ach,id_produit).subscribe((data:any)=>{
    this.panier=data;  
    console.log(data);

        if(this.produit.vendu)
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
      if(this.produit.vendu){
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
