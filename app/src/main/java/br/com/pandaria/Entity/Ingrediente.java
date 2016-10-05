package br.com.pandaria.Entity;


import java.io.Serializable;

public class Ingrediente implements Serializable{

    private long id;
    private String nome;
    private float qtdDoPacote;
    private TipoDeIngrediente tipoDeIngrediente;
    private boolean escolhido;

    public long getId() {
        return id;
    }

    public void setId(long id) {
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

    public boolean isEscolhido() {
        return escolhido;
    }

    public void setEscolhido(boolean escolhido) {
        this.escolhido = escolhido;
    }

    public void setTipoDeIngrediente(String tipo) {
      this.tipoDeIngrediente = TipoDeIngrediente.valueOf(tipo);
    }

    @Override
    public String toString(){
        return getNome();
    }
}
