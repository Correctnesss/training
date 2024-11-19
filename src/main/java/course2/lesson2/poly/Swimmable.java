package course2.lesson2.poly;

public interface Swimmable {
    default void swim(){
        System.out.println("Плывет как-то по умолчанию");
    }
}
