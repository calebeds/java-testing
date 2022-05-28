package org.calebe;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.*;
import static org.hamcrest.Matcher.*;

@RunWith(MockitoJUnitRunner.class)
public class MockitoExamplesTest {
    @Mock
    private SomeInterface someInterface;

    @Captor
    private ArgumentCaptor<List<String>> listCaptor;

    @Test
    public void verifyExample() {
        //simulated code under test
        someInterface.getMatchingSize(1, 2);

        verify(someInterface).getMatchingSize(1, 2);//it was called
    }

    @Test
    public void verifyMultipleExample() {
        // Simulated code under test
        someInterface.getMatchingSize(1, 2);
        someInterface.getMatchingSize(1, 2);

        verify(someInterface, times(2)).getMatchingSize(1, 2);//It was called 2 times
    }

    @Test
    public void verifyConcurrent() {
        // simulated code under test
        new Thread(() -> {
            for(int i = 0; i < 20; i++) {
                someInterface.getMatchingSize(1, 2);
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    //////
                }
            }
        }).start();

        verify(someInterface, timeout(2000).atLeast(20)).getMatchingSize(1, 2);
    }

    @Test
    public void verifyArgument() {
        // simulated code under test
        someInterface.receiveList(Arrays.asList("A", "B", "C"));

        // capture argument and verify it
        verify(someInterface).receiveList(listCaptor.capture());//Verify if this method was called with a list
        assertEquals(3, listCaptor.getValue().size());//Get the captured argument and return its values then siz2
    }

    @Test
    public void verifyArgumentWithoutCaptors() {
        // simulated code under test
        someInterface.receiveList(Arrays.asList("A", "B", "C"));
        // Capture argument and verify it
        verify(someInterface).receiveList(argThat(list -> list.size() == 3));
    }
}
