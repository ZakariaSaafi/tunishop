export class SignInData{
    private nom: string;
    private mdp: string; 

    
    constructor(nom:string, mdp:string){
        this.nom=nom;
        this.mdp=mdp;
    }


    getnom(): string{
        return this.nom;
    }

    getmdp(): string{
        return this.mdp;
    }
   




}