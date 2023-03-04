package ru.javawebinar.basejava;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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
    }
}
