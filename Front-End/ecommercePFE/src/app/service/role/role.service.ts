import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class RoleService {
 
  constructor(private htttp:HttpClient) { }

  //Get role by id user
  public getRoleByUserId(id:bigint){
    return this.htttp.get("http://localhost:80/getRole/"+id);
  }
}
