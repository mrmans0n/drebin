package drebin.environment;

import android.support.annotation.NonNull;
import android.view.View;

/**
 * Handles onClick events
 */
public interface ClickableEnvironment<M> {
  void performClick(@NonNull View view, @NonNull M model);

  interface Listener<T> {
    void onClick(@NonNull View view, @NonNull T Model);
  }
}
