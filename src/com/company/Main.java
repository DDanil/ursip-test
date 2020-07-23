package com.company;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) {
        final List<Integer> numbers = Stream.of(3,1,2,3,4,9).collect(Collectors.toList());
        System.out.println(generateNum(numbers));
    }

    private static String generateNum(final List<Integer> numbers) {
        // Получим отсортированный не
        final List<Integer> sortedUnrepeatedNumbers = numbers
                .stream()
                .distinct()
                .sorted(Integer::compare)
                .collect(Collectors.toList());
        // Найдем первое несоответствие числа индексу в сортированном списке без повторений
        final Optional<Integer> gap = sortedUnrepeatedNumbers
                .stream()
                .map(number -> (sortedUnrepeatedNumbers.indexOf(number)))
                .filter(index -> !index.equals(sortedUnrepeatedNumbers.get(index) - 1))
                .findFirst();
        // Вернем найденное несоотстветствие, либо размер списка + 1, если все соответствует - список без пропусков
        return String.format("%03d", gap.orElse(sortedUnrepeatedNumbers.size()) + 1);
    }
}
