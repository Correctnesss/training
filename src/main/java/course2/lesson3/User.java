package course2.lesson3;

import java.util.Objects;

public class User implements Comparable<User> {
    private int age;

    public User(int age) {
        this.age = age;
    }

    public int getAge() {
        return age;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return age == user.age;
    }

    @Override
    public int hashCode() {
        return Objects.hash(age);
    }

    @Override
    public String toString() {
        return "User{" +
                "age=" + age +
                "}";
    }

    @Override
    public int compareTo(User o) { // <0 если меньше 0 если равны >0 если больше
        return this.age - o.getAge();
    }
}
