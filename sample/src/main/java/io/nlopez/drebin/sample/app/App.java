package io.nlopez.drebin.sample.app;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;

import io.nlopez.drebin.sample.app.DaggerAppComponent;

public class App extends Application {

  private static AppComponent sComponent;

  @Override public void onCreate() {
    super.onCreate();
    Fresco.initialize(this);

    sComponent = DaggerAppComponent.builder()
            .appModule(new AppModule(this))
            .build();
  }

  public static AppComponent getComponent() {
    return sComponent;
  }
}
