package org.paukov.combinatoricslib3.example;

import org.paukov.combinatorics3.Generator;

import java.util.List;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {

        // Example 1
        System.out.println("Simple combinations of (red, black, white, green, blue):");
        List<List<String>> combinations = Generator.combination("red", "black", "white", "green", "blue")
                .simple(3)
                .stream()
                .collect(Collectors.<List<String>>toList());
        combinations.stream().forEach(System.out::println);

        // Example 2
        System.out.println("Combinations with repetitions of (apple, orange):");
        Generator.combination(new String[] { "apple", "orange" })
                .multi(3)
                .stream()
                .forEach(System.out::println);

        // Example 3
        System.out.println("Simple permutations of (apple, orange, cherry):");
        Generator.permutation("apple", "orange", "cherry")
                .simple()
                .stream()
                .forEach(System.out::println);

        // Example 4
        System.out.println("Permutations with repetitions of (apple, orange):");
        List<List<String>> permutations = Generator
                .permutation("apple", "orange")
                .withRepetitions(3)
                .stream()
                .collect(Collectors.<List<String>>toList());
        permutations.stream().forEach(System.out::println);
    }
}
