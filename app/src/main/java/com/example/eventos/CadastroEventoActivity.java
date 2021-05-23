package com.example.eventos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.eventos.modelo.Evento;

import java.util.Date;

public class CadastroEventoActivity extends AppCompatActivity {
    private final int RESULT_CODE_NOVO_EVENTO = 10;
    private final int RESULT_CODE_EVENTO_EDITADO = 11;

    private boolean edicao = false;
    private int id = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_evento);
        setTitle("Cadastro de Evento");

        carregarEvento();
    }

    private void carregarEvento() {
        Intent intent = getIntent();
        if (intent != null && intent.getExtras() != null && intent.getExtras().get("eventoEdicao") != null) {
            Evento evento = (Evento) intent.getExtras().get("eventoEdicao");
            EditText editTextNome = findViewById(R.id.editText_nome);
            EditText editTextLocal = findViewById(R.id.editText_local);
            EditText editTextData = findViewById(R.id.editTextDate_data);
            editTextNome.setText(evento.getNome());
            editTextLocal.setText(String.valueOf(evento.getLocal()));
            editTextData.setText(String.valueOf(evento.getData()));
            edicao = true;
            id = evento.getId();
        }
    }

    public void onClickVoltar(View v) {
        finish();
    }

    public void onClickSalvar(View v) {
        EditText editTextNome = findViewById(R.id.editText_nome);
        EditText editTextLocal = findViewById(R.id.editText_local);
        EditText editTextData = findViewById(R.id.editTextDate_data);

        String nome = editTextNome.getText().toString();
        String local = editTextLocal.getText().toString();
        String data = editTextData.getText().toString();


        Evento evento = new Evento(id,nome, local, data);
        Intent intent = new Intent();
        if (edicao) {
            intent.putExtra("eventoEditado", evento);
            setResult(RESULT_CODE_EVENTO_EDITADO, intent);
        } else {
            intent.putExtra("novoEvento", evento);
            setResult(RESULT_CODE_NOVO_EVENTO, intent);
        }

        finish();

    }


}


