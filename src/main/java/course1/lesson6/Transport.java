package course1.lesson6;

public abstract class Transport extends Invention{

    protected String color;

    void printName() {
        System.out.println("•Transport•");
    }

    public abstract void wroomWroom();
    @Override
    public void printInventionDate(){
        System.out.println("1980");
    }

//    public abstract int getCapacity();

}
