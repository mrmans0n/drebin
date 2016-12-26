package io.nlopez.drebin.sample;

import java.util.List;

import io.nlopez.drebin.sample.model.Place;
import io.nlopez.drebin.sample.model.User;
import revamp.base.ViewComponent;

public interface MainViewComponent extends ViewComponent {
  void fillListWithData(List elements);

  void showUserInfo(User user);

  void showPlaceInfo(Place place);
}
