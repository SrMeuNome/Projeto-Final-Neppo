package com.vinicius.neppo.controller;

import com.vinicius.neppo.model.Teste;
import jdk.nashorn.internal.ir.ReturnNode;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
public class testeController
{
    @GetMapping("/")
    public Teste home()
    {
        Teste testevar = new Teste("Frodo");
        return testevar;
    }

    @GetMapping("/teste")
    public Teste teste()
    {
        Teste testevar = new Teste("Teste");
        return testevar;
    }
}
