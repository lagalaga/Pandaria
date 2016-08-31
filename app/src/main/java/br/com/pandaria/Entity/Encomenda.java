package br.com.pandaria.Entity;

public class Encomenda extends Venda{

    private String cliente;
    private String descricao;
    private float valor;
    private boolean isCancelada;

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }

    public boolean isCancelada() {
        return isCancelada;
    }

    public void setCancelada(boolean cancelada) {
        isCancelada = cancelada;
    }
}
