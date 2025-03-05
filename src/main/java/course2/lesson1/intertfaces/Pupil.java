package course2.lesson1.intertfaces;

public class Pupil implements Studyable {
    @Override
    public void study() {
        System.out.println("Ученик учится");
    }
}
