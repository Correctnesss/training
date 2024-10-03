package course1.lesson6;

import java.sql.SQLOutput;

public class RentApp {
    public static void main(String[] args) {
        Car car = new Car("Toyota",2012);
        System.out.println();


        Car van = new Van("Ford", 2010, 7);
        Car cabrio = new Cabrio("Peugeot",2015,true);

        Car[] cars = {car, cabrio, van};

        for(Car c : cars){
            System.out.println(c.getModel());
        }

        System.out.println("Only vans");
        for (Car c : cars){
            if (c instanceof Van){
                Van v = (Van) c;
                System.out.println("This is a van " + v.getSeats());
            }
        }


//        System.out.println(van);
    }
}
