package br.com.pandaria.Entity;

/**
 * Created by SilvaSoA2 on 31/08/2016.
 */
public enum TipoDeIngrediente {

    INGREDIENTE("INGREDIENTE"),
    EMBALAGEM("EMBALAGEM");

    private String desc;

    private TipoDeIngrediente(String desc){
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }
}
