package containerSnapshot;
import org.json.JSONObject;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Snapshot {
    public static HashSet<String> containerTypes;
    public static HashMap<String, singleSnapshot> containerSnapshotMap;
    public static int hitCount;


    public class singleSnapshot {
        public HashMap<String, Integer> methodCountMap;

        public singleSnapshot() {
            methodCountMap = new HashMap<>();
        }

        public void hitMethod(String methodName) {
            if (methodCountMap.containsKey(methodName)) {
                Integer count = methodCountMap.get(methodName);
                Integer newCount = new Integer(count.intValue() + 1);
                methodCountMap.put(methodName, newCount);
            } else {
                methodCountMap.put(methodName, new Integer(1));
            }
        }

        public void printToFile(String containerName) {
            JSONObject data = new JSONObject();
            for (HashMap.Entry<String, Integer> methodCountPair: methodCountMap.entrySet()) {
                data.put(methodCountPair.getKey(), methodCountPair.getValue());
            }

            System.out.println(System.getProperty("user.dir"));
            try (FileWriter file = new FileWriter(System.getProperty("user.dir") + File.separator + "Profiler/output/" + containerName + "Snapshot.json")) {
                file.write(data.toString(4));
                file.flush();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public Snapshot() {
        containerTypes = new HashSet<>();
        containerTypes.add("LinkedList");
        containerTypes.add("ArrayList");
        containerTypes.add("Vector");
        containerTypes.add("Stack");
        containerTypes.add("List");
        containerTypes.add("ArrayDeque");
        containerTypes.add("Queue");

        containerTypes.add("PriorityQueue");
        containerTypes.add("EnumSet");
        containerTypes.add("HashSet");
        containerTypes.add("LinkedHashSet");
        containerTypes.add("TreeSet");

        containerTypes.add("EnumMap");
        containerTypes.add("TreeMap");
        containerTypes.add("HashMap");
        containerTypes.add("WeakHashMap");
        containerTypes.add("LinkedHashMap");
        containerTypes.add("IdentityHashMap");
        containerTypes.add("HashTable");

        containerSnapshotMap = new HashMap<>();
        for (String containerName : containerTypes) {
            containerSnapshotMap.put(containerName, new singleSnapshot());
        }
        hitCount = 0;
    }

    public static void hitContainerMethod(String containerName, String methodName) {
        containerSnapshotMap.get(containerName).hitMethod(methodName);
        hitCount++;
        System.out.println("Hit ID: " + hitCount);
    }

    public static void printToFile() {
        JSONObject dataList = new JSONObject();
        int containerid = 0;
        for (String str : containerTypes) {
            containerid++;
            JSONObject data = new JSONObject();
            JSONObject snapshotdata = new JSONObject();
            singleSnapshot snapshot = containerSnapshotMap.get(str);
            for (HashMap.Entry<String, Integer> methodCountPair: snapshot.methodCountMap.entrySet()) {
                snapshotdata.put(methodCountPair.getKey(), methodCountPair.getValue());
            }
            data.put(str, snapshotdata);
            dataList.put((new Integer(containerid)).toString(), data);
        }

        System.out.println(System.getProperty("user.dir"));
        try (FileWriter file = new FileWriter(System.getProperty("user.dir") + File.separator + "Profiler/output/FullSnapshot.json")) {
            file.write(dataList.toString(4));
            file.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

