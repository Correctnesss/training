package course2.lesson4;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class IterableApp {
    public static void main(String[] args) {
        List<String> list = List.of("one", "two", "three");
        Iterator<String> iterator = list.iterator();

        list = new ArrayList<>(list);
        while (iterator.hasNext()) {
            String str = iterator.next();
            System.out.println(str);
        }

        //remove if < 4
        iterator = list.iterator();
        while (iterator.hasNext()) {
            String str = iterator.next();
            if (str.length() < 4) {
                iterator.remove();
            }
        }

        list.removeIf(str -> str.length() < 4);

        list.stream().filter(x -> x.length() > 4).forEach(x -> System.out.println(x));

        System.out.println(list);

    }
}
