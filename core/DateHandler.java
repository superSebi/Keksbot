package core;

import java.util.HashMap;

public class DateHandler {
    private static HashMap<String, Integer> limitedMap= new HashMap<>();
    private static HashMap<String, Integer> monthMap= new HashMap<>();
    private static HashMap<String, Integer> yearMap= new HashMap<>();

    public static void setUserDate1 (String serverId, int date1){
        limitedMap.put(serverId, date1);
    }
    public static Integer getUserDate1 (String serverId){
        return limitedMap.getOrDefault(serverId, 0);
    }
    public static void setUserDate2 (String serverId, int date2){
        monthMap.put(serverId, date2);
    }
    public static Integer getUserDate2 (String serverId){
        return monthMap.getOrDefault(serverId, 0);
    }
    public static void setUserDate3 (String serverId, int date3){
        yearMap.put(serverId, date3);
    }
    public static Integer getUserDate3 (String serverId){
        return yearMap.getOrDefault(serverId , 0);
    }
    public static void finishedDelete1(String serverId) {
        yearMap.remove(getUserDate1(serverId));
    }
    public static void finishedDelete2(String serverId) {
        yearMap.remove(getUserDate2(serverId));
    }
    public static void finishedDelete3(String serverId) {
        yearMap.remove(getUserDate3(serverId));
    }

}

