export class UserModel{
    private email: string;
    private mdp: string;
    private nom:string;
    private prenom:string;
    private confirmMdp:string;
    private role:string;
    private region:string;
    private numTel:string;
    private codePostal:string;
    private adresse:string;

    constructor(email:string, mdp:string, nom:string, prenom:string,confirmMdp:string,role:string){
        this.email=email;
        this.mdp=mdp;
        this.nom=nom;
        this.prenom=prenom;
        this.confirmMdp=confirmMdp;
        this.role=role;
    }

    setEmail(email:string):void{
        this.email=email;
    }

    getEmail(): string{
        return this.email;
    }

    getMdp(): string{
        return this.mdp;
    }
    getPrenom(): string{
        return this.prenom;
    }
    getNom(): string{
        return this.nom;
    }

    getConfirmMdp(){
        return this.confirmMdp;
    }

    getCodePostal(){
        return this.role;
    }
    getNumTel(){
        return this.role;
    }
    getAdresse(){
        return this.role;
    }
    getRegion(){
        return this.role;
    }
    getRole(){
        return this.role;
    }

    




}