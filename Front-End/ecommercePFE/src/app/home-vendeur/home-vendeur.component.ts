import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Produit } from '../model/Produit';
import { AuthenticationService } from '../service/authentication/authentication.service';
import { ProduitServiceService } from '../service/produit/produit-service.service';

@Component({
  selector: 'app-home-vendeur',
  templateUrl: './home-vendeur.component.html',
  styleUrls: ['./home-vendeur.component.css']
})
export class HomeVendeurComponent implements OnInit {
  public produit:Produit[]=[];
  public errorMessage:string='';
  public loadingData:boolean=false;
  public code:any  
  constructor(public service:AuthenticationService,private produitService:ProduitServiceService,private router:Router, private route: ActivatedRoute) { }
  
  ngOnInit(): void {
    this.code = this.route.snapshot.paramMap.get('id'); //pour recuperer l'id 
    console.log('this.id',this.code);
  }

  logoutVendeur(){
    this.service.logoutVendeur();
  }


}
