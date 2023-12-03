import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { AuthenticationService } from '../service/authentication/authentication.service';

@Component({
  selector: 'app-home-livreur',
  templateUrl: './home-livreur.component.html',
  styleUrls: ['./home-livreur.component.css']
})
export class HomeLivreurComponent implements OnInit {
public code:any;
  constructor(public service:AuthenticationService,private route:ActivatedRoute) { }

  ngOnInit(): void {
    this.code = this.route.snapshot.paramMap.get('id'); //pour recuperer l'id 
    console.log('this.id',this.code);
  }

  logoutLivreur(){
    this.service.logoutLivreur();
  }

}