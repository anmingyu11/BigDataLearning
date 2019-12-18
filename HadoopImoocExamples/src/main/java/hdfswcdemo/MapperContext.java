package hdfswcdemo;

import java.util.HashMap;
import java.util.Map;

public class MapperContext {
    private Map<String, Integer> map;

    public MapperContext() {
        map = new HashMap<>();
    }

    public Map<String,Integer> getMap(){
        return map;
    }

    public void put(String k) {
        Integer v = map.get(k.toLowerCase());
        if (v != null) {
            map.put(k, v + 1);
        } else {
            map.put(k, 1);
        }
    }

    public int get(String k) {
        return map.get(k);
    }
}
