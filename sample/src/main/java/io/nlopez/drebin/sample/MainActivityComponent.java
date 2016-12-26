package io.nlopez.drebin.sample;

import dagger.Component;
import io.nlopez.drebin.sample.app.AppComponent;

@Component(dependencies = AppComponent.class, modules = MainActivityModule.class)
public interface MainActivityComponent {
  void inject(MainActivity activity);
}
