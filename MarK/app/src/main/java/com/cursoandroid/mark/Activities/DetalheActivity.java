package com.cursoandroid.mark.Activities;

import static android.view.View.GONE;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
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
    EditText et_titulo, et_genero, et_lancamento, et_avaliacao, et_comentario, et_tipo, et_temporada;
    ImageButton saveBtn;
    TextView pageTitleTextView;
    String titulo, genero, lancamento, avaliacao, comentario,tipo,docId, temporada;
    boolean isEditMode = false;
    TextView deleteTextViewBtn, saveTextViewBtn;
    RadioGroup rd_group;
    RadioButton rb_filme, rb_serie,rb_livro, rb_jogo;
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


        //receive data
        titulo = getIntent().getStringExtra("titulo");
        genero = getIntent().getStringExtra("genero");
        lancamento = getIntent().getStringExtra("lancamento");
        avaliacao = getIntent().getStringExtra("avaliacao");
        comentario = getIntent().getStringExtra("comentario");
        temporada = getIntent().getStringExtra("temporada");
        tipo = getIntent().getStringExtra("tipo");
        docId = getIntent().getStringExtra("docId");

        if(docId!=null && !docId.isEmpty()){
            isEditMode = true;
        }
        et_titulo.setText(titulo);
        et_genero.setText(genero);
        et_lancamento.setText(lancamento);
        et_tipo.setText(tipo);
        et_avaliacao.setText(avaliacao);
        et_comentario.setText(comentario);
        if(isEditMode){
            ll_optional.setVisibility(View.VISIBLE);
            pageTitleTextView.setText("marKaí: A EDIÇÃO");
            deleteTextViewBtn.setVisibility(View.VISIBLE);
            ratingBar.setVisibility(GONE);
            rd_group.setVisibility(GONE);
        }else{
            ll_optional.setVisibility(GONE);
            /*et_avaliacao.setVisibility(GONE);
            et_tipo.setVisibility(GONE);*/
        }

        saveTextViewBtn.setOnClickListener( (v)-> saveConteudo());

    }

    void saveConteudo(){

        String conteudoTitulo = et_titulo.getText().toString();
        String conteudoGenero = et_genero.getText().toString();
        String conteudoLancamento = et_lancamento.getText().toString();
        String conteudoTipo = et_tipo.getText().toString();
        Double conteudoAvaliacao = Double.parseDouble(et_avaliacao.getText().toString());
        String conteudoComentario = et_comentario.getText().toString();



        if(conteudoTitulo==null || conteudoTitulo.isEmpty() ){
            Toast.makeText(this, "O título é obrigatório", Toast.LENGTH_SHORT).show();
            return;
        } else if(conteudoAvaliacao>10){
            Toast.makeText(this, "10 é a nota máxima", Toast.LENGTH_SHORT).show();
            return;
        }else if(conteudoTipo==null || conteudoTitulo.isEmpty()) {
            Toast.makeText(this, "O tipo é obrigatório", Toast.LENGTH_SHORT).show();
            return;
        }

    }






    public void rating(){
        ratingBar.setOnRatingBarChangeListener((ratingBar, v, b) -> {
            String notaa = String.valueOf(ratingBar.getRating());
           // Toast.makeText(getApplicationContext(), notaa, Toast.LENGTH_SHORT).show();
            et_avaliacao.setText(notaa);
        });

    }


    public void hide(){
        rb_livro.setOnClickListener(v -> {

        });

        rb_filme.setOnClickListener(v -> {

        });

        rb_serie.setOnClickListener(v -> {
            et_temporada.setVisibility(View.VISIBLE);
        });
    }


    public void find(){
        et_titulo = findViewById(R.id.conteudo_titulo);
        et_genero = findViewById(R.id.conteudo_genero);
        et_lancamento = findViewById(R.id.et_temporada);
        et_avaliacao = findViewById(R.id.conteudo_avaliacao);
        et_comentario = findViewById(R.id.txt_coment);
        et_tipo = findViewById(R.id.conteudo_tipo);
        ratingBar = findViewById(R.id.ratingBar);
        et_temporada = findViewById(R.id.et_temporada);

        pageTitleTextView = findViewById(R.id.page_title);
        deleteTextViewBtn  = findViewById(R.id.delete_text_view);
        saveTextViewBtn  = findViewById(R.id.salvar_text_view2);
        rd_group = findViewById(R.id.rd_group);
        rb_filme = findViewById(R.id.rb_filme);
        rb_serie = findViewById(R.id.rb_serie);
        rb_livro = findViewById(R.id.rb_livro);

        ll_optional = findViewById(R.id.ll_tiutulo_nota);

    }

}