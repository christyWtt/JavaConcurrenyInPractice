package chapter5;

import java.util.Map;

public class MapOperator implements Runnable{
    public static final int COUNT = 1000000;

    private final Map<Integer, Integer> map;

    public MapOperator(Map<Integer, Integer> map) {
        this.map = map;
    }

    public void run() {
        for (int i=0; i<COUNT; i++) {
           map.put(i, i);
           map.get(i);
        }
    }
}
