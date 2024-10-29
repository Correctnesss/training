package course2.lesson1;

public class App {
    public static void main(String[] args) {
        User user = new User();
        Guard guard = new Guard();
        boolean canPass = guard.canPass(user);
    }
}
