[![Build Status](https://travis-ci.org/dpaukov/combinatoricslib3-example.svg?branch=master)](https://travis-ci.org/dpaukov/combinatoricslib3-example)

# combinatoricslib3-example
Code examples of how to use the [combinatoricslib3 for Java 8](https://github.com/dpaukov/combinatoricslib3)

### Add combinatoricslib3 to your project
```xml
<dependency>
    <groupId>com.github.dpaukov</groupId>
    <artifactId>combinatoricslib3</artifactId>
    <version>3.0.0</version>
</dependency>
```

### Start using the Generator class
For example, you can generate all combinations of `("red", "black", "white", "green", "blue")`

```java
import org.paukov.combinatorics3.Generator;
import java.util.List;
import java.util.stream.Collectors;

public class Example {
    public static void main(String[] args) {
        List<List<String>> combinations = Generator.combination("red", "black", "white", "green", "blue")
                .simple(3)
                .stream()
                .collect(Collectors.<List<String>>toList());

        combinations.stream().forEach(System.out::println);
    }
}
```

## How to build and execute this example
Clone the repository and run the following command:
```
mvn package exec:java -Dexec.mainClass="org.paukov.combinatoricslib3.example.Main"
```
Or you can run the example from commandAdd line
```
java -jar target/combinatoricslib3-example-1.0.0-SNAPSHOT-jar-with-dependencies.jar
```
