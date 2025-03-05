package course2.lesson3;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class MapApp {
    public static void main(String[] args) {

        //equals
        //hashCode

        Map<String, Integer> map = new HashMap<>();

        map.put("key", 100);
        map.put("key", 200);
        map.containsKey("key");
        map.remove("asda0");
        map.size();

        map.getOrDefault("key123",10);

        map.get("key"); //null

        map.isEmpty();

        map.values(); //Collection

        map.keySet(); //Set

        Set<Integer> set = new HashSet<>();
    }
}
