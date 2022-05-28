package org.calebe;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.internal.stubbing.answers.AnswersWithDelay;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.runners.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;

import java.io.IOException;
import java.util.*;
import java.util.function.Predicate;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.BDDMockito.given;
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

    @Test(expected = IOException.class)
    public void willThrow() throws Exception {
        when(someInterface.isFileValid("myFile")).thenThrow(new IOException("Boom"));

        // when we run the code that uses the mock
        someInterface.isFileValid("myFile");
    }

    @Test
    public void thenAnswer() {
        when(someInterface.getMatchingSize(anyInt(), anyInt())).thenAnswer(new Answer<Integer>(){

            @Override
            public Integer answer(InvocationOnMock invocationOnMock) throws Throwable {
                return (int) invocationOnMock.getArguments()[0] + (int) invocationOnMock.getArguments()[1];
            }
        });

        //assert the mock... in real life
        //we would assert the outcome code using the mocks
        assertEquals(4, someInterface.getMatchingSize(1, 3));
    }

    @Test
    public void spying() {
        Set<Integer> set = spy(new LinkedHashSet<>());

        calculatePrimes(set, 100);

        assertTrue(set.contains(31));

        verify(set).add(31);
    }

    private static void calculatePrimes(Set<Integer> primes, int max) {
        for(int i = 2; i <= max; i++) {
            if(!divisibleByAny(i, primes)){
                primes.add(i);
            }
        }
    }

    private static boolean divisibleByAny(int newNumber, Set<Integer> primes) {
        for(Integer prime: primes) {
            if(newNumber % prime == 0) {
                return true;
            }
        }
        return false;
    }

    @Test
    public void bddMockito() throws Exception {
        //given a mock set up to validate my file
        given(someInterface.isFileValid("myFile"))
                .willReturn(true);

        //when we validate my file
        boolean isValid = someInterface.isFileValid("myFile");

        //then the answer is "Valid"
        assertTrue(isValid);
    }

    @Test
    public void whenWhenIsNotEnough() {
        Set<Integer> set = spy(new HashSet<>());

//        when(set.add(31)).thenThrow(new RuntimeException());
        doThrow(new RuntimeException("Boom"))
                .when(set)
                .add(31);
        System.out.println(set.contains(31));

    }

    @Test
    public void mockPredicate() {
        Predicate<Integer> predicate = i -> i == 12;

        assertTrue(predicate.test(12));
    }
}
