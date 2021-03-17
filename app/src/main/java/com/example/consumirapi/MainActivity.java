package com.example.consumirapi;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private Button button;
    private TextView textView;
    private List<CEP> cepList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = (Button) findViewById(R.id.button);
        textView = (TextView) findViewById(R.id.textView);
        EditText uf = (EditText) findViewById(R.id.search_uf);
        EditText localidade = (EditText) findViewById(R.id.search_localidade);
        EditText logradouro = (EditText) findViewById(R.id.search_logradouro);
        Editable estado = uf.getText();
        Editable cidade = localidade.getText();
        Editable endereco =logradouro.getText();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Tarefa tarefa = new Tarefa();
                tarefa.execute("https://viacep.com.br/ws/" + estado +"/"+ cidade +"/"+  endereco +"/json/");
            }
        });
    }
    private class Tarefa extends AsyncTask<String, String, String>{
        @Override
        protected String doInBackground(String... strings) {
            String retorno = Conexao.getDados(strings[0]);
            return retorno;
        }
        @Override
        protected void onPostExecute(String s) {
            cepList = ConsumirJson.jsonDados(s);
            exibirDados();
        }
        @SuppressLint("SetTextI18n")
        private void exibirDados() {
            if(cepList.size() > 0){
                String logradouro = cepList.get(0).getLogradouro();
                textView.setText("Lista de CEP da: " + logradouro + "\n");
                for (CEP cep: cepList) {
                    textView.append(cep.getCep() + " " + cep.getBairro() + " " + cep.getComplemento() + "\n");
                }
            }
            else {
                textView.setText("\n" + "Não foram encontrados resultados! ");
            }
        }
    }
}