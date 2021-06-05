package com.example.eventos;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.eventos.database.Entity.CategoriaDAO;
import com.example.eventos.modelo.Categoria;

public class CadastroCategoriaActivity extends AppCompatActivity {

    private int id = 0;
    private EditText editTextNomeLocal;
    private EditText editTextBairro;
    private EditText editTextCidade;
    private EditText editTextPublico;


    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_categoria);
        setTitle("Cadastro de Categoria");
        editTextNomeLocal = findViewById(R.id.editTextNomeLocal);
        editTextBairro = findViewById(R.id.editTextBairro);
        editTextCidade = findViewById(R.id.editTextCidade);
        editTextPublico = findViewById(R.id.editTextPublico);
        carregarCategoria();
    }

    public void carregarCategoria(){
        Intent intent = getIntent();
        if (intent != null && intent.getExtras() != null &&
                intent.getExtras().get("categoriaEdicao") != null) {
            Categoria categoria = (Categoria) intent.getExtras().get("categoriaEdicao");
            EditText editTextNomeLocal = findViewById(R.id.editTextNomeLocal);
            EditText editTextBairro = findViewById(R.id.editTextBairro);
            EditText editTextCidade = findViewById(R.id.editTextCidade);
            EditText editTextPublico = findViewById(R.id.editTextPublico);
            editTextNomeLocal.setText(categoria.getNomeLocal());
            editTextBairro.setText(categoria.getBairro());
            editTextCidade.setText(categoria.getCidade());
            editTextPublico.setText(Integer.toString(categoria.getPublico()));
            id = categoria.getId();
        }
    }

    public void onClickVoltar(View v) {
        finish();
    }

    public void onClickSalvar(View v) {
        String nomeLocal = editTextNomeLocal.getText().toString();
        String bairro = editTextBairro.getText().toString();
        String cidade = editTextCidade.getText().toString();
        String publico = editTextPublico.getText().toString();
        Categoria categoria = new Categoria(id, nomeLocal, bairro, cidade, Integer.parseInt(publico));
        CategoriaDAO categoriaDAO = new CategoriaDAO(getBaseContext());
        boolean salvou = categoriaDAO.salvar(categoria);
        if(salvou){
            finish();
        }else {
            Toast.makeText(CadastroCategoriaActivity.this, "Erro ao salvar", Toast.LENGTH_LONG).show();
        }
    }

}