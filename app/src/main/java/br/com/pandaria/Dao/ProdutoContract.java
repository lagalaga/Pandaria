package br.com.pandaria.Dao;

import android.provider.BaseColumns;

public final class ProdutoContract {

    private ProdutoContract(){}

    public static class Produto implements BaseColumns {
        public static final String NOME_TABELA = "produto";
        public static final String NOME_COLUNA_NOME = "nome";
        public static final String NOME_COLUNA_VALOR_DE_VENDA = "valor_venda";
        public static final String NOME_COLUNA_IS_INGREDIENTE = "is_ingrediente";


    }

    private static final String TEXT_TYPE = " TEXT";
    private static final String FLOAT_TYPE = " REAL";
    private static final String BOOLEAN_TYPE = " INTEGER";
    private static final String COMMA_SEP = ",";

    protected static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + Produto.NOME_TABELA + " (" +
                    Produto._ID + " INTEGER PRIMARY KEY," +
                    Produto.NOME_COLUNA_NOME + TEXT_TYPE + COMMA_SEP +
                    Produto.NOME_COLUNA_VALOR_DE_VENDA + FLOAT_TYPE + COMMA_SEP +
                    Produto.NOME_COLUNA_IS_INGREDIENTE + BOOLEAN_TYPE +
                     " )";

    protected static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + Produto.NOME_TABELA;
}
