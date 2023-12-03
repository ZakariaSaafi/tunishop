import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Categorie } from '../model/Categorie';
import { Produit } from '../model/Produit';
import { AuthenticationService } from '../service/authentication/authentication.service';
import { CategorieService } from '../service/categorie/categorie.service';
import { ProduitServiceService } from '../service/produit/produit-service.service';

@Component({
  selector: 'app-list-info-multi',
  templateUrl: './list-info-multi.component.html',
  styleUrls: ['./list-info-multi.component.css']
})
export class ListInfoMultiComponent implements OnInit {
  public produit:Produit[]=[];
  public categorie:Categorie[]=[];
  public errorMessage:string='';
  public loadingData:boolean=false;
  public id:any;

  constructor(private categorieService:CategorieService,private produitService:ProduitServiceService,private router:Router, private route: ActivatedRoute,public service:AuthenticationService) { }

  ngOnInit(): void {


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

    this.id = this.route.snapshot.paramMap.get('id'); //pour recuperer l'id 
    console.log('this.id',this.id);
    if (this.id){
    this.produitService.publicGetProduitByCategorieId(this.id).subscribe(
      (data:any) => {
          this.loadingData=true;
          //this.produitService.patchValue(data);
          this.produit=data;
         },(error)=>{
           this.errorMessage=error;
           this.loadingData=false;
         }
    );
  }

  }



}
