/*
 * Copyright (c) 2015-present, Parse, LLC.
 * All rights reserved.
 *
 * This source code is licensed under the BSD-style license found in the
 * LICENSE file in the root directory of this source tree. An additional grant
 * of patent rights can be found in the PATENTS file in the same directory.
 */
package com.parse.starter.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.viewpager.widget.ViewPager;

import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseUser;
import com.parse.SaveCallback;
import com.parse.starter.R;
import com.parse.starter.util.SlidingTabLayout;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import adapter.HomeAdapter;
import adapter.TabsAdapter;
import fragments.HomeFragment;


public class MainActivity extends AppCompatActivity {

  private Toolbar toolbar;
  private SlidingTabLayout slidingTabLayout;
  private ViewPager viewPager;


  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    findd();
  }

  @SuppressLint("ResourceAsColor")
  private void findd() {
    toolbar = findViewById(R.id.toolbar_principal);
    setSupportActionBar(toolbar);

    configAba();

    // Remove o título para exibir apenas o logo
    if (getSupportActionBar() != null) {
      getSupportActionBar().setDisplayShowTitleEnabled(false);
    }

    toolbar.setLogo(R.drawable.imagem1);
    toolbar.setBackgroundColor(ContextCompat.getColor(this, R.color.preto));
  }

  private void configAba() {
    slidingTabLayout = findViewById(R.id.sliding_tabs);
    viewPager = findViewById(R.id.viewpager);

    //adapter
    TabsAdapter tabsAdapter = new TabsAdapter(getSupportFragmentManager(), this);
    viewPager.setAdapter(tabsAdapter);
    slidingTabLayout.setDistributeEvenly(true);
    slidingTabLayout.setViewPager(viewPager);
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    MenuInflater inflater = getMenuInflater();
    inflater.inflate(R.menu.menu_main, menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(@NonNull MenuItem item) {
    int id = item.getItemId();

    if (id == R.id.action_sair) {
      deslogarUsuario();
      return true;
    } else if (id == R.id.action_configuracoes) {
      return true;
    } else if (id == R.id.action_compartilhar) {
      compartilharFotos();
      return true;
    }

    return super.onOptionsItemSelected(item);
  }

  private void compartilharFotos() {
    Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
    startActivityForResult(intent, 1);
  }

  @Override
  protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
    super.onActivityResult(requestCode, resultCode, data);

    if(requestCode == 1 && resultCode == RESULT_OK && data != null){
      Uri uri = data.getData();
        try {
            Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
          ByteArrayOutputStream stream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG,75,stream);
            byte[] byteArray = stream.toByteArray();
          SimpleDateFormat simpleDateFormat = new SimpleDateFormat("ddmmaaaahhmmss");
          String nomeImagem = simpleDateFormat.format(new Date());
          ParseFile parseFile = new ParseFile(nomeImagem + "imagem.png",byteArray);
          ParseObject parseObject = new ParseObject("imagem");
          parseObject.put("username", ParseUser.getCurrentUser().getUsername());
          parseObject.put("imagem",parseFile);
          parseObject.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {
              if(e==null){
                Toast.makeText(MainActivity.this, "Imagem salva com sucesso", Toast.LENGTH_SHORT).show();
                TabsAdapter adapter = (TabsAdapter) viewPager.getAdapter();
                HomeFragment homeFragmentNovo = (HomeFragment) adapter.getFragment(0);
                homeFragmentNovo.atualizaPostagen();
              }else{
                Toast.makeText(MainActivity.this, "Erro ao salvar imagem", Toast.LENGTH_SHORT).show();
              }
            }
          });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
  }

  private void deslogarUsuario() {
    ParseUser.logOut();
    Intent intent = new Intent(this, LoginActivity.class);
    startActivity(intent);
    finish();
  }
}
