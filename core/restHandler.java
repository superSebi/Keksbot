package core;

import net.dv8tion.jda.core.entities.Guild;

import java.util.HashMap;

public class restHandler {
    private static HashMap<String, Integer> limitedMap= new HashMap<>();
    private static HashMap<String, String> idMap= new HashMap<>();
    private static HashMap<String, Integer> xpEnableMap = new HashMap<>();
    private static HashMap<String, String> whiteList = new HashMap<>();
    private static HashMap<String, Integer> whiteListEnableMap = new HashMap<>();
    private static HashMap<Guild, Integer> commandMap = new HashMap<>();
    private static HashMap<Guild, Integer> msgMap = new HashMap<>();

    public static void setSetServerLimit (String serverId, int xp){
        limitedMap.put(serverId, xp);
    }
    public static void setCommands (Guild g, Integer commands){
        commandMap.put(g, commands + 1);

    }
    public static int getCommands (Guild g){
        return commandMap.getOrDefault(g, 0);

    }
    public static void setMsgs (Guild g, Integer commands){
        msgMap.put(g, commands + 1);

    }
    public static int getMsgs (Guild g){
        return msgMap.getOrDefault(g, 0);

    }
    public static Integer getServerLimit (String serverId){
        return limitedMap.getOrDefault(serverId, 0);
    }
    public static void setSetIDBan (String serverId, String userid){
        idMap.put(serverId, userid);
    }
    public static String getIDBan (String serverId, String userid){
        return idMap.getOrDefault(serverId, idMap.getOrDefault(userid, null));
    }
    public static void removeIDBan (String serverId, String userid){
        idMap.remove(serverId, userid);
    }

    public static Integer XPenable (String serverId) {
        return xpEnableMap.getOrDefault(serverId, 0);
    }
    public static Integer setenable (String serverId, Integer enable) {
        return xpEnableMap.put(serverId, enable);
    }
    public static void setWhiteList (String serverId, String userId){
        whiteList.put(serverId, userId);
    }
    public static String getWhiteList (String serverId, String userId) {
        return whiteList.getOrDefault(serverId, whiteList.getOrDefault(userId, null));
    }
    public static Integer setEnableWhiteList (String serverId, Integer enable) {
        return whiteListEnableMap.put(serverId, enable);
    }
    public static Integer WhiteListEnable (String serverId) {
        return whiteListEnableMap.getOrDefault(serverId, 0);
    }



}
