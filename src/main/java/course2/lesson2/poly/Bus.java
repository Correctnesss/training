package course2.lesson2.poly;

public class Bus extends Transport{
    String marshroute;

    public String getMarshroute() {
        return marshroute;
    }

    public void setMarshroute(String marshroute) {
        this.marshroute = marshroute;
    }

    @Override
    void load() {
        System.out.println("Люди ломятся в автобус");
    }
}
