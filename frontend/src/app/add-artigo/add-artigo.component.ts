import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { MEAT_API } from '../app.api';
import {catchError, map, startWith, switchMap} from 'rxjs/operators';
import { Observable, of } from 'rxjs';
import { error } from 'protractor';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Usuario } from '../models/usuario.model';
import { CookieService } from 'ngx-cookie-service';
import { Artigo } from '../models/artigo.model';

@Component({
  selector: 'app-add-artigo',
  templateUrl: './add-artigo.component.html',
  styleUrls: ['./add-artigo.component.css']
})
export class AddArtigoComponent implements OnInit {

  groupSingup: FormGroup

  error: boolean = false

  constructor(private router: Router, private _httpClient: HttpClient, private fb: FormBuilder, private cookie: CookieService) {}

  ngOnInit(): void {
    this.groupSingup = this.fb.group(
      {
        titulo: [null, Validators.compose([Validators.required, Validators.minLength(5)])],
        descricao: [null],
        conteudo: [null, Validators.compose([Validators.required, Validators.minLength(10)])],
        tags: [null]
      }
    )

  }

  submit(): void {
      if(this.groupSingup.valid)
      {
        const token = this.cookie.get('access_token')
        const header: HttpHeaders = new HttpHeaders({Authorization: `Bearer ${token}`})

        const formData = new FormData()
        this.error = false
        formData.append('titulo', this.groupSingup.get('titulo').value)
        formData.append('descricao', this.groupSingup.get('descricao').value)
        formData.append('conteudo', this.groupSingup.get('conteudo').value)
        formData.append('tags', this.groupSingup.get('tags').value)
        this._httpClient.post<Artigo>(`${MEAT_API}/artigos`, formData)
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
