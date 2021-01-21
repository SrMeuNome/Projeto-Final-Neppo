import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { AddArtigoComponent } from './add-artigo/add-artigo.component';

const routes: Routes = [
  //{path: '', component: AddArtigoComponent},
  {path: 'criar-artigo', component: AddArtigoComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
