package br.com.pandaria.Entity;


import java.util.Date;

public class Estoque {

    private long id;
    private float qtdDePacotes;
    private Date data;
    private float precoUnit;
    private Ingrediente ingrediente;

    public Estoque(){
        this.ingrediente = new Ingrediente();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public float getQtdDePacotes() {
        return qtdDePacotes;
    }

    public void setQtdDePacotes(float qtdDePacotes) {
        this.qtdDePacotes = qtdDePacotes;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public float getPrecoUnit() {
        return precoUnit;
    }

    public void setPrecoUnit(float precoUnit) {
        this.precoUnit = precoUnit;
    }

    public Ingrediente getIngrediente() {
        return ingrediente;
    }

    public void setIngrediente(Ingrediente ingrediente) {
        this.ingrediente = ingrediente;
    }
}
