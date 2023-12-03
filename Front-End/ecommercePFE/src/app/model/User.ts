import { Role } from "./Role";

export interface User{
    code:bigint;
    nom: string;
    prenom: string;
    mdp: string;
    roles: Set<Role>;
    email: string;
    enabled: boolean;
    logged: boolean;
    isBanned: boolean;
}