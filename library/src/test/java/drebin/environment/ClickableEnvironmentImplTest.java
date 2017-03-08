package drebin.environment;

import android.view.View;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.robolectric.RobolectricTestRunner;

import drebin.testing.TestModel;

import static org.mockito.Mockito.verify;

/**
 * Tests {@link ClickableEnvironmentImpl}
 */
@RunWith(RobolectricTestRunner.class)
public class ClickableEnvironmentImplTest {

  @Rule public MockitoRule mMockitoRule = MockitoJUnit.rule();
  @Mock private ClickableEnvironment.Listener<TestModel> mListener;
  @Mock private View mView;
  @Mock private TestModel mModel;

  private ClickableEnvironmentImpl<TestModel> mEnvironment;

  @Before
  public void setup() {
    mEnvironment = new ClickableEnvironmentImpl<>(mListener);
  }

  @Test
  public void testPerformClickPropagates() {
    mEnvironment.performClick(mView, mModel);
    verify(mListener).onClick(mView, mModel);
  }
}
