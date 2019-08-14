package core;

import net.dv8tion.jda.core.entities.User;
import util.XPComparator;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;

public class XPHandler {
    public static final String XP= "1";
    public static final String Level = "1";
    private static final String USER_XP = "user.";

    private static XPComparator xpComp= new XPComparator();
    private static Map<User, Integer> userLevelMap = new HashMap<>();
    private static Map<User, Integer> userXPMap = new HashMap<>();

    public static Integer getUsersXP (User user, String serverID) {
        return userXPMap.getOrDefault(user, 0);
    }
    public static List<Map.Entry<User, Integer>> getToplist () {
        return XPComparator.entriesSortedByValues(userXPMap).subList(0, Math.min(userXPMap.size()-1,3));
    }

    public static void setUsersXP (User user, Integer xp){
        userXPMap.put(user, xp + 2);
    }
    public static Integer getUserLevel (User user, String serverID) {
        return userLevelMap.getOrDefault(user, 0);
    }

    public static void setUserLevel (User user, int level){
        userLevelMap.put(user, level);
    }
    public static void loadXP() {
        try {
            Properties props = new Properties();
            props.load(PrefixHandler.class.getClassLoader().getResourceAsStream("xp.properties"));

           //props.entrySet().stream().filter(e->e.getKey().toString().startsWith(USER_XP)).forEach(e-> userXPMap.put(e.getKey().toString().replaceFirst(USER_XP,""),Integer.parseInt(e.getValue().toString())));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void saveXPs() {
        try {
            Properties props = new Properties();
            File propFile = new File(PrefixHandler.class.getClassLoader().getResource("xp.properties").getPath());
            userXPMap.entrySet().stream().forEach(e-> props.put(USER_XP + e.getKey().toString(),e.getValue().toString()));
            props.store(new FileOutputStream(propFile),"XPs from "+ new Date());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
