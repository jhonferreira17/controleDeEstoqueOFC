package com.example.controledeestoque.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.controledeestoque.R;
import com.example.controledeestoque.model.Produto;

public class FromProdutoActivity extends AppCompatActivity {

    private EditText edit_produto;
    private EditText edit_quantidade;
    private EditText edit_valor;

    private Button salvarProduto;
    private Produto produto;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_from_produto);

        Bundle bundle = getIntent().getExtras();
        if(bundle != null){
            produto = (Produto) bundle.getSerializable("produto");

            editProduto();
        }

        inicializarComponentes();

        salvarProduto.setOnClickListener( v-> salvarProduto());
    }


    private void editProduto(){
        edit_produto.setText(produto.getNome());
        edit_quantidade.setText(String.valueOf(produto.getEstoque()));
        edit_valor.setText(String.valueOf(produto.getValor()));
    }

    public void salvarProduto() {
        String nome = edit_produto.getText().toString();
        String quantidade = edit_quantidade.getText().toString();
        String valor = edit_valor.getText().toString();



        if (!nome.isEmpty()) {
            if (!quantidade.isEmpty()) {

                int qtd = Integer.parseInt(quantidade);

                if (qtd >= 1) {

                    if (!valor.isEmpty()) {

                        double valorProduto = Double.parseDouble(valor);

                        if (valorProduto > 0) {

                            if (produto == null) produto = new Produto();
                            produto.setNome(nome);
                            produto.setEstoque(qtd);
                            produto.setValor(valorProduto);
                            finish();

                        } else {
                            edit_valor.requestFocus();
                            edit_valor.setError("Informe um valor maior que 0.");
                        }

                    } else {
                        edit_valor.requestFocus();
                        edit_valor.setError("Informe o valor do produto.");
                    }

                } else {
                    edit_quantidade.requestFocus();
                    edit_quantidade.setError("Informe um valor maior que 0.");
                }

            } else {
                edit_quantidade.requestFocus();
                edit_quantidade.setError("Informe um valor válido.");
            }
        } else {
            edit_produto.requestFocus();
            edit_produto.setError("Informe o nome do produto.");
        }

    }


        private void inicializarComponentes () {
            edit_produto = findViewById(R.id.editCadProduto);
            edit_quantidade = findViewById(R.id.editCadEstoque);
            edit_valor = findViewById(R.id.editCadValor);
            salvarProduto = findViewById(R.id.btnSalvar);
        }
    }
