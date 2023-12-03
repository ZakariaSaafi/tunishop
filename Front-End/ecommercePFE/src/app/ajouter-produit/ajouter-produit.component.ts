import { HttpErrorResponse, HttpEvent } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Route, Router } from '@angular/router';
import { Categorie } from '../model/Categorie';
import { Produit } from '../model/Produit';
import { AuthenticationService } from '../service/authentication/authentication.service';
import { CategorieService } from '../service/categorie/categorie.service';
import { FileService } from '../service/file/file.service';
import { ProduitServiceService } from '../service/produit/produit-service.service';

@Component({
  selector: 'app-ajouter-produit',
  templateUrl: './ajouter-produit.component.html',
  styleUrls: ['./ajouter-produit.component.css']
})
export class AjouterProduitComponent implements OnInit {
public categorie:Categorie[]=[];
public errorMessage:string='';
  public loadingData:boolean=false;
  public id:any;
  selectedCat:any;
Form:FormGroup | any ;
selectedFile:File[];
produit:Produit;
imgUrl:any;
  constructor(private categorieService:CategorieService,public service:AuthenticationService,private fb:FormBuilder,private produitService:ProduitServiceService,
    private router:Router,private fileService:FileService) { }

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

    this.Form=this.fb.group({
      nom:['',Validators.required],
      description:['',Validators.required],
      prix :['',Validators.required],
      categorie :['',Validators.required],
      quantite :['',Validators.required],
      files :['',Validators.required],
      dateAjout :[new Date()],
    });

  }

  //upload de l'image de produit
  onFileUpload(files:File[],id_produit:bigint){
    const formData=new FormData();
    for(const file of files){
      formData.append('files',file,file.name);
    }
      this.fileService.setPorduitImage(formData,id_produit).subscribe(event=>{
      console.log(event);
    },(error:HttpErrorResponse)=>{
      console.log(error);
      }
      );  
    }
    

  
    onFileSelected(event:any){
    this.selectedFile= event.target.files;
    console.log(event);
    var reader=new FileReader();
      reader.readAsDataURL(this.selectedFile[0]);
      reader.onload=(_event)=>{
        this.imgUrl=reader.result;
    }
  }

  ajouterProduit(c:any,id_vendeur:any,id_categorie:any){
    this.produitService.createProduit(c,id_vendeur,id_categorie).subscribe((data:any)=>{
      console.log(data);
      this.produit=data;
      this.onFileUpload(this.selectedFile,this.produit.code);
      alert("produit ajouté avec succée");
      this.Form.reset();
    })
  }

  categorieChange(e:any){
    console.log(e.target.value);
    this.selectedCat=e.target.value;
  }

}
