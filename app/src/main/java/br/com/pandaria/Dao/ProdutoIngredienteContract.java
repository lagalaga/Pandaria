package br.com.pandaria.Dao;

import android.provider.BaseColumns;

public class ProdutoIngredienteContract {

    private ProdutoIngredienteContract(){}

    public static class ProdutoIngrediente implements BaseColumns {
        public static final String NOME_TABELA = "produto_ingrediente";
        public static final String NOME_COLUNA_FK_PRODUTO = "id_produto";
        public static final String NOME_COLUNA_FK_INGREDIENTE = "id_ingrediente";

    }

    private static final String TEXT_TYPE = " TEXT";
    private static final String FLOAT_TYPE = " REAL";
    private static final String COMMA_SEP = ",";

    protected static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + ProdutoIngrediente.NOME_TABELA + " (" +
                    ProdutoIngrediente.NOME_COLUNA_FK_PRODUTO + "" + COMMA_SEP +
                    ProdutoIngrediente.NOME_COLUNA_FK_INGREDIENTE + FLOAT_TYPE + COMMA_SEP +
                    " )";

    protected static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + ProdutoIngrediente.NOME_TABELA;
}
