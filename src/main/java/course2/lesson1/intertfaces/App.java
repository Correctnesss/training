package course2.lesson1.intertfaces;

import java.util.Arrays;

public class App {
    public static void main(String[] args) {

        Student student = new Student();
        Pupil pupil = new Pupil();


        Studyable[] studyables = new Studyable[]{student, pupil};
        for (Studyable studyable : studyables) {
            studyable.study();
        }


//        1. javac Class
//        2. java Class (looking for main) a1 a2 a3
    }
}
