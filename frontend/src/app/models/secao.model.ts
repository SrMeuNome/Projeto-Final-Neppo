import { Artigo } from "./artigo.model";

export interface Secao
{
  id: number
  nome: String
  artigos: Artigo[]
}
