package io.nlopez.drebin.sample.app;

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
