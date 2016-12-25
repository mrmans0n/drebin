package drebin.core;

/**
 * Selects the appropriate {@link Binder} class based on the model and its environment.
 *
 * @param <M> model class
 */
public interface BinderSelector<M, BE extends BinderEnvironment> {

  /**
   * @param model
   * @param environment
   * @return the appropriate {@link Binder} for the given model
   */
  Binder select(M model, BE environment);

  /**
   * Creates a {@link BinderSelector} based on an only possible {@link Binder}.
   */
  class BINDER {
    public static <M, BE extends BinderEnvironment> BinderSelector<M, BE> build(final Binder binder) {
      return new BinderSelector<M, BE>() {
        @Override public Binder select(final M item, final BE environment) {
          return binder;
        }
      };
    }
  }
}
