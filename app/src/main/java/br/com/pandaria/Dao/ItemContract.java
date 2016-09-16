package br.com.pandaria.Dao;

import android.provider.BaseColumns;

public final class ItemContract {

    private ItemContract(){}

    public static class Item implements BaseColumns {
        public static final String NOME_TABELA = "item";
        public static final String NOME_COLUNA_FK_PRODUTO = "id_produto";
        public static final String NOME_COLUNA_FK_VENDA = "id_venda";
        public static final String NOME_COLUNA_QTD = "qtd_produto";

    }

    private static final String INTEGER_TYPE = " INTEGER";
    private static final String COMMA_SEP = ",";

    protected static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + Item.NOME_TABELA + " (" +
                    Item.NOME_COLUNA_FK_PRODUTO + INTEGER_TYPE + COMMA_SEP +
                    Item.NOME_COLUNA_FK_VENDA + INTEGER_TYPE + COMMA_SEP +
                    Item.NOME_COLUNA_QTD + INTEGER_TYPE + COMMA_SEP +
                    " FOREIGN KEY(" + Item.NOME_COLUNA_FK_PRODUTO + ")"+
                    " REFERENCES " + ProdutoContract.Produto.NOME_TABELA +
                    "(" + ProdutoContract.Produto._ID + ")" + COMMA_SEP +
                    " FOREIGN KEY(" + Item.NOME_COLUNA_FK_VENDA + ")"+
                    " REFERENCES " + VendaContract.Venda.NOME_TABELA +
                    "(" + VendaContract.Venda._ID + ")" +
                    " )";

    protected static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + Item.NOME_TABELA;
}
