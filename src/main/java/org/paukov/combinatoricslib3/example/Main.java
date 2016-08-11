package org.paukov.combinatoricslib3.example;

import org.paukov.combinatorics3.Generator;

import java.util.List;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {

        List<List<String>> combinations = Generator.combination("red", "black", "white", "green", "blue")
                .simple(3)
                .stream()
                .collect(Collectors.<List<String>>toList());

        combinations.stream().forEach(System.out::println);
    }
}
