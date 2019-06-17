package com.fefelov.anagram;

import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.concurrent.TimeUnit;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Warmup;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

/**
 * Anagram benchmark.
 *
 * <p>The benchmark runs anagram check for each word in the dictionary against all other words.
 *
 * @author Vladislav Fefelov
 * @since 17.06.2019
 */
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@State(Scope.Benchmark)
@Fork(value = 5)
@Warmup(iterations = 3)
@Measurement(iterations = 5)
public class AnagramBenchmark {

    private Anagram anagram = new Anagram();

    private String[] dictionary;

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
            .include(AnagramBenchmark.class.getSimpleName())
            .build();

        new Runner(opt).run();
    }

    @Setup
    public void setUp() throws Exception {
        final String path = Anagram.class.getClassLoader().getResource("pldf-win.txt").getPath();
        dictionary = Files.readAllLines(Paths.get(path), Charset.forName("CP1251"))
            .toArray(new String[0]);
    }

    @Benchmark
    public void collectAnagrams() {
        for (String word : dictionary) {
            anagram.collectAnagrams(dictionary, word);
        }
    }

}
