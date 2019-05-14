[![Build Status](https://travis-ci.org/dpaukov/combinatoricslib3-example.svg?branch=master)](https://travis-ci.org/dpaukov/combinatoricslib3-example)

# combinatoricslib3-example
Code examples of how to use the [combinatoricslib3 for Java 8](https://github.com/dpaukov/combinatoricslib3)

## Add combinatoricslib3 v3.3.0 to your project
```xml
<dependency>
    <groupId>com.github.dpaukov</groupId>
    <artifactId>combinatoricslib3</artifactId>
    <version>3.3.0</version>
</dependency>
```

## Start using the Generator class
For example, you can generate all combinations of `("red", "black", "white", "green", "blue")`

```java
import org.paukov.combinatorics3.Generator;

public class Example {
    public static void main(String[] args) {
        Generator.combination("red", "black", "white", "green", "blue")
                .simple(3)
                .stream()
                .forEach(System.out::println)
    }
}
```

## How to build and execute these examples
Clone the repository and run the following command:
```
mvn package exec:java -Dexec.mainClass="org.paukov.combinatoricslib3.example.Main"
```
Or you can run the example from command line
```
mvn clean install
java -jar target/combinatoricslib3-example-1.0.0-SNAPSHOT-jar-with-dependencies.jar
```

## Examples

1. [Simple combinations](#1-simple-combinations)
2. [Combinations with repetitions](#2-combinations-with-repetitions)
3. [Simple permutations](#3-simple-permutations)
4. [Permutations with repetitions](#4-permutations-with-repetitions)
5. [Subsets](#5-subsets)
6. [Integer partitions](#6-integer-partitions)
7. [Cartesian product](#7-cartesian-product)
8. [k-Permutations](#8-k-permutations)


| Description                      | Is Order Important? | Is Repetition Allowed? | Stream  |
|----------------------------------|:-------------------:|:----------------------:|---------| 
| [Simple combinations](#1-simple-combinations) | No | No | `Generator.combination(...).simple(n).stream()` |
| [Combinations with repetitions](#2-combinations-with-repetitions) | No | Yes | `Generator.combination(...).multi(n).stream()` |
| [Simple permutations](#3-simple-permutations) | Yes | No | `Generator.permutation(...).simple().stream()` |
| [Permutations with repetitions](#4-permutations-with-repetitions) | Yes | Yes | `Generator.permutation(...).withRepetitions(n).stream()` |


### 1. Simple combinations
A simple k-combination of a finite set S is a subset of k distinct elements of S. 
Specifying a subset does not arrange them in a particular order. As an example, a poker hand can 
be described as a 5-combination of cards from a 52-card deck: the 5 cards of the hand are all distinct, 
and the order of the cards in the hand does not matter.

Let's generate all 3-combination of the set of 5 colors (red, black, white, green, blue).

```java
   Generator.combination("red", "black", "white", "green", "blue")
       .simple(3)
       .stream()
       .forEach(System.out::println);

```
And the result of 10 combinations
```
   [red, black, white]
   [red, black, green]
   [red, black, blue]
   [red, white, green]
   [red, white, blue]
   [red, green, blue]
   [black, white, green]
   [black, white, blue]
   [black, green, blue]
   [white, green, blue]
```

### 2. Combinations with repetitions
A k-multicombination or k-combination with repetition of a finite set S is given by a sequence of 
k not necessarily distinct elements of S, where order is not taken into account.

As an example. Suppose there are 2 types of fruits (apple and orange) at a grocery store, and you 
want to buy 3 pieces of fruit. You could select
- (apple, apple, apple)
- (apple, apple, orange)
- (apple, orange, orange)
- (orange, orange, orange)

```java
   Generator
       .combination(new String[] { "apple", "orange" })
       .multi(3)
       .stream()
       .forEach(System.out::println);
```
And the result will be:
```
   [apple, apple, apple]
   [apple, apple, orange]
   [apple, orange, orange]
   [orange, orange, orange]
```

### 3. Simple permutations
A permutation is an ordering of a set in the context of all possible orderings. For example, the set 
containing the first three digits, 123, has six permutations: 123, 132, 213, 231, 312, and 321.

This is an example of the permutations of the 3 string items (apple, orange, cherry):

```java
   Generator
       .permutation("apple", "orange", "cherry")
       .simple()
       .stream()
       .forEach(System.out::println);
```

```
   [apple, orange, cherry]
   [apple, cherry, orange]
   [cherry, apple, orange]
   [cherry, orange, apple]
   [orange, cherry, apple]
   [orange, apple, cherry]
```

This generator can produce the permutations *even if an initial vector has duplicates*. For example, 
all permutations of (1, 1, 2, 2):

```java
   Generator.permutation(1, 1, 2, 2)
       .simple()
       .stream()
       .forEach(System.out::println);
```

The result does not have duplicates. All permutations are distinct by default.
```
   [1, 1, 2, 2]
   [1, 2, 1, 2]
   [1, 2, 2, 1]
   [2, 1, 1, 2]
   [2, 1, 2, 1]
   [2, 2, 1, 1]
```
Notice that we have 6 permutations here instead of 24. If you still need all permutations, 
you should call method `simple(PermutationGenerator.TreatDuplicatesAs.IDENTICAL)`.

### 4. Permutations with repetitions
Permutation may have more elements than slots. For example, all possible permutation of '12' 
in three slots are: 111, 211, 121, 221, 112, 212, 122, and 222.

Let's generate all possible permutations with repetitions of 3 elements from the set of apple and orange.

```java
   List<List<String>> permutations = Generator
        .permutation("apple", "orange")
        .withRepetitions(3)
        .stream()
        .collect(Collectors.<List<String>>toList());
        
   permutations.stream().forEach(System.out::println);
```

And the list of all 8 permutations

```
   [apple, apple, apple]
   [orange, apple, apple]
   [apple, orange, apple]
   [orange, orange, apple]
   [apple, apple, orange]
   [orange, apple, orange]
   [apple, orange, orange]
   [orange, orange, orange]
```

### 5. Subsets
A set A is a subset of a set B if A is "contained" inside B. A and B may coincide. 
The relationship of one set being a subset of another is called inclusion or sometimes containment.

Examples:

The set (1, 2) is a proper subset of (1, 2, 3).
Any set is a subset of itself, but not a proper subset.
The empty set, denoted by ∅, is also a subset of any given set X.
All subsets of (1, 2, 3) are:

- ()
- (1)
- (2)
- (1, 2)
- (3)
- (1, 3)
- (2, 3)
- (1, 2, 3)

Here is a piece of code that generates all possible subsets of (one, two, three)

```java
   List<List<String>> subsets = Generator
        .subset("one", "two", "three")
        .simple()
        .stream()
        .collect(Collectors.<List<String>>toList());
   subsets.stream().forEach(System.out::println);

```
And the list of all 8 subsets
```
   []
   [one]
   [two]
   [one, two]
   [three]
   [one, three]
   [two, three]
   [one, two, three]
```

### 6. Integer Partitions
In number theory, a partition of a positive integer `n` is a way of writing `n` as a sum of positive integers.
Two sums that differ only in the order of their summands are considered to be the same partition;
if order matters then the sum becomes a composition. A summand in a partition is also called a part.

The partitions of 5 are listed below:

- 1 + 1 + 1 + 1 + 1
- 2 + 1 + 1 + 1
- 2 + 2 + 1
- 3 + 1 + 1
- 3 + 2
- 4 + 1
- 5

Let's generate all possible partitions of 5:
```java
   Generator.partition(5)
       .stream()
       .forEach(System.out::println);
```
And the result of all 7 integer possible partitions:
```
   [1, 1, 1, 1, 1]
   [2, 1, 1, 1]
   [2, 2, 1]
   [3, 1, 1]
   [3, 2]
   [4, 1]
   [5]
```


### 7. Cartesian Product
In set theory, a Cartesian Product A × B is the set of all ordered pairs (a, b) where a ∈ A and b ∈ B.

As an example, suppose there are 3 sets of number, (1, 2, 3), (8) and (10, 20), and you want to get
the Cartesian product of them.

Source: [Cartesian Product](https://en.wikipedia.org/wiki/Cartesian_product)

```java
       Generator.cartesianProduct(Arrays.asList(1, 2, 3), Arrays.asList(8), Arrays.asList(10, 20))
           .stream()
           .forEach(System.out::println);
```
And the result will be:

```
   [1, 8, 10]
   [1, 8, 20]
   [2, 8, 10]
   [2, 8, 20]
   [3, 8, 10]
   [3, 8, 20]
```

### 8. k-Permutations
You can generate k-Permutations with and without repetitions using the combination and permutation
generators together. For example, 2-Permutations without repetitions of the lists (1, 2, 3):

```java
        Generator.combination(1, 2, 3)
            .simple(2)
            .stream()
            .forEach(combination -> Generator.permutation(combination)
                .simple()
                .forEach(System.out::println));
```
prints the following 6 2-permutations:
```
   [1, 2]
   [2, 1]
   [1, 3]
   [3, 1]
   [2, 3]
   [3, 2]
```        

Similarly, you can get 2-Permutations with repetitions of the lists (1, 2, 3):  
```java 
        Generator.combination(1, 2, 3)
            .multi(2)
            .stream()
            .forEach(combination -> Generator.permutation(combination)
                .simple()
                .forEach(System.out::println));
```
prints the following 9 2-permutations:
```
   [1, 1]
   [1, 2]
   [2, 1]
   [1, 3]
   [3, 1]
   [2, 2]
   [2, 3]
   [3, 2]
   [3, 3]
```        
