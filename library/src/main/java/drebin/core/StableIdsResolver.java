package drebin.core;

/**
 * Returns a unique ID based on the model
 */
public interface StableIdsResolver<M, BE extends BinderEnvironment> {
  int getId(M model, BE environment);
}
