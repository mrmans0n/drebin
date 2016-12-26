package io.nlopez.drebin.sample.binder;

import drebin.core.BinderEnvironment;
import io.nlopez.drebin.sample.model.User;

public interface HasUsersEnvironment extends BinderEnvironment {
  void userSelected(User user);
}
