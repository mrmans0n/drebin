package drebin.core;

import android.view.View;

/**
 * Wraps a {@link View} class, an will be what will be passed onto your {@link Binder#bind(Object, ViewHost, BinderEnvironment)}
 * and {@link Binder#unbind(ViewHost, BinderEnvironment)} methods.
 * Ideally you would want to subclass this class and create your own implementation, where
 * you could have the resolved subviews (with findViewById, or ButterKnife, or whatever you want).
 *
 * @param <V>
 */
public class ViewHost<V extends View> {
  public final V rootView;

  public ViewHost(V view) {
    this.rootView = view;
  }
}
