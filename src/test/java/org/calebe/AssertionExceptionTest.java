package org.calebe;

import org.junit.Test;

import java.io.IOException;
import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.Assertions.offset;

public class AssertionExceptionTest {
    @Test
    public void willThrowThrows() throws Exception{
        assertThatThrownBy(() -> willThrow())
                .isInstanceOf(IOException.class)
                .hasMessage("Bang");
        // DO FINAL CHECKS
    }


    public static void willThrow() throws IOException {
        throw new IOException("Bang");
    }
}
