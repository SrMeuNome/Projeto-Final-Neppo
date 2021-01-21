import { AfterViewInit, Component, ViewChild } from '@angular/core';
import { Router } from '@angular/router';
import { Categoria } from '../models/categoria.model';
import {HttpClient} from '@angular/common/http';
import { Pageable } from '../models/pageable.model';
import { Observable, of } from 'rxjs';
import {catchError, map, startWith, switchMap} from 'rxjs/operators';
import { MEAT_API } from '../app.api';
import { MatPaginator } from '@angular/material/paginator';

@Component({
  selector: 'app-artigo-view',
  templateUrl: './artigo-view.component.html',
  styleUrls: ['./artigo-view.component.css']
})
export class ArtigoViewComponent implements AfterViewInit {

  categorias: Categoria[]
  bodyOfKnowledgeAPI: BodyOfKnowledgeAPI

  pageSizeOptions: number[] = [5, 10, 20, 40]
  resultsLength: number

  @ViewChild(MatPaginator) paginator: MatPaginator;

  database: DataBase | null

  constructor(private router: Router, private _httpClient: HttpClient) {
   }

  goTo(path: string) : void
  {
    this.router.navigateByUrl(path);
  }

  ngAfterViewInit(){
    this.database = new DataBase(this._httpClient)
    this.paginator.page.pipe(
      startWith({}),
      switchMap( () => {
        return this.database!.getRepoIssues(this.paginator.pageSize, this.paginator.pageIndex)
      }),
      map(data => {
        this.resultsLength = data.totalElements
        return data.content
      }),
      catchError( err => {
        return of([]);
      })
    ).subscribe(data => this.categorias = data)
  }

}

export interface BodyOfKnowledgeAPI
{
  content: Categoria[]
  pageable: Pageable
  last: boolean
  totalElements: number
  totalPages: number
  empty: boolean
}

export class DataBase
{
  constructor(private _httpClient: HttpClient) {}

  getRepoIssues(tamanhoPagina: number, numeroDaPagina: number): Observable<BodyOfKnowledgeAPI>
  {
    const path = `${MEAT_API}/categorias/publicadas`
    const pathParam = `${path}?pagina=${numeroDaPagina}&tamanho=${tamanhoPagina}`

    return this._httpClient.get<BodyOfKnowledgeAPI>(pathParam)
  }
}
