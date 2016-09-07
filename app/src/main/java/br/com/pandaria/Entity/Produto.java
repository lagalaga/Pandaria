package br.com.pandaria.Entity;


import java.util.HashMap;

public class Produto {

    private long id;
    private String nome;
    private float precoDeVenda;
    private boolean ingrediente;
    private HashMap<Ingrediente,Float> ingredientes;

    public Produto(){
        this.ingredientes = new HashMap<>();
    }

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

    public float getPrecoDeVenda() {
        return precoDeVenda;
    }

    public void setPrecoDeVenda(float precoDeVenda) {
        this.precoDeVenda = precoDeVenda;
    }

    public HashMap<Ingrediente, Float> getIngredientes() {
        return ingredientes;
    }

    public void setIngredientes(HashMap<Ingrediente, Float> ingredientes) {
        this.ingredientes = ingredientes;
    }

    public boolean isIngrediente() {
        return ingrediente;
    }

    public void setIngrediente(int ingrediente) {
        this.ingrediente = ingrediente == 0 ? false : true;
    }
}
