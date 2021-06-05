package com.example.eventos.database.Entity;

import android.provider.BaseColumns;

public final class CategoriaEntity implements BaseColumns {

    private CategoriaEntity(){}

    public static final String TABLE_NAME = "categoria";
    public static final String COLLUMN_NAME_LOCAL_CAT = "nomeLocal";
    public static final String COLLUMN_NAME_BAIRRO = "bairro";
    public static final String COLLUMN_NAME_CIDADE = "cidade";
    public static final String COLLUMN_NAME_PUBLICO = "publico";
}
