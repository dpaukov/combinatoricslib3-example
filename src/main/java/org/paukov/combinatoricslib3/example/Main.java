package org.paukov.combinatoricslib3.example;

import java.net.BindException;
import java.util.stream.IntStream;
import org.paukov.combinatorics3.Generator;

import java.util.Arrays;
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

        // Example 5.1 - All subsets
        System.out.println("Subsets of  (one, two, three):");
        Generator.subset("one", "two", "three")
                .simple()
                .stream()
                .forEach(System.out::println);

        // Example 5.2 - Subsets of the exact number of elements
        System.out.println("Subsets of (one, two, three) that have 2 numbers:");
        Generator.combination("one", "two", "three")
            .simple(2)
            .stream()
            .forEach(System.out::println);

        // Example 5.3 - All subsets ordered by size (number of the elements)
        System.out.println("All Subsets of (one, two, three) ordered by size (number of elements)");
        String[] arr = new String[]{"one", "two", "three"};
        for (int i=0; i<=arr.length; i++) {
            Generator.combination(arr)
                .simple(i)
                .stream()
                .forEach(System.out::println);
        }

        // Example 6
        System.out.println("Cartesian product of the lists (1, 2, 3), (8), and (10, 20) :");
        Generator.cartesianProduct(Arrays.asList(1,2,3), Arrays.asList(8), Arrays.asList(10, 20))
                .stream()
                .forEach(System.out::println);

        // Example 7. k-Permutations without repetitions
        System.out.println("2-Permutations without repetitions of the lists (1, 2, 3) :");
        Generator.combination(1, 2, 3)
            .simple(2)
            .stream()
            .forEach(combination -> Generator.permutation(combination)
                .simple()
                .forEach(System.out::println));

        // Example 8. k-Permutations with repetitions
        System.out.println("2-Permutations with repetitions of the lists (1, 2, 3) :");
        Generator.combination(1, 2, 3)
            .multi(2)
            .stream()
            .forEach(combination -> Generator.permutation(combination)
                .simple()
                .forEach(System.out::println));

    }
}
