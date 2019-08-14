package core;

import java.util.HashMap;

public class gChatHandler {
    public static HashMap<String, String> userId = new HashMap<>();

    public static void setBlackList (String userid) {
        userId.put(userid, userid);
    }
    public static String loadBlacklist (String userid) {
        return userId.get(userid);
    }
    public static void removeBlacklist (String userid) {
        userId.remove(userid);
    }
}
