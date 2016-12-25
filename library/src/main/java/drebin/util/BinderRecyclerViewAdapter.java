package drebin.util;

import android.content.Context;
import android.support.v4.util.SparseArrayCompat;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.List;

import drebin.core.Binder;
import drebin.core.BinderEnvironment;
import drebin.core.StableIdsResolver;
import drebin.core.ViewHost;

public class BinderRecyclerViewAdapter extends RecyclerView.Adapter<BinderRecyclerViewAdapter.BinderViewHolder> {

  private final List mItems;
  private final SparseArrayCompat<Binder> mBinderCache;
  private final Context mContext;
  private final BinderSelectorMapper mMapper;
  private final BinderEnvironment mEnvironment;
  private final StableIdsResolver mIdResolver;

  public BinderRecyclerViewAdapter(Context context, List<?> items, BinderEnvironment environment, BinderSelectorMapper mapper, StableIdsResolver stableIdsResolver) {
    mContext = context;
    mEnvironment = environment;
    mBinderCache = new SparseArrayCompat<>();
    mItems = items;
    mMapper = mapper;
    mIdResolver = stableIdsResolver;
    setHasStableIds(mIdResolver != null);
  }

  public void add(Object item) {
    mItems.add(item);
  }

  public void addAll(List items) {
    mItems.addAll(items);
  }

  public void clear() {
    mItems.clear();
  }

  @Override public int getItemViewType(final int position) {
    Object rawItem = mItems.get(position);
    Binder binder = mMapper.binderForModel(rawItem, mEnvironment);
    mBinderCache.put(binder.hashCode(), binder);
    return binder.hashCode();
  }

  @Override public BinderRecyclerViewAdapter.BinderViewHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {
    Binder binder = mBinderCache.get(viewType);
    ViewHost viewHost = binder.createViewHost(binder.getViewFactory().create(mContext, parent));
    return new BinderViewHolder(viewHost, binder, mEnvironment);
  }

  @Override public void onBindViewHolder(final BinderRecyclerViewAdapter.BinderViewHolder holder, final int position) {
    Object item = mItems.get(position);
    holder.bind(item);
  }

  @Override public void onViewDetachedFromWindow(final BinderViewHolder holder) {
    holder.unbind();
  }

  @Override public int getItemCount() {
    return mItems.size();
  }

  @Override public long getItemId(final int position) {
    if (hasStableIds()) {
      return mIdResolver.getId(mItems.get(position), mEnvironment);
    }
    return super.getItemId(position);
  }

  static class BinderViewHolder extends RecyclerView.ViewHolder {
    private ViewHost mViewHost;
    private BinderEnvironment mBinderEnvironment;
    private Binder mBinder;

    BinderViewHolder(final ViewHost viewHost, final Binder binder, final BinderEnvironment binderEnvironment) {
      super(viewHost.rootView);
      mViewHost = viewHost;
      mBinder = binder;
      mBinderEnvironment = binderEnvironment;
    }

    public void bind(Object object) {
      mBinder.bind(object, mViewHost, mBinderEnvironment);
    }

    public void unbind() {
      mBinder.unbind(mViewHost, mBinderEnvironment);
    }
  }
}
