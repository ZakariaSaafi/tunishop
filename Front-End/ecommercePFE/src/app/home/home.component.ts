import { DOCUMENT } from '@angular/common';
import { Component, ElementRef, Inject, NgZone, OnInit, ViewChild } from '@angular/core';
import { FormControl } from '@angular/forms';
import { MatCarousel, MatCarouselComponent } from '@ngmodule/material-carousel';
import { Categorie } from '../model/Categorie';
import { AuthenticationService } from '../service/authentication/authentication.service';
import { CategorieService } from '../service/categorie/categorie.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  public categorie:Categorie[]=[];
  public errorMessage:string='';
  public loadingData:boolean=false;
  public id:any;
  public nom:string="";
  public prenom:string="";
  constructor(private categorieService:CategorieService,public service:AuthenticationService) { }

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

    this.nom=this.service.getAchNom();
    this.prenom=this.service.getAchPrenom();
  
  }

  

  }
  
 

  
 



