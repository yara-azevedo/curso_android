package com.cursoandroid.mark.Activities;

import static android.view.View.GONE;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.cursoandroid.mark.R;

public class DetalheActivity extends AppCompatActivity {
    EditText et_titulo, et_genero, et_ano, et_nota, et_comentario, et_tipo, et_temporada;
    ImageButton btn_save;
    TextView pageTitleTextView;
    String titulo,genero, ano, temporada, nota, comentario,tipo,docId;
    boolean isEditMode = false;
    TextView btn_delete, btn_salvar;
    RadioGroup rd_group;
    RadioButton rb_filme, rb_serie,rb_livro;
    RatingBar ratingBar;

    LinearLayout ll_optional;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhe);

        find();
        rating();
        hide();



    }

    public void rating(){
        ratingBar = findViewById(R.id.ratingBar);
        ratingBar.setOnRatingBarChangeListener((ratingBar, v, b) -> {
            String notaa = String.valueOf(ratingBar.getRating());
            et_nota.setText(notaa);
        });

    }


    public void hide(){
        rb_serie.setOnCheckedChangeListener((buttonView, isChecked) -> et_temporada.setVisibility(View.VISIBLE));
    }


    public void find(){
       et_titulo = findViewById(R.id.et_titulo);
       et_tipo   = findViewById(R.id.et_tipo);
       et_nota   = findViewById(R.id.et_nota);
       et_genero = findViewById(R.id.et_genero);
       et_ano    = findViewById(R.id.et_ano);
       et_temporada = findViewById(R.id.et_temporada);

       pageTitleTextView = findViewById(R.id.page_title);
       btn_delete  = findViewById(R.id.delete_text_view);
       btn_salvar  = findViewById(R.id.salvar_text_view2);

       rd_group = findViewById(R.id.rd_group);
       rb_filme = findViewById(R.id.rb_filme);
       rb_serie = findViewById(R.id.rb_serie);
       rb_livro = findViewById(R.id.rb_livro);

       ll_optional = findViewById(R.id.ll_tiutulo_nota);

    }



    /* void saveConteudo(){

        String conteudoTitulo = et_titulo.getText().toString();
        String conteudoTipo = et_tipo.getText().toString();
        Double conteudoNota = Double.parseDouble(et_nota.getText().toString());
        String conteudoGenero = et_genero.getText().toString();
        String conteudoAno = et_ano.getText().toString();
        String conteudoTemporada = et_temporada.getText().toString();
        String conteudoComentario = et_comentario.getText().toString();



        if(conteudoTitulo==null || conteudoTitulo.isEmpty() ){
            Toast.makeText(this, "O título é obrigatório", Toast.LENGTH_SHORT).show();
            return;
        } else if(conteudoNota>10){
            Toast.makeText(this, "10 é a nota máxima", Toast.LENGTH_SHORT).show();
            return;
        }else if(conteudoTipo==null || conteudoTitulo.isEmpty()) {
            Toast.makeText(this, "O tipo é obrigatório", Toast.LENGTH_SHORT).show();
            return;
        }

    }*/

    /*//receive data
        titulo = getIntent().getStringExtra("titulo");
        genero = getIntent().getStringExtra("genero");
        ano = getIntent().getStringExtra("ano");
        nota = getIntent().getStringExtra("nota");
        comentario = getIntent().getStringExtra("comentario");
        temporada = getIntent().getStringExtra("temporada");
        tipo = getIntent().getStringExtra("tipo");
        docId = getIntent().getStringExtra("docId");

        if(docId!=null && !docId.isEmpty()){
            isEditMode = true;
        }
        et_titulo.setText(titulo);
        et_genero.setText(genero);
        et_ano.setText(ano);
        et_tipo.setText(tipo);
        et_nota.setText(nota);
//        et_comentario.setText(comentario);
        if(isEditMode){
            ll_optional.setVisibility(View.VISIBLE);
            pageTitleTextView.setText("marKaí: A EDIÇÃO");
            btn_delete.setVisibility(View.VISIBLE);
            ratingBar.setVisibility(GONE);
            rd_group.setVisibility(GONE);
        }else{
            ll_optional.setVisibility(GONE);
            /*et_avaliacao.setVisibility(GONE);
            et_tipo.setVisibility(GONE);*/
}

