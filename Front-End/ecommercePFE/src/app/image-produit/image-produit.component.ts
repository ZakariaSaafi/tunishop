import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { FileService } from '../service/file/file.service';

@Component({
  selector: 'app-image-produit',
  templateUrl: './image-produit.component.html',
  styleUrls: ['./image-produit.component.css']
})
export class ImageProduitComponent implements OnInit {
  selectedFile: File;
  public id:any 
  public code:any
  Form:FormGroup | any ;  
  constructor(private fileService:FileService,private fb:FormBuilder,private route: ActivatedRoute) { }


  ngOnInit(): void {
    this.Form=this.fb.group({
      files:['',Validators.required]
    });

    this.id = this.route.snapshot.paramMap.get('code'); //pour recuperer l'id 
    console.log('this.id',this.id);
    this.code=this.id;
  }

  
  onFileSelected(event:any){
    this.selectedFile= event.target.files[0];
    console.log(event);
  }

    //upload de l'image de produit
  onFileUpload(file:File,id_produit:any){
    const formData=new FormData();
    
      formData.append('files',file,file.name);
    
      this.fileService.setPorduitImage(formData,id_produit).subscribe(event=>{
      console.log(event);
      alert("produit ajouté avec succée");
      },(error:HttpErrorResponse)=>{
        console.log(error);
      }
      );  
    }
    

}
