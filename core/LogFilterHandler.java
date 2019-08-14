package core;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;

public class LogFilterHandler {

    public static final String DEFAULT_LOGFILTER = "Unbekannt";
    private static final String LOGFILTER = "logfilter.";
    private static HashMap<String, Set<String>> logFilterMap = new HashMap<>();

    //public static String getLogFilter(String server) {
       // return logFilterMap.getOrDefault(server, DEFAULT_LOGFILTER);
   // }

    public static void setLogFilter(String serverId, String eventId) {

        if (logFilterMap.containsKey((serverId))) {
            logFilterMap.get(serverId).add(eventId);
        } else {
            Set<String> eventIds = new HashSet<>();
            eventIds.add(eventId);
            logFilterMap.put(serverId, eventIds);
        }
    }

    static void loadLogFilter() {
        try {
            Properties props = new Properties();
            props.load(LogFilterHandler.class.getClassLoader().getResourceAsStream("logfilter.properties"));
            props.entrySet().stream().filter(e -> e.getKey().toString().startsWith(LOGFILTER)).forEach(e -> {
                Set<String> eventIds = new HashSet<>();
                eventIds.addAll(Arrays.asList(e.getValue().toString().split(",")));
                logFilterMap.put(e.getKey().toString().replaceFirst(LOGFILTER, ""), eventIds);
            });

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void saveLogFilter() {
        try {
            Properties props = new Properties();
            File propFile = new File(LogFilterHandler.class.getClassLoader().getResource("logfilter.properties").getPath());
            logFilterMap.entrySet().stream().forEach(e -> props.put(LOGFILTER + e.getKey().toString(), String.join(",", e.getValue())));

            props.store(new FileOutputStream(propFile), "Filters from " + new Date());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}



