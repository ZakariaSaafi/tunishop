import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Produit } from '../model/Produit';
import { AuthenticationService } from '../service/authentication/authentication.service';
import { ProduitServiceService } from '../service/produit/produit-service.service';

@Component({
  selector: 'app-dashboard-vendeur',
  templateUrl: './dashboard-vendeur.component.html',
  styleUrls: ['./dashboard-vendeur.component.css']
})
export class DashboardVendeurComponent implements OnInit {
  public produit:Produit[]=[];
  public errorMessage:string='';
  public loadingData:boolean=false;
  public id:any 
  public code:any 
  public nbrCommande:number=0;
  public nbrVente:number=0;
  public Wallet:number=0;
  constructor(public service:AuthenticationService,private produitService:ProduitServiceService,private router:Router, private route: ActivatedRoute){}

  ngOnInit(): void {
    this.id = this.route.snapshot.paramMap.get('code'); //pour recuperer l'id 
    console.log('this.id',this.id);
    this.code=this.id;
     if (this.id) { 
 
      this.produitService.publicGetProduitByVendeurId(this.code).subscribe(
        (data:any) => {
            this.loadingData=true;
            this.produit=data;
            console.log(this.produit);
            for(var p of this.produit){
              //check surcommande
              if(p.surCommande){
                this.nbrCommande++;
                console.log(this.nbrCommande);
              }
              //check vente
              if(p.vendu){
                this.nbrVente++;
                this.Wallet+=p.prix;
                console.log(this.nbrCommande);
              }
              
            }
           },(error)=>{
             this.errorMessage=error;
             this.loadingData=false;
           }
      );
  }

 

}

}
