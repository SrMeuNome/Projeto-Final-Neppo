import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Component, OnInit, Input } from '@angular/core';
import { Router } from '@angular/router';
import { CookieService } from 'ngx-cookie-service';
import { Observable } from 'rxjs';
import { MEAT_API } from '../app.api';
import { User } from '../models/usuario.model';

@Component({
  selector: 'header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  nameUser: string = ''
  typeUser: string = ''

  token: string

  database: DataBase

  constructor(private router: Router, private cookie: CookieService, private _httpClient: HttpClient) { }

  goTo(path: string) : void
  {
    this.router.navigateByUrl(path);
  }

  ngOnInit(): void {
    this.logInCheck()
  }

  logInCheck(): void {
    this.token = this.cookie.get('access_token')
    if(this.token)
    {
      this.database = new DataBase(this._httpClient)
      this.database!.getRepoIssues(this.token).subscribe(data => {
        this.nameUser = data.username? data.username : ''
        this.typeUser = data.authorities[0].authority? data.authorities[0].authority : ''
        console.log(this.typeUser)
      })
    }
  }

  logOut(): void{
    this.token = ''
    this.cookie.set('access_token', '')
    this.logInCheck()
    window.location.reload()
  }

}

export class DataBase
{
  constructor(private _httpClient: HttpClient) {}

  getRepoIssues(token): Observable<User>
  {
    const path = `${MEAT_API}/auth/detalhes`

    const header: HttpHeaders = new HttpHeaders({Authorization: `Bearer ${token}`})

    return this._httpClient.get<User>(path, { headers: header })
  }
}
