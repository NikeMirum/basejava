package ru.javawebinar.basejava;

import java.util.Arrays;

import static ru.javawebinar.basejava.util.StreamUtil.minValue;
import static ru.javawebinar.basejava.util.StreamUtil.oddOrEven;

public class MainStream {

    public static void main(String[] args) {
        System.out.println(minValue(new int[]{1, 2, 3, 3, 2, 3}));
        System.out.println(oddOrEven(Arrays.asList(1, 2, 3, 4, 5, 6)));
        System.out.println(oddOrEven(Arrays.asList(1, 2, 3, 4, 5, 7)));
    }
}