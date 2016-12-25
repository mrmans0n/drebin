package drebin.core;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Knows how to create views that will be bound.
 * @param <V> view class of the root view
 */
public interface ViewFactory<V extends View> {
  V create(Context context, ViewGroup parent);

  /**
   * Convenience class for creating a view based on a layout resource
   */
  class INFLATE {
    public static <V extends View> ViewFactory<V> fromLayout(@LayoutRes final int resId) {
      return new ViewFactory<V>() {
        @Override public V create(final Context context, final ViewGroup parent) {
          return (V) LayoutInflater.from(context).inflate(resId, parent, false);
        }
      };
    }
  }
}
