import { HttpClient, HttpParams } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { MEAT_API, APP_URL } from '../app.api';
import {catchError, map, startWith, switchMap} from 'rxjs/operators';
import { Observable, of } from 'rxjs';
import { error } from 'protractor';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { CookieService } from 'ngx-cookie-service';
import { User } from '../models/usuario.model';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  groupSingup: FormGroup

  error: boolean = false

  constructor(private router: Router, private _httpClient: HttpClient, private fb: FormBuilder, private cookie: CookieService) {}

  ngOnInit(): void {
    this.groupSingup = this.fb.group(
      {
        email: [null, Validators.compose([Validators.required, Validators.email])],
        senha: [null, Validators.compose([Validators.required, Validators.minLength(6)])]
      }
    )
  }

  saveToken(tokenData) : void {
    console.log(tokenData.data.token)
    this.cookie.set("access_token", tokenData.data.token);
    console.log('Obtained Access token');
    window.location.href = `${APP_URL}/`
  }

  submit(): void {
      if(this.groupSingup.valid)
      {
        this.error = false
        this._httpClient.post<User>(`${MEAT_API}/auth`, this.groupSingup.value)
        .pipe(
          catchError(() => of(this.error = true)),
        )
        .subscribe( (data) => {
          if(!this.error && data)
          {
            this.router.navigateByUrl('/')
            this.groupSingup.reset()
            this.saveToken(data)
          }
        })
      }
  }

}
