package io.nlopez.drebin.sample;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import drebin.Drebin;
import drebin.util.BinderRecyclerViewAdapter;
import io.nlopez.drebin.sample.model.Place;
import io.nlopez.drebin.sample.model.User;
import io.nlopez.drebin.sample.binder.PlacesBinder;
import io.nlopez.drebin.sample.users.UsersBO;
import io.nlopez.drebin.sample.binder.UsersBinder;
import io.nlopez.drebin.sample.users.UsersPresenter;
import io.nlopez.drebin.sample.users.UsersViewComponent;
import revamp.android.PresenterActivity;

public class MainActivity extends PresenterActivity<UsersPresenter, UsersViewComponent> implements UsersViewComponent {
  @BindView(R.id.recycler_view)
  RecyclerView mRecyclerView;

  private final UsersBinder mUsersBinder = new UsersBinder();
  private final PlacesBinder mPlacesBinder = new PlacesBinder();
  private BinderRecyclerViewAdapter mAdapter;

  @Override
  public void onCreate(Bundle bundle) {
    super.onCreate(bundle);
    setContentView(R.layout.activity_recyclerview);
    ButterKnife.bind(this);

    initView();

    presenter().loadData();
  }

  private void initView() {
    mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    mAdapter = Drebin.with(this)
            .binder(User.class, mUsersBinder)
            .binder(Place.class, mPlacesBinder)
            .into(mRecyclerView);
  }

  @Override
  public UsersPresenter buildPresenter() {
    return new UsersPresenter(new UsersBO());
  }

  @Override
  public void fillListWithUsers(List<User> users) {
    mAdapter.addAll(users);
  }

  @Override
  public void highlightUserSelection(User user) {

  }
}
