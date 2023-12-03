import { Acheteur } from "./Acheteur";
import { Produit } from "./Produit";

export interface Panier{
    code_panier: bigint; 
	prixTotal: number;
    produits:Array<Produit>;
    acheteur:Acheteur;
    passed:boolean;
}