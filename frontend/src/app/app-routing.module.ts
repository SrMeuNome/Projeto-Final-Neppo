import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { AddArtigoComponent } from './add-artigo/add-artigo.component';
import { ArtigoViewComponent } from './artigo-view/artigo-view.component'
import { CadastroComponent } from './cadastro/cadastro.component';
import { LoginComponent } from './login/login.component';

const routes: Routes = [
  {path: '', component: ArtigoViewComponent},
  {path: 'criar-artigo', component: AddArtigoComponent},
  {path: 'cadastrar', component: CadastroComponent},
  {path: 'login', component: LoginComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
