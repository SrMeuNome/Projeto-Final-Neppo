import { HttpClient, HttpParams } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { MEAT_API } from '../app.api';
import {catchError, map, startWith, switchMap} from 'rxjs/operators';
import { Observable, of } from 'rxjs';
import { error } from 'protractor';


@Component({
  selector: 'app-cadastro',
  templateUrl: './cadastro.component.html',
  styleUrls: ['./cadastro.component.css']
})
export class CadastroComponent implements OnInit {

  error: boolean = false

  constructor(private router: Router, private _httpClient: HttpClient) {}

  ngOnInit(): void {}

  submit(value: any, valid: boolean): void {
      if(valid)
      {
        const formData = new FormData()
        this.error = false
        formData.append('email', value.email)
        formData.append('senha', value.senha)
        this._httpClient.post(`${MEAT_API}/usuarios/register`, formData)
        .pipe(
          catchError(() => of(this.error = true))
        )
        .subscribe( (data) => {
          console.log(data)
        })
      }
  }

}
