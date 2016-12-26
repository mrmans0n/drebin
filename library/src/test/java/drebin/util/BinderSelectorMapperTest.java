package drebin.util;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.robolectric.RobolectricTestRunner;

import drebin.core.Binder;
import drebin.core.BinderEnvironment;
import drebin.core.BinderSelector;
import drebin.testing.TestModel;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.mockito.Mockito.when;

/**
 * Tests {@link BinderSelectorMapper}
 */
@RunWith(RobolectricTestRunner.class)
public class BinderSelectorMapperTest {

  @Rule public MockitoRule rule = MockitoJUnit.rule();
  @Mock public Binder mBinder;
  @Mock public BinderSelector<TestModel, BinderEnvironment> mBinderSelector;
  @Mock public BinderEnvironment mBinderEnvironment;
  private TestModel mModel;
  private BinderSelectorMapper mMapper;

  @Before
  public void setup() {
    mMapper = new BinderSelectorMapper();
    mModel = new TestModel();
  }

  @Test
  public void testAddBinder() {
    mMapper.add(TestModel.class, mBinder);
    assertThat(mMapper.binderForModel(mModel, mBinderEnvironment)).isEqualTo(mBinder);
  }

  @Test
  public void testAddBinderSelector() {
    when(mBinderSelector.select(mModel, mBinderEnvironment)).thenReturn(mBinder);
    mMapper.add(TestModel.class, mBinderSelector);
    assertThat(mMapper.binderForModel(mModel, mBinderEnvironment)).isEqualTo(mBinder);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testFailsWhenModelNotMapped() {
    mMapper.binderForModel(mModel, mBinderEnvironment);
  }
}
