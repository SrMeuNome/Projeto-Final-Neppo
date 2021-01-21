import { Tag } from "./tags.model";
import { Usuario } from "./usuario.model";

export interface Artigo
{
  id: number
  titulo: string
  conteudo: string
  descricao: string
  autor: Usuario
  likes: number
  notLikes: number
  tags: Tag[]
}
