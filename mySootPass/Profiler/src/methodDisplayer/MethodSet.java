package methodDisplayer;

import java.io.File;
import java.lang.reflect.Method;
import java.util.HashSet;
import java.io.FileWriter;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class MethodSet {
    public static HashSet<String> MethodSet;

    public static void printToFile() {
        JSONObject data = new JSONObject();
        int i = 0;
        for (String methodName : MethodSet) {
            i++;
            data.put(i, methodName);
        }
        JSONArray dataList = new JSONArray();
        dataList.add(data);

        System.out.println(System.getProperty("user.dir"));
        try (FileWriter file = new FileWriter(System.getProperty("user.dir") + File.separator + "Profiler/output/MethodSet.json")) {
            file.write(dataList.toJSONString());
            file.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
