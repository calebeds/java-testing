package com.calebe.wordcount;

import java.util.HashSet;
import java.util.Set;

public class WordCounter {
    private Set<String> wordsSoFar = new HashSet<>();
    public void add(String sentence) {
        if(!sentence.isEmpty()) {
            for(String word : sentence.trim().split("\\s+")) {
                wordsSoFar.add(word);
            }
        }
    }

    public int getUniqueWordsCount() {
        return wordsSoFar.size();
    }
}
