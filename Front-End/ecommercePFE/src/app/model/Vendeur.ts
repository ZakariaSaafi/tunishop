import { Acheteur } from "./Acheteur";
import { Produit } from "./Produit";

export interface Vendeur{
    code_vendeur:bigint;
    nom: string;
    prenom: string;
    mdp: string;
    role: string;
    email: string;
    enabled: boolean;
    logged: boolean;
    isBanned: boolean;
    produits:Array<Produit>
}