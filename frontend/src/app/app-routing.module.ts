import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { AddArtigoComponent } from './add-artigo/add-artigo.component';
import { ArtigoViewComponent } from './artigo-view/artigo-view.component'

const routes: Routes = [
  {path: '', component: ArtigoViewComponent},
  {path: 'criar-artigo', component: AddArtigoComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
