import { Acheteur } from "./Acheteur";
import { Categorie } from "./Categorie";
import { Vendeur } from "./Vendeur";

export interface Produit{
    code: bigint; 
	nom: string;
	description: string;
	dateAjout: Date;
	prix: number;
    quantite :number;
    vendu:boolean;
    vendeur:Vendeur;
    acheteur:Acheteur;
    categories:Set<Categorie>;
    surCommande:boolean;
    image:string;
}