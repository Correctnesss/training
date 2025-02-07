package course2.lesson8;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamsApp {

    public static void main(String[] args) {
        Random random = new Random();

        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            list.add(random.nextInt(100)); // 0-99
        }
        System.out.println(list);

        Stream<Integer> stream = list.stream();

        String s = list.stream()
                .limit(50)
//                .peek(i -> System.out.println(i))
                .filter(integer -> integer % 2 == 1)
                .sorted()
                .distinct()
                .map(integer -> String.valueOf(integer))
                .collect(Collectors.joining("<->"));
        System.out.println(s);

        Stream<String> stringStream = Stream.of("aaaa", "bbbbb", "cccc", "aaa", "aaaa", "bbb");

        Map<String, Integer> map = stringStream
                .collect(Collectors.toMap(Function.identity(), str -> 1, (v1, v2) -> v1 + 1));

        System.out.println(map);


//        boolean isAll4 = stringStream
////                .filter(str -> str.length()==4)
//                .map(str -> str.toUpperCase())
//                .noneMatch(str -> str.length() == 5);
//        System.out.println(isAll4);

    }
}
