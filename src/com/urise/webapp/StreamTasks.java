package com.urise.webapp;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class StreamTasks {
    public static void main(String[] args) {
    }

    private static int minValue(int[] values) {
        return Arrays.stream(values)
                .distinct()
                .sorted()
                .reduce((a, b) -> a * 10 + b)
                .orElse(0);
    }

    private static List<Integer> oddOrEven(List<Integer> integers) {
        return integers.stream()
                .filter(integers.stream().mapToInt(a -> a)
                        .sum() % 2 == 0 ? a -> a % 2 != 0 : a -> a % 2 == 0)
                .collect(Collectors.toList());
    }
}