package com.fefelov.anagram;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

/**
 * Anagram collector.
 *
 * @author Vladislav Fefelov
 * @since 17.06.2019
 */
public class Anagram {

    private final boolean ignoreCase;

    public Anagram(boolean ignoreCase) {
        this.ignoreCase = ignoreCase;
    }

    public Anagram() {
        this(true);
    }

    public Collection<String> collectAnagrams(String[] dictionary, String search) {
        final String searchWithCase = ignoreCase ? search.toLowerCase() : search;
        final char[] sortedChars = asSortedChars(searchWithCase);
        final int searchHash = hash(searchWithCase);

        return Arrays.stream(dictionary)
            .parallel()
            .filter(subject -> isAnagram(subject, sortedChars, searchHash))
            .collect(Collectors.toList());
    }

    private char[] asSortedChars(String source) {
        final char[] chars = source.toCharArray();

        Arrays.sort(chars);

        return chars;
    }

    private int hash(String word) {
        final int length = word.length();

        int hash = 0;

        for (int i = 0; i < length; i++) {
            hash += word.charAt(i);
        }

        return hash;
    }

    private boolean isAnagram(String subject, char[] searchAsSortedChars, int searchHash) {
        final int expectedLength = searchAsSortedChars.length;

        if (subject.length() != expectedLength) {
            return false;
        }

        final String subjectWithCase = ignoreCase ? subject.toLowerCase() : subject;

        if (searchHash != hash(subjectWithCase)) {
            return false;
        }

        final char[] subjectAsSortedChars = asSortedChars(subjectWithCase);

        for (int i = 0; i < expectedLength; i++) {
            if (searchAsSortedChars[i] != subjectAsSortedChars[i]) {
                return false;
            }
        }

        return true;
    }

}
