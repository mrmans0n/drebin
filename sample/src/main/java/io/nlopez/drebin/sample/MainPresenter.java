package io.nlopez.drebin.sample;

import android.support.annotation.NonNull;

import java.util.List;

import javax.inject.Inject;

import io.nlopez.drebin.sample.model.Place;
import io.nlopez.drebin.sample.model.User;
import revamp.base.BasePresenter;

public class MainPresenter extends BasePresenter<MainBO, MainViewComponent> {

  @Inject
  public MainPresenter(@NonNull MainBO businessObject) {
    super(businessObject);
  }

  public void loadData() {
    List elements = bo().getElements();
    view().fillListWithData(elements);
  }

  public void placeSelected(final Place place) {
    view().showPlaceInfo(place);
  }

  public void userSelected(final User user) {
    view().showUserInfo(user);
  }
}
