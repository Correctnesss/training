package course2.lesson2.exception;

public class MyCustomException extends RuntimeException{

    public MyCustomException(int a) {
        super("Не умею делить на " + a);
    }
}
