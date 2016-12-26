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
import io.nlopez.drebin.sample.model.User;

public class UsersBinder implements Binder<LinearLayout, UsersBinder.UserViewHost, User, HasUsersEnvironment> {

  private static final ViewFactory<LinearLayout> VIEW_FACTORY = ViewFactory.INFLATE.fromLayout(R.layout.view_user);

  @Inject
  public UsersBinder() {

  }

  @Override public ViewFactory<LinearLayout> getViewFactory() {
    return VIEW_FACTORY;
  }

  @Override public UserViewHost createViewHost(LinearLayout view) {
    return new UserViewHost(view);
  }

  @Override public void bind(final User model, UserViewHost viewHost, final HasUsersEnvironment environment) {
    viewHost.text.setText(model.getFirstName() + " " + model.getLastName() + "\n" + model.getRole());
    viewHost.image.setImageURI(model.getAvatar());
    viewHost.rootView.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(final View view) {
        environment.userSelected(model);
      }
    });
  }

  @Override public void unbind(UserViewHost viewHost, HasUsersEnvironment environment) {
    viewHost.rootView.setOnClickListener(null);
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
