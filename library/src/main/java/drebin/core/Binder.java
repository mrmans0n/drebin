package drebin.core;

import android.view.View;

/**
 * Defines a binding template. It is a class used to define how views will be created and
 * bound when rendering a collection of things on screen.
 * <p>
 * IT SHOULD BE USED AS A TEMPLATE, and NEVER STORE STATEFUL DATA.
 * <p>
 * Every method in here should be used as if it were a static method.
 *
 * @param <V>  view class for the root view of the object we want to create
 * @param <VH> enclosing class for handling the view.
 * @param <M>  object we are trying to render in a view
 * @param <BE> environment for the binder. {@link BinderEnvironment} serves as an empty environment class.
 */
public interface Binder<V extends View, VH extends ViewHost<V>, M, BE extends BinderEnvironment> {
  /**
   * @return an enclosing {@link ViewFactory} for a {@link View}. The {@link ViewFactory}
   * knows how to create a view.
   */
  ViewFactory<V> getViewFactory();

  /**
   * @param view a root view created in the {@link ViewFactory}
   * @return an enclosing {@link ViewHost} class.
   * <p>
   * The ViewHost would be the place to use injectors like ButterKnife or perform any decoration
   * on the view.
   */
  VH createViewHost(V view);

  /**
   * Displays the data from the model into the view.
   *
   * @param model
   * @param viewHost
   * @param environment
   */
  void bind(M model, VH viewHost, BE environment);

  /**
   * Cleans up anything done in the {@link #bind(Object, ViewHost, BinderEnvironment)} method.
   *
   * @param viewHost
   * @param environment
   */
  void unbind(VH viewHost, BE environment);
}
