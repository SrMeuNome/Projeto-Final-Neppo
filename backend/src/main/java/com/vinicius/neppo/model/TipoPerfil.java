package com.vinicius.neppo.model;

public enum TipoPerfil
{
    ADMIN(1, "ADMIN"), USER(2, "USER");

    private long cod;
    private String desc;

    private TipoPerfil(long cod, String desc) {
        this.cod = cod;
        this.desc = desc;
    }

    public long getCod() {
        return cod;
    }

    public String getDesc() {
        return desc;
    }
}
