package io.nlopez.drebin.sample.binder;

import javax.inject.Inject;

import io.nlopez.drebin.sample.model.Place;
import io.nlopez.drebin.sample.model.User;

public class MainActivityEnvironment implements HasPlacesEnvironment, HasUsersEnvironment {

  @Inject Listener mListener;

  @Inject
  public MainActivityEnvironment() {
  }

  @Override public void placeSelected(Place place) {
    mListener.onPlaceSelected(place);
  }

  @Override public void userSelected(User user) {
    mListener.onUserSelected(user);
  }

  public interface Listener {
    void onPlaceSelected(Place place);

    void onUserSelected(User user);
  }
}
