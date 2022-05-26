package com.calebe.wordcount;

import org.junit.Test;

import static org.junit.Assert.*;

public class WordCounterTest {
    private WordCounter counter = new WordCounter();

    @Test
    public void emptyStringHasNoWords() {
        counter.add("");
        assertEquals(0, counter.getUniqueWordsCount());
    }

    @Test
    public void singleWordStringHasOneWord() {
        counter.add("Hello");
        assertEquals(1, counter.getUniqueWordsCount());
    }

    @Test
    public void twoSingleWordStringHaveTwoWord() {
        counter.add("Hello");
        counter.add("world");

        assertEquals(2, counter.getUniqueWordsCount());
    }

    @Test
    public void multiWordStringHasCorrectWordCount() {
        counter.add("One two three");

        assertEquals(3, counter.getUniqueWordsCount());
    }

    @Test
    public void spacedOutMultiWordStringHasCorrectWordCount() {
        counter.add("     One two three     ");

        assertEquals(3, counter.getUniqueWordsCount());
    }

    @Test
    public void severalMultiWordStringHasCorrectWordCount() {
        counter.add("One two three");
        counter.add("Four five six");

        assertEquals(6, counter.getUniqueWordsCount());
    }

    @Test
    public void inputWIthDupliatesCountsOnlyIndividualCases() {
        counter.add("Nine Nine Nine");

        assertEquals(1, counter.getUniqueWordsCount());
    }

}