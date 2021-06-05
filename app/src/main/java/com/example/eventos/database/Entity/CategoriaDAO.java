package com.example.eventos.database.Entity;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.example.eventos.database.DBGateway;
import com.example.eventos.modelo.Categoria;

import java.util.ArrayList;
import java.util.List;

public class CategoriaDAO {

    private final String SQL_LISTAR_TODOS = "SELECT * FROM " + CategoriaEntity.TABLE_NAME;
    private DBGateway dbGateway;

    public CategoriaDAO(Context context){
        dbGateway = DBGateway.getInstance(context);
    }

    public boolean salvar(Categoria categoria){
        ContentValues contentValues = new ContentValues();
        contentValues.put(CategoriaEntity.COLLUMN_NAME_LOCAL_CAT, categoria.getNomeLocal());
        contentValues.put(CategoriaEntity.COLLUMN_NAME_BAIRRO, categoria.getBairro());
        contentValues.put(CategoriaEntity.COLLUMN_NAME_CIDADE, categoria.getCidade());
        contentValues.put(CategoriaEntity.COLLUMN_NAME_PUBLICO, categoria.getPublico());

        if (categoria.getId() > 0){
            return dbGateway.getDatabase().update(CategoriaEntity.TABLE_NAME,
                    contentValues,
                    CategoriaEntity._ID + "=?",
                    new String[]{String.valueOf(categoria.getId())}) > 0;
        }
        return dbGateway.getDatabase().insert(CategoriaEntity.TABLE_NAME,
                null, contentValues) > 0;
    }

    public List<Categoria> listar() {
        List<Categoria> categorias = new ArrayList<>();
        Cursor cursor = dbGateway.getDatabase().rawQuery(SQL_LISTAR_TODOS, null);
        while (cursor.moveToNext()){
            int id = cursor.getInt(cursor.getColumnIndex(CategoriaEntity._ID));
            String nomeLocal = cursor.getString(cursor.getColumnIndex(CategoriaEntity.COLLUMN_NAME_LOCAL_CAT));
            String bairro = cursor.getString(cursor.getColumnIndex(CategoriaEntity.COLLUMN_NAME_BAIRRO));
            String cidade = cursor.getString(cursor.getColumnIndex(CategoriaEntity.COLLUMN_NAME_CIDADE));
            String publico = cursor.getString(cursor.getColumnIndex(CategoriaEntity.COLLUMN_NAME_PUBLICO));
            categorias.add(new Categoria(id, nomeLocal, bairro, cidade, Integer.parseInt(publico)));
        }
        cursor.close();
        return categorias;
    }

    public boolean Excluir(Categoria categoria){
        ContentValues contentValues = new ContentValues();
        contentValues.put(CategoriaEntity.COLLUMN_NAME_LOCAL_CAT, categoria.getNomeLocal());

        if (categoria.getId() > 0){
            return dbGateway.getDatabase().delete(CategoriaEntity.TABLE_NAME,
                    CategoriaEntity._ID + "=?",
                    new String[]{String.valueOf(categoria.getId())}) >0;
        }
        return dbGateway.getDatabase().insert(CategoriaEntity.TABLE_NAME,
                null, contentValues) > 0;

    }
}
