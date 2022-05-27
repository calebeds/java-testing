package org.calebe;

import org.junit.Test;

import java.io.IOException;

public class AssertionExceptionTest {
    @Test(expected = IOException.class)
    public void willThrowThrows() throws Exception{
        willThrow();
    }


    public static void willThrow() throws IOException {
        throw new IOException("Bang");
    }
}
