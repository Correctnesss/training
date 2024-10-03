package course1.lesson6;


//Is a
public class Cabrio extends Car{

    boolean hasHardTop;

    public Cabrio(String model, int year, boolean hasHardTop) {
        super(model,year);
        System.out.println("this is a Cabrio constructor");
        this.hasHardTop = hasHardTop;
    }

    public boolean isHasHardTop() {
        return hasHardTop;
    }

    public void setHasHardTop(boolean hasHardTop) {
        this.hasHardTop = hasHardTop;
    }
}
