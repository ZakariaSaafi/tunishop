import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class CategorieService {

  constructor(private http:HttpClient) { }

  public publicGetCategorie() {
    return this.http.get("http://localhost:80/Categorie/getAll");
}
//Get by ID
public publicGetCategorieById(id: bigint){
    return this.http.get("http://localhost:80/Categorie/findCategorie/"+id);
}

}
