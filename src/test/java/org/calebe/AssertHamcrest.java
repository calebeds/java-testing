package org.calebe;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import java.util.Arrays;

import static org.hamcrest.Matchers.closeTo;
import static org.hamcrest.Matchers.contains;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

/**
 * Refactored to use assertThat and Hamcrest - most of which is automatically included in JUnit
 * in *hamcrest-core* though "hamcrest-all" has been added to the project to support comparison of doubles.
 */
public class AssertHamcrest {
    @Test
    public void tryAssertTrue() {
        Rectangle rectangle = new Rectangle(10, 10);

        assertThat(rectangle.isSquare(), is(true));
    }

    @Test
    public void tryAssertFalse() {
        Rectangle rectangle = new Rectangle(1, 10);

        assertThat(rectangle.isSquare(), is(false));
    }

    @Test
    public void tryAssertTrue2() {
        Rectangle rectangle = new Rectangle(10, 10);

        assertThat("Rectangle with same width and height should be square",
                rectangle.isSquare(),
                is(true));
    }

    @Test
    public void tryAssertNull() {
        Object value = null;

        assertThat(value, nullValue());
    }

    @Test
    public void tryAssertNotNull() {
        Object value = new Rectangle(1, 1);

        assertThat(value, is(notNullValue()));
    }

    @Test
    public void tryAssertEquals() {
        assertThat(new Rectangle(2, 8).getArea(), is(16) );
        assertThat(new Rectangle(2, 8).getArea(),equalTo(16));
        assertThat(new Rectangle(2, 8).getArea(), is(equalTo(16)));
    }

    @Test
    public void tryAssertEqualsWithStrings() {
        assertThat("Hello\nworld\nnow", is("Hello\nworld\nnow"));
    }

    @Test
    public void tryAssertNotEquals() {
        assertThat(new Rectangle(2, 8).getArea(), is(not(99999)));
    }

    @Test
    public void tryAssertEqualsWithDouble() {
        double aspectRatio = new Rectangle(1, 3).getAspectRatio();

        assertThat(aspectRatio, closeTo(0.3333333333333, 0.001));
    }

    @Test
    public void tryAssertEqualsWithObject() {
        assertThat(new Rectangle(1, 1), is(new Rectangle(1,1)));
    }

    @Test
    public void tryAssertSame() {
        Rectangle example1 = new Rectangle(4, 5);
        Rectangle example1SecondReference = example1;

        assertThat(example1, sameInstance(example1SecondReference));

        Rectangle duplicateOfExample1 = new Rectangle(4, 5);

        assertThat(example1, not(sameInstance(duplicateOfExample1)));
        assertThat(example1, equalTo(duplicateOfExample1));
    }

    @Ignore("Some reason")//It works if this test class were run
    @Test
    public void tryAssertFail() {
        Assert.fail("This was never meant to happen");
    }

    @Test
    public void tryAssertArray() {
        Rectangle rectangle = new Rectangle(5, 6);

        assertArrayEquals(new int[] {5, 6}, rectangle.getSides());//It can't be done with Hamcrest because it uses Generics and int is primitive type. One solution would be use Integer class.
    }

    @Test
    public void tryAssertList() {
        Rectangle rectangle = new Rectangle(7, 6);

        assertThat(rectangle.getSidesList(), contains(7, 6));
    }
}
