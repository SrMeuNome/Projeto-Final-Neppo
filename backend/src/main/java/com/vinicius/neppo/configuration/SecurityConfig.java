package com.vinicius.neppo.configuration;

import com.vinicius.neppo.service.impl.UsuarioServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter
{
    private static final String[] AUTH_LIST = { "/", "/artigos", "/artigos/artigos-publicados", "/artigos/{id}"
    , "/artigos/{id}/like", "/artigos/{id}/notlike", "/categorias", "categorias/{id}/secao/{id_secao}", "categorias/{id}",
        "/secoes" , "/secoes/{id}", "/tags", "/tags/{id}", "/usuarios", "/usuarios/{id}"};
    private static final String[] AUTH_USER_LIST = { "/login", "/logiout" };
    private static final String[] AUTH_ADMIN_LIST = { "/teste" };

    @Autowired
    UsuarioServiceImp usuarioServiceImp;

    @Override
    protected void configure(HttpSecurity http) throws Exception
    {
        http.httpBasic()
                .and()
                .authorizeRequests()
                .antMatchers(AUTH_LIST).permitAll()
                .antMatchers(AUTH_USER_LIST).hasAnyAuthority("USER", "ADMIN")
                .antMatchers(AUTH_ADMIN_LIST).hasAuthority("ADMIN")
                .anyRequest()
                .authenticated()
                .and()
                .csrf().disable()
                .formLogin().disable();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception
    {
        auth.userDetailsService(usuarioServiceImp).passwordEncoder(new BCryptPasswordEncoder());
    }
}
