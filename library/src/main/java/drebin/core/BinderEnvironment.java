package drebin.core;

/**
 * Tags an environment class for our binders. Environments are useful for handling external
 * input/outputs to the elements of the list. For instance, if we wanted to handle clicks
 * in different elements of our bound view, we could route their onClick actions through
 * environment methods and implement them in the enclosing activity/fragment.
 * Or if we wanted to provide more external info apart from what's in the model we use
 * for binding, this would be the place to add it.
 */
public interface BinderEnvironment {
  /**
   * Empty implementation of a {@link BinderEnvironment}. Useful when you don't have
   * external input.
   */
  enum EmptyEnvironment implements BinderEnvironment {
    INSTANCE;
  }
}
