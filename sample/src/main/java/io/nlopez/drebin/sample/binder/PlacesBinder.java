package io.nlopez.drebin.sample.binder;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import drebin.core.Binder;
import drebin.core.ViewFactory;
import drebin.core.ViewHost;
import io.nlopez.drebin.sample.R;
import io.nlopez.drebin.sample.model.Place;

public class PlacesBinder implements Binder<LinearLayout, PlacesBinder.PlaceViewHost, Place, HasPlacesEnvironment> {

  private static final ViewFactory<LinearLayout> VIEW_FACTORY = ViewFactory.INFLATE.fromLayout(R.layout.view_place);

  @Inject
  public PlacesBinder() {

  }

  @Override public ViewFactory<LinearLayout> getViewFactory() {
    return VIEW_FACTORY;
  }

  @Override public PlaceViewHost createViewHost(LinearLayout view) {
    return new PlaceViewHost(view);
  }

  @Override public void bind(final Place model, PlaceViewHost viewHost, final HasPlacesEnvironment environment) {
    viewHost.text.setText(model.getName());
    viewHost.image.setImageURI(model.getImageUrl());
    viewHost.rootView.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(final View view) {
        environment.placeSelected(model);
      }
    });
  }

  @Override public void unbind(PlaceViewHost viewHost, HasPlacesEnvironment environment) {
    viewHost.rootView.setOnClickListener(null);
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
