package drebin;

import android.content.Context;
import android.support.annotation.VisibleForTesting;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import drebin.core.Binder;
import drebin.core.BinderEnvironment;
import drebin.core.BinderSelector;
import drebin.core.StableIdsResolver;
import drebin.util.BinderRecyclerViewAdapter;
import drebin.util.BinderSelectorMapper;

public class Drebin {

  private Drebin() {
    // no direct instance, use with(Context)
  }

  public static DrebinBuilder with(Context context) {
    return new DrebinBuilder(context);
  }

  public static class DrebinBuilder {

    private final Context mContext;
    @VisibleForTesting List mItems;
    @VisibleForTesting BinderEnvironment mEnvironment;
    @VisibleForTesting BinderSelectorMapper mMapper;
    @VisibleForTesting StableIdsResolver mStableIdsResolver;

    public DrebinBuilder(final Context context) {
      mContext = context;
      mMapper = new BinderSelectorMapper();
    }

    public DrebinBuilder items(List items) {
      mItems = items;
      return this;
    }

    public DrebinBuilder environment(BinderEnvironment environment) {
      mEnvironment = environment;
      return this;
    }

    public DrebinBuilder binder(Class modelClass, BinderSelector selector) {
      mMapper.add(modelClass, selector);
      return this;
    }

    public DrebinBuilder binder(Class modelClass, Binder binder) {
      mMapper.add(modelClass, binder);
      return this;
    }

    public DrebinBuilder stabledIds(StableIdsResolver resolver) {
      mStableIdsResolver = resolver;
      return this;
    }

    public BinderRecyclerViewAdapter into(RecyclerView recyclerView) {
      mItems = mItems != null ? mItems : new ArrayList();
      mEnvironment = mEnvironment != null ? mEnvironment : BinderEnvironment.EmptyEnvironment.INSTANCE;
      final BinderRecyclerViewAdapter adapter = new BinderRecyclerViewAdapter(mContext, mItems, mEnvironment, mMapper, mStableIdsResolver);
      recyclerView.setAdapter(adapter);
      return adapter;
    }
  }
}
