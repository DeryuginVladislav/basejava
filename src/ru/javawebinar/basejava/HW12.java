package ru.javawebinar.basejava;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class HW12 {
    public static void main(String[] args) {
        System.out.println(minValue(new int[]{1, 2, 3, 3, 2, 3}));
        System.out.println(oddOrEven(Arrays.asList(1, 6, 2, 6, 8, 3, 25, 7, 9, 7)));
    }

    private static int minValue(int[] values) {
        return Arrays.stream(values).distinct()
                .sorted()
                .reduce((result, element) -> result * 10 + element)
                .getAsInt();
    }

    private static List<Integer> oddOrEven(List<Integer> integers) {
        return integers.stream().filter(e -> {
            if (integers.stream().reduce(Integer::sum).get() % 2 == 0) {
                return e % 2 == 1;
            }
            return e % 2 == 0;
        }).collect(Collectors.toList());
    }
}
