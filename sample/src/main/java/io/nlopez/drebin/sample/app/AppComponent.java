package io.nlopez.drebin.sample.app;

import dagger.Component;
import io.nlopez.drebin.sample.util.DataGenerator;
import io.nlopez.drebin.sample.util.Toaster;

@Component(modules = AppModule.class)
public interface AppComponent {
  DataGenerator getDataGenerator();

  Toaster getToaster();
}
