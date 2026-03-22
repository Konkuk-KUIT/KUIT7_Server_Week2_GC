package src;

import java.util.LinkedHashMap;
import java.util.Map;

public class Log {
    private final Map<User, Product> logList = new LinkedHashMap<>();

    public void addLog(User user, Product product) {
        if(logList.size() >= 10) removeLog();
        logList.put(user, product);
    }

    public void removeLog() {
        User firstKey = logList.keySet().iterator().next();
        logList.remove(firstKey);
    }

    public String printLog() {
        StringBuilder print = new StringBuilder();
        for (Map.Entry<User, Product> entry : logList.entrySet()) {
             print.append("Key - ").append(entry.getKey().toString())
                     .append(" Value - ").append(entry.getValue().toString()).append("\n");
        }
        return print.toString();
    }
}
