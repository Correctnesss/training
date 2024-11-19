package course2.lesson2.poly;

public abstract class Transport {
    int capacity;

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    /**
     * Посадка в транспорт
     */
    abstract void load();
}
