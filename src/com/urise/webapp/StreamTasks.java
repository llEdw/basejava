package com.urise.webapp;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class StreamTasks {
    public static void main(String[] args) {
        System.out.println(minValue(new int[]{4, 4, 4, 6, 6, 5, 1, 7, 9, 3}));
        System.out.println(oddOrEven(Arrays.asList(-10, 8, -3, 0, 14, -7, 1, 7, 9, 3)));
    }

    private static int minValue(int[] values) {
        return Arrays.stream(values)
                .distinct()
                .sorted()
                .reduce(0, (a, b) -> a * 10 + b);
    }

    private static List<Integer> oddOrEven(List<Integer> integers) {
        int sum = integers.stream().reduce(0, Integer::sum);

        return integers.stream()
                .filter(a -> Math.abs(a % 2) != sum % 2)
                .collect(Collectors.toList());
    }
}