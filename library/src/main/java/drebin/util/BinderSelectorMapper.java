package drebin.util;

import android.support.annotation.NonNull;
import android.support.v4.util.ArrayMap;

import drebin.core.Binder;
import drebin.core.BinderEnvironment;
import drebin.core.BinderSelector;

/**
 * Convenience mapper from model classes to binder selectors.
 */
public class BinderSelectorMapper {

  private final ArrayMap<Class, Entry> mEntries;

  public BinderSelectorMapper() {
    mEntries = new ArrayMap<>();
  }

  public void add(Class modelClass, Binder binder) {
    add(modelClass, BinderSelector.BINDER.build(binder));
  }

  public void add(Class modelClass, BinderSelector binderSelector) {
    mEntries.put(modelClass, new Entry(modelClass, binderSelector));
  }

  @NonNull Binder binderForModel(Object model, BinderEnvironment environment) {
    Entry entry = mEntries.get(model.getClass());
    if (entry == null) {
      throw new IllegalArgumentException("Object " + model + " has no binder registered.");
    }
    return entry.binderSelector.select(model, environment);
  }

  static class Entry {
    final Class modelClass;
    final BinderSelector binderSelector;

    Entry(Class modelClass, BinderSelector binderSelector) {
      this.modelClass = modelClass;
      this.binderSelector = binderSelector;
    }
  }
}
