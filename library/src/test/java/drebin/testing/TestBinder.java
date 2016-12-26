package drebin.testing;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import drebin.core.Binder;
import drebin.core.BinderEnvironment;
import drebin.core.ViewFactory;
import drebin.core.ViewHost;

public class TestBinder implements Binder<View, ViewHost<View>, TestModel, BinderEnvironment> {
  @Override public ViewFactory<View> getViewFactory() {
    return new ViewFactory<View>() {
      @Override public View create(final Context context, final ViewGroup parent) {
        return new View(context);
      }
    };
  }

  @Override public ViewHost<View> createViewHost(final View view) {
    return new ViewHost<>(view);
  }

  @Override public void bind(final TestModel model, final ViewHost<View> viewHost, final BinderEnvironment environment) {

  }

  @Override public void unbind(final ViewHost<View> viewHost, final BinderEnvironment environment) {

  }
}
