//Nikita Smirnov n01287334 RNB
package nikita.smirnov.n01287334.nikitalab3;

import androidx.appcompat.app.AppCompatDelegate;
import androidx.lifecycle.ReportFragment;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.internal.verification.VerificationModeFactory.times;

//@RunWith((PowerMockRunner.class))
//@PrepareForTest({ ReportFragment.class })

public class SmirnovUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

//    @Test
//    public void test_onCreate() {
//        // Mock some data
//        mockStatic(HomeFragment.class);
//        MyActivity activity = spy(new MyActivity());
//        doNothing().when(activity).initScreen();
//        doNothing().when(activity).setContentView(R.layout.layout);
//        doReturn(mock(AppCompatDelegate.class)).when(activity).getDelegate();
//        // Call the method
//        activity.onCreate(null);
//        // Verify that it worked
//        verify(activity, times(1)).setContentView(R.layout.layout);
//        verify(activity, times(1)).initScreen();
//    }


}