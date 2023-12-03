import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Commande } from '../model/Commande';
import { AuthenticationService } from '../service/authentication/authentication.service';
import { CommandeService } from '../service/commande/commande.service';
import { ProduitServiceService } from '../service/produit/produit-service.service';

@Component({
  selector: 'app-dashboard-livreur',
  templateUrl: './dashboard-livreur.component.html',
  styleUrls: ['./dashboard-livreur.component.css']
})
export class DashboardLivreurComponent implements OnInit {
  public id:any 
  public code:any 
  public livreurCommande:Commande[]=[]
  public commandeNonLivree:Commande[]=[];
  loadingData:boolean=false;
  public maps_ur="https://www.google.com/maps/place/";
  constructor(public service:AuthenticationService,private commandeService:CommandeService,private router:Router, private route: ActivatedRoute) { }

  ngOnInit(): void {
    this.id = this.route.snapshot.paramMap.get('code'); //pour recuperer l'id 
    console.log('this.id',this.id);
    this.code=this.id;

    if(this.code){
      this.commandeService.getCommandeNotDelivered().subscribe((data:any)=>{
        this.loadingData=true;
        this.commandeNonLivree=data;
       /*  for(var c of this.commandeNonLivree){
          if(c.region===this.service.currentLivreur)
        } */
      })
    }
  }

  getDirection(adresse:string,region:string){
    window.open(this.maps_ur+adresse+", "+region, "_blank");

  }

}
