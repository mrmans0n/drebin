package io.nlopez.drebin.sample.users;

import java.util.List;

import io.nlopez.drebin.sample.model.User;
import revamp.base.ViewComponent;

public interface UsersViewComponent extends ViewComponent {
  void fillListWithUsers(List<User> users);

  void highlightUserSelection(User user);
}
