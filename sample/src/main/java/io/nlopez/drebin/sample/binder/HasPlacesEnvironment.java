package io.nlopez.drebin.sample.binder;

import drebin.core.BinderEnvironment;
import io.nlopez.drebin.sample.model.Place;

public interface HasPlacesEnvironment extends BinderEnvironment {
  void placeSelected(Place place);
}
