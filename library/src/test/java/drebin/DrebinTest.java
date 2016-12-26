package drebin;

import android.support.v7.widget.RecyclerView;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;

import drebin.core.StableIdsResolver;
import drebin.util.BinderRecyclerViewAdapter;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 * Tests {@link Drebin}
 */
@RunWith(RobolectricTestRunner.class)
public class DrebinTest {

  @Rule public MockitoRule rule = MockitoJUnit.rule();
  @Mock public RecyclerView mRecyclerView;
  private Drebin.DrebinBuilder mBuilder;

  @Before
  public void setup() {
    mBuilder = Drebin.with(RuntimeEnvironment.application);
  }

  @Test
  public void testAttachToRecyclerView() {
    mBuilder.into(mRecyclerView);
    verify(mRecyclerView).setAdapter(any(BinderRecyclerViewAdapter.class));
  }

  @Test
  public void testNoEnvironmentCreatesEnvironment() {
    mBuilder.into(mRecyclerView);
    assertThat(mBuilder.mEnvironment).isNotNull();
  }

  @Test
  public void testNoItemsCreatesList() {
    mBuilder.into(mRecyclerView);
    assertThat(mBuilder.mItems).isNotNull();
  }

  @Test
  public void testNoStableIdsResolverMakesStableIdsFalse() {
    BinderRecyclerViewAdapter adapter = mBuilder.into(mRecyclerView);
    assertThat(adapter.hasStableIds()).isFalse();
  }

  @Test
  public void testStableIdsResolverMakesStableIdsTrue() {
    BinderRecyclerViewAdapter adapter =
            mBuilder.stabledIds(mock(StableIdsResolver.class))
            .into(mRecyclerView);
    assertThat(adapter.hasStableIds()).isTrue();
  }
}
