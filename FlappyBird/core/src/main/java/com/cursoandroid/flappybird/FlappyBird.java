package com.cursoandroid.flappybird;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

import java.util.Random;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class FlappyBird extends ApplicationAdapter {

    private SpriteBatch batch;
    private Texture[] passaro;
    private Texture fundo;
    private Texture canoBaixo;
    private Texture canoAlto;

    //atributos de config
    private int largura;
    private int altura;

    private float variacao = 0;
    private float velocidadeQueda =0;
    private float posicaoInicialVertical;
    private float posicaoMovimentoCanoH;
    private float espacoEntreCanos;
    private float deltaTime;
    private Random random;
    private float alturaEntreCanosRandom;
    private int estadoJogo =0; //o= nao iniciado 1=iniciado
    private BitmapFont fonte;
    private int pontuacao =0;
    private boolean marcouPonto=false;



    @Override
    public void create() {
        batch = new SpriteBatch();

        random = new Random();
        passaro = new Texture[3];
        passaro[0] = new Texture("passaro1.png");
        passaro[1] = new Texture("passaro2.png");
        passaro[2] = new Texture("passaro3.png");

        fundo = new Texture("fundo.png");

        canoBaixo = new Texture("cano_baixo.png");
        canoAlto = new Texture("cano_topo.png");

        altura = Gdx.graphics.getHeight();
        largura = Gdx.graphics.getWidth();
        posicaoInicialVertical = altura/2;
        posicaoMovimentoCanoH = largura;
        espacoEntreCanos=300;
        alturaEntreCanosRandom = random.nextInt(400)-200;

        fonte = new BitmapFont();
        fonte.setColor(Color.WHITE);
        fonte.getData().setScale(6);
    }

    @Override
    public void render() {
        //bate as asas mesmo parado

        deltaTime = Gdx.graphics.getDeltaTime();

        variacao += deltaTime * 10 ;
        posicaoMovimentoCanoH -= deltaTime * 200;
        velocidadeQueda++;

        if(variacao > 2) variacao = 0;

        if( Gdx.input.justTouched() ){
            velocidadeQueda = -15;
        }

        if(posicaoInicialVertical > 0 || velocidadeQueda < 0 )
            posicaoInicialVertical = posicaoInicialVertical - velocidadeQueda;

        //Verifica se o cano saiu inteiramente da tela
        if(posicaoMovimentoCanoH < -canoAlto.getWidth() ){
            posicaoMovimentoCanoH = largura;
            alturaEntreCanosRandom = random.nextInt(400) - 200;
            marcouPonto=false;

            if(posicaoMovimentoCanoH<120){
                if(!marcouPonto){
                    pontuacao++;
                    marcouPonto=true;
                }

            }
        }
        batch.begin();


        batch.draw(fundo, 0, 0, largura, altura);
        batch.draw( canoAlto, posicaoMovimentoCanoH, altura / 2 + espacoEntreCanos / 2 + alturaEntreCanosRandom );
        batch.draw(canoBaixo, posicaoMovimentoCanoH, altura / 2 - canoBaixo.getHeight() - espacoEntreCanos / 2 + alturaEntreCanosRandom );
        batch.draw( passaro[ (int) variacao ] , 120, posicaoInicialVertical );
        fonte.draw(batch,String.valueOf(pontuacao),largura/2, altura-150);


        batch.end();
    }


}
