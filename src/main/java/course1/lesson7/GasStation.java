package course1.lesson7;

public class GasStation {

    private int available;


    public GasStation(int freeVolume) {
        this.available = freeVolume;
    }

    public int getAvailable() {
        return available;
    }

    /**
     * Заправить указанное кол-во литров
     * @param amount
     */
    public void refill(int amount) {
        this.available -= amount;
    }



    @Override
    public String toString() {
        return "GasStation{" +
                "available=" + available +
                '}';
    }

    public void info(){
        System.out.println(this);
    }
}
