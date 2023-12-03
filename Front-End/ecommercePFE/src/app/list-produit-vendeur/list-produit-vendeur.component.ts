import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Produit } from '../model/Produit';
import { AuthenticationService } from '../service/authentication/authentication.service';
import { ProduitServiceService } from '../service/produit/produit-service.service';

@Component({
  selector: 'app-list-produit-vendeur',
  templateUrl: './list-produit-vendeur.component.html',
  styleUrls: ['./list-produit-vendeur.component.css']
})
export class ListProduitVendeurComponent implements OnInit {
  public produit:Produit[]=[];
  public errorMessage:string='';
  public loadingData:boolean=false;
  public id:any 
  public code:any 
  constructor(public service:AuthenticationService,private produitService:ProduitServiceService,private router:Router, private route: ActivatedRoute) { }

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
           },(error)=>{
             this.errorMessage=error;
             this.loadingData=false;
           }
      );
    } 
  }

  deleteProduit(id:bigint){
    this.produitService.deleteProduit(id).subscribe((data:any)=>{
      console.log(data);
    })
  }

}
