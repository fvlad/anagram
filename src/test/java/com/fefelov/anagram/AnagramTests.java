package com.fefelov.anagram;

import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class AnagramTests {

    private Anagram anagram = new Anagram();

    @Test
    public void testAnagrams() throws Exception {
        assertEquals(
            Arrays.asList("abc", "BAc", "bca", "Bac"),
            anagram.collectAnagrams(Arrays.asList("abc", "Aaa", "aaa", "BAc", "bca", "aba", "Bac").toArray(new String[0]), "abc"));
    }

    @Test
    public void testDictionary() throws Exception {
        final String path = Anagram.class.getClassLoader().getResource("pldf-win.txt").getPath();
        final String[] dictionary = Files.readAllLines(Paths.get(path), Charset.forName("CP1251"))
            .toArray(new String[0]);

        assertFalse(anagram.collectAnagrams(dictionary, dictionary[4]).isEmpty());
    }

}
