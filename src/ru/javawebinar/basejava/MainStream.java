package ru.javawebinar.basejava;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MainStream {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("string1");
        list.add("string2");
        list.add("string3");
        list.add("string4");
        list.add("string5");

        /*OLD
        for (int i = 0; i < list.size(); i++) {
            list.set(i, String.valueOf(list.get(i).length()));
        }*/

        List<Integer> integerList = list.stream().map(element -> element.length()).collect(Collectors.toList());
        System.out.println(integerList);
        System.out.println(list.stream().map((element -> element.length())).getClass());

        int[] array = {4, 6, 7, 3, 2, 8};
        System.out.println(Arrays.toString(array));
        array = Arrays.stream(array).map(element -> {
            if (element % 2 == 0) {
                element /= 2;
            }
            return element;
        }).toArray();
        System.out.println(Arrays.toString(array));

        int result = Arrays.stream(array).reduce((accamulator, el) -> accamulator * el).getAsInt();
        System.out.println(result);


        //Method sorted
        array = Arrays.stream(array).sorted().toArray();
        System.out.println(Arrays.toString(array));

        //Chaining
        int[] array2 = {3, 6, 4, 3, 9, 23, 46, 21, 79, 23, 72, 4, 3, 7, 27};
        int asInt = Arrays.stream(array2).filter(e -> e % 2 == 1)
                .map(e -> {
                    if (e % 3 == 0) {
                        e /= 3;
                    }
                    return e;
                }).reduce(Integer::sum).getAsInt();
        System.out.println("Chaining: " + asInt);

        //concat
        Stream<Integer> stream = Stream.of(1, 6, 1, 6, 2, 8, 9, 3, 4);
        //distinct
    }
}
