import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Produit } from 'src/app/model/Produit';

@Injectable({
  providedIn: 'root'
})
export class ProduitServiceService {
    requestOptions: Object = {
        /* other options here */
        responseType: 'text'
      }
  constructor(private http:HttpClient) { }


//PUBLIC
  public publicGetProduit() {
    return this.http.get("http://localhost:80/Produit/getAll");
}
//Get by ID 
public publicGetProduitById(id: bigint){
    return this.http.get("http://localhost:80/Vendeur/Produit/findproduit/"+id);
}

//Get by Categorie Id
public publicGetProduitByCategorieId(id: bigint){
    return this.http.get("http://localhost:80/Produit/getAllProduitsFromCategorie/"+id);
}

//SECURED (cad login obligatoire)

//create
public createProduit(c:any,id_vendeur:bigint,id_categorie:bigint){
    return this.http.post("http://localhost:80/Vendeur/Produit/AddProduit/"+id_vendeur+"/"+id_categorie,c);
}
//Delete
public deleteProduit(id: bigint){
    return this.http.delete("http://localhost:80/Vendeur/Produit/deleteProduit/"+id);
}
//Update
public updateProduit(id: bigint,Produit: Produit){
    return this.http.put("http://localhost:80/Vendeur/Produit/UpdateProduit/"+id,Produit);
}

//Get produit by Vendeur
public publicGetProduitByVendeurId(id: bigint){
    return this.http.get("http://localhost:80/Vendeur/Produit/getAllVend/"+id);
}

//Get produit by Acheteur
public publicGetProduitByAcheteurId(id: bigint){
    return this.http.get("http://localhost:80/Vendeur/Produit/getAllAch/"+id);
}


}
