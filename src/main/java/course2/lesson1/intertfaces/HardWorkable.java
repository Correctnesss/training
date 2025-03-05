package course2.lesson1.intertfaces;

public interface HardWorkable extends Workable {

    void workHard();

    default void workVeryHard() {
        workHard();
    }

}
