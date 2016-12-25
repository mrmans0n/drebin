package io.nlopez.drebin.sample.users;

import android.support.annotation.NonNull;

import java.util.List;

import io.nlopez.drebin.sample.model.User;
import revamp.base.BasePresenter;

public class UsersPresenter extends BasePresenter<UsersBO, UsersViewComponent> {
  public UsersPresenter(@NonNull UsersBO businessObject) {
    super(businessObject);
  }

  public void loadData() {
    List<User> users = bo().getUsers();
    view().fillListWithUsers(users);
  }

  public void userSelected(User user) {
    bo().storeUser(user);
    view().highlightUserSelection(user);
  }

}
