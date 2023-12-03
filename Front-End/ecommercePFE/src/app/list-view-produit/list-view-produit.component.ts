import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Categorie } from '../model/Categorie';
import { Produit } from '../model/Produit';
import { AuthenticationService } from '../service/authentication/authentication.service';
import { CategorieService } from '../service/categorie/categorie.service';
import { ProduitServiceService } from '../service/produit/produit-service.service';

@Component({
  selector: 'app-list-view-produit',
  templateUrl: './list-view-produit.component.html',
  styleUrls: ['./list-view-produit.component.css']
})
export class ListViewProduitComponent implements OnInit {
  public produit:Produit[]=[];
  public categorie:Categorie[]=[];
  public errorMessage:string='';
  public loadingData:boolean=false;
  public id:any;

  constructor(private categorieService:CategorieService,private produitService:ProduitServiceService,private router:Router, private route: ActivatedRoute,public service:AuthenticationService) { }

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



}
