package io.nlopez.drebin.sample;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import drebin.Drebin;
import drebin.util.BinderRecyclerViewAdapter;
import io.nlopez.drebin.sample.app.App;
import io.nlopez.drebin.sample.binder.MainActivityEnvironment;
import io.nlopez.drebin.sample.binder.PlacesBinder;
import io.nlopez.drebin.sample.binder.UsersBinder;
import io.nlopez.drebin.sample.model.Place;
import io.nlopez.drebin.sample.model.User;
import io.nlopez.drebin.sample.util.Toaster;
import revamp.android.PresenterActivity;

public class MainActivity extends PresenterActivity<MainPresenter, MainViewComponent> implements MainViewComponent, MainActivityEnvironment.Listener {
  @BindView(R.id.recycler_view) RecyclerView mRecyclerView;
  @Inject MainPresenter mPresenter;
  @Inject UsersBinder mUsersBinder;
  @Inject PlacesBinder mPlacesBinder;
  @Inject MainActivityEnvironment mEnvironment;
  @Inject Toaster mToaster;
  private BinderRecyclerViewAdapter mAdapter;

  @Override
  public void onCreate(Bundle bundle) {
    injectDependencies();
    super.onCreate(bundle);
    setContentView(R.layout.activity_recyclerview);
    ButterKnife.bind(this);

    initView();

    presenter().loadData();
  }

  private void injectDependencies() {
    DaggerMainActivityComponent.builder()
            .appComponent(((App) getApplication()).getComponent())
            .mainActivityModule(new MainActivityModule(this))
            .build()
            .inject(this);
  }

  private void initView() {
    mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    mAdapter = Drebin.with(this)
            .environment(mEnvironment)
            .binder(User.class, mUsersBinder)
            .binder(Place.class, mPlacesBinder)
            .into(mRecyclerView);
  }

  @Override public MainPresenter buildPresenter() {
    return mPresenter;
  }

  @Override public void fillListWithData(List elements) {
    mAdapter.addAll(elements);
    mAdapter.notifyDataSetChanged();
  }

  @Override public void onPlaceSelected(final Place place) {
    presenter().placeSelected(place);
  }

  @Override public void onUserSelected(final User user) {
    presenter().userSelected(user);
  }

  @Override public void showUserInfo(User user) {
    mToaster.show(user.getFirstName());
  }

  @Override public void showPlaceInfo(Place place) {
    mToaster.show(place.getName());
  }
}
