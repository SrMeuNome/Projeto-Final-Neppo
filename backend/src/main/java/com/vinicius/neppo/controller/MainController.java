package com.vinicius.neppo.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
public class MainController
{
    @RequestMapping(path = "/login", method = RequestMethod.GET)
    public User retornarUsuarioAtual()
    {
        return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    @RequestMapping(path = "/logiout", method = RequestMethod.GET)
    public void retornarErroLogin()
    {
        SecurityContextHolder.getContext().getAuthentication().setAuthenticated(false);
    }
}
