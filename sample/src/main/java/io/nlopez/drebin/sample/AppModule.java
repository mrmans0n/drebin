// Copyright (c) 2016-present Drebin

package io.nlopez.drebin.sample;

import android.content.Context;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {

  private final Context mContext;

  public AppModule(Context context) {
    mContext = context;
  }

  @Provides public Context provideContext() {
    return mContext;
  }
}
