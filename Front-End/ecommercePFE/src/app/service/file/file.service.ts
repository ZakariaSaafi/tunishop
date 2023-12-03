import { HttpClient, HttpEvent } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class FileService {

  constructor(private http:HttpClient) { }

  //setProduitImage
  public setPorduitImage(formData:FormData, id_produit:bigint):Observable<HttpEvent<string[]>>{
    return this.http.post<string[]>("http://localhost:80/File/uploadProduitImage/"+id_produit,formData,{
      reportProgress:true,
      observe:'events'
    });
  }
}
