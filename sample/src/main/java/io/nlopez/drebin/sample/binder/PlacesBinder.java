package io.nlopez.drebin.sample.binder;

import android.widget.LinearLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import butterknife.BindView;
import butterknife.ButterKnife;
import drebin.core.Binder;
import drebin.core.BinderEnvironment;
import drebin.core.ViewFactory;
import drebin.core.ViewHost;
import io.nlopez.drebin.sample.R;
import io.nlopez.drebin.sample.model.Place;

public class PlacesBinder implements Binder<LinearLayout, PlacesBinder.PlaceViewHost, Place, BinderEnvironment> {

  private static final ViewFactory<LinearLayout> VIEW_FACTORY = ViewFactory.INFLATE.fromLayout(R.layout.view_place);

  @Override public ViewFactory<LinearLayout> getViewFactory() {
    return VIEW_FACTORY;
  }

  @Override public PlaceViewHost createViewHost(LinearLayout view) {
    return new PlaceViewHost(view);
  }

  @Override public void bind(Place model, PlaceViewHost viewHost, BinderEnvironment environment) {
    viewHost.text.setText(model.getName());
    viewHost.image.setImageURI(model.getImageUrl());
  }

  @Override public void unbind(PlaceViewHost viewHost, BinderEnvironment environment) {

  }

  static class PlaceViewHost extends ViewHost<LinearLayout> {
    @BindView(R.id.place_image) SimpleDraweeView image;
    @BindView(R.id.place_text) TextView text;

    public PlaceViewHost(LinearLayout view) {
      super(view);
      ButterKnife.bind(this, view);
    }
  }
}
