package br.com.pandaria.Entity;

public enum TipoDeIngrediente {

    INGREDIENTE("INGREDIENTE"),
    EMBALAGEM("EMBALAGEM");

    private String desc;

    TipoDeIngrediente(String desc){
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }
}
