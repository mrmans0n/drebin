package drebin.environment;

import android.support.annotation.NonNull;
import android.view.View;

/**
 * Handles onClick events
 */
public class ClickableEnvironmentImpl<M> implements ClickableEnvironment<M> {

  @NonNull private final Listener<M> mListener;

  public ClickableEnvironmentImpl(@NonNull Listener<M> listener) {
    mListener = listener;
  }

  public void performClick(@NonNull View view, @NonNull M model) {
    mListener.onClick(view, model);
  }
}
