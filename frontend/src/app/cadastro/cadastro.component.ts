import { HttpClient, HttpParams } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { MEAT_API } from '../app.api';
import {catchError, map, startWith, switchMap} from 'rxjs/operators';
import { Observable, of } from 'rxjs';
import { error } from 'protractor';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Usuario } from '../models/usuario.model';


@Component({
  selector: 'app-cadastro',
  templateUrl: './cadastro.component.html',
  styleUrls: ['./cadastro.component.css']
})
export class CadastroComponent implements OnInit {

  groupSingup: FormGroup

  error: boolean = false

  constructor(private router: Router, private _httpClient: HttpClient, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.groupSingup = this.fb.group(
      {
        email: [null, Validators.compose([Validators.required, Validators.email])],
        senha: [null, Validators.compose([Validators.required, Validators.minLength(6)])]
      }
    )

  }

  submit(): void {
      if(this.groupSingup.valid)
      {
        const formData = new FormData()
        this.error = false
        formData.append('email', this.groupSingup.get('email').value)
        formData.append('senha', this.groupSingup.get('senha').value)
        this._httpClient.post<Usuario>(`${MEAT_API}/usuarios/register`, formData)
        .pipe(
          catchError(() => of(this.error = true))
        )
        .subscribe( (data) => {
          if(!this.error && data)
          {
            this.router.navigateByUrl('/')
            this.groupSingup.reset()
          }
        })
      }
  }

}
