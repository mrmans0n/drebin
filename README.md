Drebin
======

[![Build Status](https://travis-ci.org/mrmans0n/drebin.svg?branch=master)](https://travis-ci.org/mrmans0n/drebin)

Binder framework for Android RecyclerView. Name comes from the super agent John Drebin, as Drebin is an anagram for Binder.

![John Drebin](drebin.gif)

Adding to your project
----------------------

Add this to your dependencies:

```groovy
compile 'io.nlopez.drebin:library:1.0.0'
```

Usage
-----

We have to create Binder classes to let the system know how to render some object into a view. These binders have to be thought of as templates, and should not preserve state in any way.

Some simple binder example:

```java
public class UsersBinder implements Binder<LinearLayout, UsersBinder.UserViewHost, User, HasUsersEnvironment> {

  private static final ViewFactory<LinearLayout> VIEW_FACTORY = ViewFactory.INFLATE.fromLayout(R.layout.view_user);

  @Inject
  public UsersBinder() {

  }

  @Override public ViewFactory<LinearLayout> getViewFactory() {
    return VIEW_FACTORY;
  }

  @Override public UserViewHost createViewHost(LinearLayout view) {
    return new UserViewHost(view);
  }

  @Override public void bind(final User model, UserViewHost viewHost, final HasUsersEnvironment environment) {
    viewHost.text.setText(model.getFirstName() + " " + model.getLastName() + "\n" + model.getRole());
    viewHost.image.setImageURI(model.getAvatar());
    viewHost.rootView.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(final View view) {
        environment.userSelected(model);
      }
    });
  }

  @Override public void unbind(UserViewHost viewHost, HasUsersEnvironment environment) {
    viewHost.rootView.setOnClickListener(null);
  }

  // Convenience static class for injecting views
  static class UserViewHost extends ViewHost<LinearLayout> {
    @BindView(R.id.user_image) SimpleDraweeView image;
    @BindView(R.id.user_text) TextView text;

    public UserViewHost(LinearLayout view) {
      super(view);
      ButterKnife.bind(this, view);
    }
  }
}

```

The ViewFactory, usually defined statically, is the one responsible on how to create our view. It could be from a layout, like in the example, or just simply creating a new ViewFactory instance that creates a view.

The ViewHost works as a ViewHolder of sorts, and in cases where you would like to use ButterKnife for injecting views or simply want to do some convenience findViewById calls, you should create your own inheriting from that class. You can see a few examples in the sample app.

Then the binder will populate the view with the information from the model (or something provided from an environment), and unbind would do the cleanup.

For wiring it to a RecyclerView, you could do it this way:

```java
  Drebin.with(this)
    .items(mItemList)
    .environment(mEnvironment)
    .binder(User.class, mUsersBinder)
    .binder(Place.class, mPlacesBinder)
    .into(mRecyclerView);
```

You can add multiple binders, or even BinderSelector instances. You can add an environment class.

Please refer to the [sample](sample) for seeing it in action.

### BinderSelectors

In situations where you have different renderings for the same model object class, BinderSelectors come to the rescue. They will allow you to decide which Binder to be used for a particular model.

A simple example could be:

```java
public class UsersBinderSelector implements BinderSelector<User, BinderEnvironment> {

  @Inject UserAdminBinder mUserAdminBinder;
  @Inject UserVipBinder mUserVipBinder;
  @Inject UserBinder mUserBinder;

  @Inject
  public UsersBinderSelector() { }

  @Override public Binder select(final User model, final BinderEnvironment environment) {
    switch (model.getRole()) {
      case "VIP":
        return mUserVipBinder;
      case "Admin" ;
        return mUserAdminBinder;
    }
    return mUserBinder;
  }
}
```

### Environments

If you want to let the binder know about more stuff going on with your app apart from the model itself, you might want to create your own BinderEnvironment. It works both ways, from the app to the row being bound, and from the row being bound to the app.

The most typical case would be using the environment for receiving information triggered by user actions on your bound view, like a click or a long press.

An example interface could be:

```java
public interface HasUsersEnvironment extends BinderEnvironment {
  boolean isCurrentUser(User user);
  void userSelected(User user);
}
```

Or it could be a simpler one, like this:

```java
public class HasContextEnvironment implements BinderEnvironment {
  private Context mContext;
  public HasContextEnvironment(Context context) {
    mContext = context;
  }
  public Context getContext() { return mContext; }
}
```

Differences with [SmartAdapters](https://github.com/mrmans0n/smart-adapters)
------------------------------

The caveat I was finding when working with SmartAdapters was all the heavy lifting involved in working with BindableLayouts. As we were working with custom views, and didn't have access to the adapter, using an inflated layout was bothersome if you wanted to get rid of the 1st layer in the view hierarchy. So you had to do things like setting orientation in a LinearLayout programmatically.

With this approach the Binder, which roughly serves the same purpose as a BindableLayout, is apart from the view and is easily injectable. Also, it's easy to do composition between binders. And using BinderEnvironments is pretty flexible for things like multiple actions when tapping in different views. The environments and the selectors are easily injectable as well.

And we get rid of all the reflection magic of the builders, which is nice as well.

Contributing
------------
Forks, patches and other feedback are welcome.

Creators
--------

Nacho López - Github [@mrmans0n](https://github.com/mrmans0n) - Twitter [@mrmans0n](https://twitter.com/mrmans0n) - Blog [nlopez.io](http://nlopez.io)

License
-------

[MIT License](LICENSE)
