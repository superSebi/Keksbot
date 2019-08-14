package core;

import net.dv8tion.jda.core.entities.User;

import java.util.HashMap;

public class GBHandler {
    private static HashMap<String, User> nameMap= new HashMap<>();

    public static void setSetUserName (String serverId, User user){
        nameMap.put(serverId, user);
    }
    public static User getUserName (String serverId){
        return nameMap.get(serverId);
    }

    public static void finishedUser(String serverId) {
        nameMap.remove(getUserName(serverId));
    }
}
