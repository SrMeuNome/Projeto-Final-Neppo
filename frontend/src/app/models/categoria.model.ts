import { Artigo } from "./artigo.model";
import { Secao } from "./secao.model";

export interface Categoria
{
  id: number
  nome: string
  descricao: string
  artigos: Artigo[]
  secoes: Secao[]
}
