package io.nlopez.drebin.sample;

import dagger.Module;
import dagger.Provides;
import io.nlopez.drebin.sample.binder.MainActivityEnvironment;

@Module
public class MainActivityModule {

  private final MainActivityEnvironment.Listener mEnvironmentListener;

  public MainActivityModule(MainActivityEnvironment.Listener listener) {
    mEnvironmentListener = listener;
  }

  @Provides public MainActivityEnvironment.Listener getEnvironmentListener() {
    return mEnvironmentListener;
  }
}
