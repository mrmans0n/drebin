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
import io.nlopez.drebin.sample.model.User;

public class UsersBinder implements Binder<LinearLayout, UsersBinder.UserViewHost, User, BinderEnvironment> {

  private static final ViewFactory<LinearLayout> VIEW_FACTORY = ViewFactory.INFLATE.fromLayout(R.layout.view_user);

  @Override public ViewFactory<LinearLayout> getViewFactory() {
    return VIEW_FACTORY;
  }

  @Override public UserViewHost createViewHost(LinearLayout view) {
    return new UserViewHost(view);
  }

  @Override public void bind(User model, UserViewHost viewHost, BinderEnvironment environment) {
    viewHost.text.setText(model.getFirstName() + " " + model.getLastName() + "\n" + model.getRole());
    viewHost.image.setImageURI(model.getAvatar());
  }

  @Override public void unbind(UserViewHost viewHost, BinderEnvironment environment) {

  }

  static class UserViewHost extends ViewHost<LinearLayout> {
    @BindView(R.id.user_image) SimpleDraweeView image;
    @BindView(R.id.user_text) TextView text;

    public UserViewHost(LinearLayout view) {
      super(view);
      ButterKnife.bind(this, view);
    }
  }
}
