import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Categorie } from '../model/Categorie';
import { Produit } from '../model/Produit';
import { AuthenticationService } from '../service/authentication/authentication.service';
import { ProduitServiceService } from '../service/produit/produit-service.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {
  public produit:Produit[]=[];
  public categorie:Categorie[]=[];
  public errorMessage:string='';
  public loadingData:boolean=false;
  public id:any;
  search:FormGroup | any ;
  public trv=false;
  constructor(private fb:FormBuilder,public service:AuthenticationService,private produitService:ProduitServiceService,private router:Router, private route: ActivatedRoute) { }

  ngOnInit(): void {

    this.search=this.fb.group({
      term:[''],
    });
  }

  sendData(event:any){
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

    let input=event.target.value;
    for(let i =0;i<this.produit.length;i++){
      let str=this.produit[i].nom;
      for(let j=0;j<str.length;j++){
        console.log(str)
        if(str[j]===input){
          this.trv=true;
        }
      }
    }

  }

}
