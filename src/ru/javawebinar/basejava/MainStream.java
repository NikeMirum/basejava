package ru.javawebinar.basejava;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class MainStream {

    public static void main(String[] args) {
        System.out.println(minValue(new int[]{1, 2, 3, 3, 2, 3}));
        System.out.println(oddOrEven(Arrays.asList(1, 2, 3, 4, 5, 6)));
        System.out.println(oddOrEven(Arrays.asList(1, 2, 3, 4, 5, 7)));
    }

    public static int minValue(int[] values) {
        return Arrays.stream(values).
                distinct().
                sorted()
                .reduce(0, (a, b) -> 10 * a + b);
    }

    public static List<Integer> oddOrEven(List<Integer> integers) {
        final Map<Boolean, List<Integer>> oddOrEvenMap = integers.stream()
                .collect(Collectors.partitioningBy(i -> i % 2 == 0));
        return oddOrEvenMap.get(oddOrEvenMap.get(false).size() % 2 != 0);
    }
}