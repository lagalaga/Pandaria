package br.com.pandaria.Entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Venda {

    private int id;
    private Date dateVenda;
    private List<Item> items;

    public Venda(){
        this.items = new ArrayList<>();
    }

    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDateVenda() {
        return dateVenda;
    }

    public void setDateVenda(Date dateVenda) {
        this.dateVenda = dateVenda;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }
}
