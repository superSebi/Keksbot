package core;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;

public class ProfileHandler {

    public static final String DEFAULT_DESC = "Unbekannt";
    public static final String DEFAULT_CODE = "Unbekannt";
    private static final String USER_DESC = "desc.";
    private static final String USER_CODE = "code.";
    private static HashMap<String, String> descMap= new HashMap<>();
    private static HashMap<String, String> codeMap = new HashMap<>();

    public static String getUserDesc(String user, String serverID) {
        return descMap.getOrDefault(user, DEFAULT_DESC);
    }

    public static void setUserDesc (String userId, String desc){
        descMap.put(userId, desc);
    }
    static void loadProfile() {
        try {
            Properties props = new Properties();
            props.load(ProfileHandler.class.getClassLoader().getResourceAsStream("profile.properties"));
            props.entrySet().stream().filter(e -> e.getKey().toString().startsWith(USER_DESC)).forEach(e -> descMap.put(e.getKey().toString().replaceFirst(USER_DESC, ""), e.getValue().toString()));
            props.entrySet().stream().filter(e -> e.getKey().toString().startsWith(USER_CODE)).forEach(e -> codeMap.put(e.getKey().toString().replaceFirst(USER_CODE, ""), e.getValue().toString()));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void saveProfile() {
        try {
            Properties props =new Properties();
            File propFile = new File(ProfileHandler.class.getClassLoader().getResource("profile.properties").getPath());
            descMap.entrySet().stream().forEach(e -> props.put(USER_DESC + e.getKey().toString(), e.getValue().toString()));
            codeMap.entrySet().stream().forEach(e -> props.put(USER_CODE + e.getKey().toString(), e.getValue().toString()));
            props.store(new FileOutputStream(propFile), "Profiles from " + new Date());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void setUserCode(String userId, String code) {

        codeMap.put(userId,code);
    }

    public static String getUserCode(String id) {
        return codeMap.getOrDefault(id, DEFAULT_CODE);
    }
}

