/*
 * Copyright (c) 2015-present, Parse, LLC.
 * All rights reserved.
 *
 * This source code is licensed under the BSD-style license found in the
 * LICENSE file in the root directory of this source tree. An additional grant
 * of patent rights can be found in the PATENTS file in the same directory.
 */
package com.parse.starter;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseACL;


public class StarterApplication extends Application {

  @Override
  public void onCreate() {
    super.onCreate();

    // Habilite armazenamento local.
    Parse.enableLocalDatastore(this);

    // Codigo de configuração do App
    Parse.initialize(new Parse.Configuration.Builder(getApplicationContext())
            .applicationId("EdgC4vkbejbjHxgkLwjwLmn9QPzWikH4arQGgGbz")
            .clientKey("EZYNjNcyUAlwudFP0OIXsV2GMrUk1XLR8TtBq9dB")
            .server("https://parseapi.back4app.com")
    .build()
    );

    // Habilita o acesso de leitura pública para que usuários apareçam na lista
    ParseACL defaultACL = new ParseACL();
    defaultACL.setPublicReadAccess(true); 
    ParseACL.setDefaultACL(defaultACL, true);
  }
}