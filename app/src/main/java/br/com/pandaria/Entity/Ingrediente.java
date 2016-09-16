package br.com.pandaria.Entity;


public class Ingrediente {

    private long id;
    private String nome;
    private float qtdDoPacote;
    private TipoDeIngrediente tipoDeIngrediente;

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

    public void setTipoDeIngrediente(String tipo) {
      this.tipoDeIngrediente = TipoDeIngrediente.valueOf(tipo);
    }

    @Override
    public String toString(){
        return getNome();
    }
}
