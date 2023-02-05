package com.example.controledeestoque.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.controledeestoque.R;
import com.example.controledeestoque.model.Produto;
import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.normal.TedPermission;

import java.util.List;

public class FromProdutoActivity extends AppCompatActivity {

    private static  final int REQUEST_GALERIA = 100;
    private ImageView imagem_produto;
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

    public void abrirGaleria(View view){
        verificaPermissaoGaleria();
    }

    private void verificaPermissaoGaleria(){
        PermissionListener permissionListener = new PermissionListener() {
            @Override
            public void onPermissionGranted() {
                acessarGaleria();
            }

            @Override
            public void onPermissionDenied(List<String> deniedPermissions) {
                Toast.makeText(FromProdutoActivity.this, "Permissão Negada.", Toast.LENGTH_SHORT).show();
            }
        };
        showDialogPermissao(permissionListener, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE});
    }

    private void acessarGaleria(){
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, REQUEST_GALERIA);
    }

    private void showDialogPermissao(PermissionListener listener, String[] permissoes){
        TedPermission.create()
                .setPermissionListener(listener)
                .setDeniedTitle("Permissões")
                .setDeniedMessage("Permissão negada para acessar a galeria do dispositivo, deseja permitir?")
                .setDeniedCloseButtonText("Não")
                .setGotoSettingButtonText("Sim")
                .setPermissions(permissoes)
                .check();
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
            imagem_produto = findViewById(R.id.imagem_produto);
        }
    }
