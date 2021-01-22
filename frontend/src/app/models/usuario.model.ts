export interface Usuario
{
  id: number
  email: string
  perfil: string
}

export interface User
{
  username: string
  accountNonExpired: boolean
  authorities: Authorities[]
}

export interface Authorities
{
  authority: string
}
