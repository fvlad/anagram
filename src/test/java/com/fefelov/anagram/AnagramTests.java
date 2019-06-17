package com.fefelov.anagram;

import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.junit.Test;

import static org.junit.Assert.assertFalse;

public class AnagramTests {

    private Anagram anagram = new Anagram(true);

    @Test
    public void testAnagrams() throws Exception {
        final String path = Anagram.class.getClassLoader().getResource("pldf-win.txt").getPath();
        final String[] dictionary = Files.readAllLines(Paths.get(path), Charset.forName("CP1251"))
            .toArray(new String[0]);

        assertFalse(anagram.collectAnagrams(dictionary, dictionary[4]).isEmpty());
    }

}
