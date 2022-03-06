package ru.javawebinar.basejava.util;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class StreamUtil {

    public static int minValue(int[] values) {
        return Arrays.stream(values).distinct().sorted().reduce(0, (a, b) -> 10 * a + b);
    }

    public static List<Integer> oddOrEven(List<Integer> integers) {
        final Map<Boolean, List<Integer>> oddOrEvenMap = integers.stream()
                .collect(Collectors.partitioningBy(i -> i % 2 == 0));
        return oddOrEvenMap.get(oddOrEvenMap.get(false).size() % 2 != 0);
    }
}