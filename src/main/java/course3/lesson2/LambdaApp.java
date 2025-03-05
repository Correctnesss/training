package course3.lesson2;

public class LambdaApp {
    public static void main(String[] args) {
        int i = 11;

        new Thread(() ->System.out.println(i)).start();
    }
}
