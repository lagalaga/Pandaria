package br.com.pandaria.Entity;


public class Ingrediente {

    private int id;
    private String nome;
    private float qtdDoPacote;
    private TipoDeIngrediente tipoDeIngrediente;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public float getQtdDoPacote() {
        return qtdDoPacote;
    }

    public void setQtdDoPacote(float qtdDoPacote) {
        this.qtdDoPacote = qtdDoPacote;
    }

    public TipoDeIngrediente getTipoDeIngrediente() {
        return tipoDeIngrediente;
    }

    public void setTipoDeIngrediente(TipoDeIngrediente tipoDeIngrediente) {
        this.tipoDeIngrediente = tipoDeIngrediente;
    }
}
