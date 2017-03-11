package polylines;

import java.util.Map;
import java.util.HashMap;

public class Task5Main {

    public static <K, V1, V2> void mapExclude(Map<K, V1> from, Map<K, V2> what) {
        for(K key : what.keySet()) {
            from.remove(key);
        }
    }

    public static void printMap(Map<?, ?> mapping) {
        System.out.println('{');
        for (Map.Entry entry : mapping.entrySet()) {
            System.out.print("    ");
            System.out.print(entry.getKey().toString());
            System.out.print(": ");
            System.out.println(entry.getValue().toString());
        }
        System.out.println('}');
    }


    public static void main(String argv[]) {
        Map<Integer, Integer> removeFrom = new HashMap<>();
        for (int i = 0; i < 10; i++) {
            removeFrom.put(i, i);
        }

        Map<Integer, Integer> whatToRemove = new HashMap<>();
        for (int i = 0; i < 20; i+=2) {
            whatToRemove.put(i, i);
        }

        System.out.println("Before removing:");
        printMap(removeFrom);
        System.out.println("What ill be removed:");
        printMap(whatToRemove);

        mapExclude(removeFrom, whatToRemove);

        System.out.println("After removing:");
        printMap(removeFrom);
    }

}
