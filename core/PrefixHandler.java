package core;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Properties;

public class PrefixHandler {

            public static final String PREFIX = "/";
            private static final String USER_PREFIX = "user.";
            private static final String SERVER_PREFIX = "server.";
            private static HashMap<String, String> userPrefixMap= new HashMap<>();
            private static HashMap<String, String> serverPrefixMap = new HashMap<>();

            public static String getUserPrefix (String user, String serverID) {
                return userPrefixMap.getOrDefault(user, serverPrefixMap.getOrDefault(serverID,PREFIX));
            }

            public static void setUserPrefix (String userId, String prefix){
                userPrefixMap.put(userId, prefix);
            }
            public static void loadPrefixes() {
                try {
                    Properties props = new Properties();
                    props.load(PrefixHandler.class.getClassLoader().getResourceAsStream("prefix.properties"));

                    props.entrySet().stream().filter(e->e.getKey().toString().startsWith(USER_PREFIX)).forEach(e-> userPrefixMap.put(e.getKey().toString().replaceFirst(USER_PREFIX,""),e.getValue().toString()));
                    props.entrySet().stream().filter(e->e.getKey().toString().startsWith(SERVER_PREFIX)).forEach(e-> serverPrefixMap.put(e.getKey().toString().replaceFirst(SERVER_PREFIX,""),e.getValue().toString()));

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            public static void savePrefixes() {
                try {
                    Properties props = new Properties();
                    File propFile = new File(PrefixHandler.class.getClassLoader().getResource("prefix.properties").getPath());
                    userPrefixMap.entrySet().stream().forEach(e-> props.put(USER_PREFIX + e.getKey().toString(),e.getValue().toString()));
                    serverPrefixMap.entrySet().stream().forEach(e-> props.put(SERVER_PREFIX + e.getKey().toString(),e.getValue().toString()));
                    props.store(new FileOutputStream(propFile),"Prefixes from "+ new Date());
                } catch (IOException e) {
                    e.printStackTrace();
        }
    }

    public static void setServerPrefix(String server, String prefix) {

        serverPrefixMap.put(server, prefix);
    }

    public static String getServerPrefix(String id) {
        return serverPrefixMap.getOrDefault(id, PREFIX);
    }
}
