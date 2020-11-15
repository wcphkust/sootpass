package containerSnapshot;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

public class Snapshot {
    public static HashMap<String, Integer> methodCountMap;

    public static void hitMethod(String methodName) {
        if (methodCountMap.containsKey(methodName)) {
            Integer count = methodCountMap.get(methodName);
            Integer newCount = new Integer(count.intValue() + 1);
            methodCountMap.put(methodName, newCount);
        } else {
            methodCountMap.put(methodName, new Integer(1));
        }
    }

    public static void printToFile() {
        JSONObject data = new JSONObject();
        for (HashMap.Entry<String, Integer> methodCountPair: methodCountMap.entrySet()) {
            data.put(methodCountPair.getKey(), methodCountPair.getValue());
        }
        JSONArray dataList = new JSONArray();
        dataList.add(data);

        System.out.println(System.getProperty("user.dir"));
        try (FileWriter file = new FileWriter(System.getProperty("user.dir") + File.separator + "Profiler/output/Snapshot.json")) {
            file.write(dataList.toJSONString());
            file.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

