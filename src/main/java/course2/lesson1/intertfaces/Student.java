package course2.lesson1.intertfaces;

public class Student implements Studyable, HardWorkable {
    @Override
    public void study() {
        System.out.println("Стундент учится только перед сессией");
    }

    @Override
    public void work() {
        System.out.println("Студент еще и работает");
    }

    @Override
    public void workHard() {
        System.out.println("Студент еще и работает как Папа Карло");
    }


}
