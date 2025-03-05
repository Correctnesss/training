package course2.lesson8;

import java.util.stream.IntStream;

public class intStreamApp {

    public static void main(String[] args) {
        IntStream.iterate(0, i -> i +3)
                .limit(10);

    }
}
