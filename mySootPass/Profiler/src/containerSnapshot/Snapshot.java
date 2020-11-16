package containerSnapshot;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Snapshot {
    public static List<String> containerTypes;
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
            JSONArray dataList = new JSONArray();
            dataList.add(data);

            System.out.println(System.getProperty("user.dir"));
            try (FileWriter file = new FileWriter(System.getProperty("user.dir") + File.separator + "Profiler/output/" + containerName + "Snapshot.json")) {
                file.write(dataList.toJSONString());
                file.flush();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public Snapshot() {
        containerTypes = new ArrayList<>();
        containerTypes.add("Vector");
        containerTypes.add("Stack");
        containerTypes.add("HashMap");
        //TODO

        containerSnapshotMap = new HashMap<>();
        for (String str : containerTypes) {
            containerSnapshotMap.put(str, new singleSnapshot());
        }
        hitCount = 0;
    }

    public static void hitContainerMethod(String containerName, String methodName) {
        containerSnapshotMap.get(containerName).hitMethod(methodName);
        hitCount++;
        System.out.println("Hit ID: " + hitCount);
    }

    public static void printToFile() {
        JSONArray dataList = new JSONArray();
        for (String str : containerTypes) {
            JSONObject data = new JSONObject();
            JSONObject snapshotdata = new JSONObject();
            singleSnapshot snapshot = containerSnapshotMap.get(str);
            for (HashMap.Entry<String, Integer> methodCountPair: snapshot.methodCountMap.entrySet()) {
                snapshotdata.put(methodCountPair.getKey(), methodCountPair.getValue());
            }
            data.put(str, snapshotdata);
            dataList.add(data);
        }

        System.out.println(System.getProperty("user.dir"));
        try (FileWriter file = new FileWriter(System.getProperty("user.dir") + File.separator + "Profiler/output/FullSnapshot.json")) {
            file.write(dataList.toJSONString());
            file.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

