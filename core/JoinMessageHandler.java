package core;

import net.dv8tion.jda.core.entities.Role;
import net.dv8tion.jda.core.events.guild.member.GuildMemberJoinEvent;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class JoinMessageHandler{

    public static final String DEFAULT_JOIN = "Willkommen, %USER%, auf unserem Server! %NEWLINE% Du bist der %NUMBER%te User! %NEWLINE% Er hat die Rollen %ROLES% bekommen";
    private static final String JOIN = "join.";
    private static HashMap<String, String> descMap= new HashMap<>();


    public static String [] getJoinMessage(String serverID, GuildMemberJoinEvent event) {
        String messageTemplate = descMap.getOrDefault(serverID, DEFAULT_JOIN);
        messageTemplate = messageTemplate.replace("%USER%", event.getMember().getUser().getName());
        messageTemplate = messageTemplate.replace("%NUMBER%", Integer.toString(event.getGuild().getMembers().size()));
        List<String> list = new ArrayList<>();
        for (Role role : event.getMember().getRoles()) {
            String name = role.getName();
            list.add(name);
        }
        return messageTemplate.replace("%ROLES%", String.join(",", list)).split("%NEWLINE%");
    }

    public static void setJoinMessage (String serverId, String desc){

        descMap.put(serverId, desc);
    }
    static void loadJoinMessages() {
        try {
            Properties props = new Properties();
            props.load(JoinMessageHandler.class.getClassLoader().getResourceAsStream("joinmessage.properties"));
            props.entrySet().stream().filter(e -> e.getKey().toString().startsWith(JOIN)).forEach(e -> descMap.put(e.getKey().toString().replaceFirst(JOIN, ""), e.getValue().toString()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void saveJoinMessages() {
        try {
            Properties props =new Properties();
            File propFile = new File(JoinMessageHandler.class.getClassLoader().getResource("joinmessage.properties").getPath());
            descMap.entrySet().stream().forEach(e -> props.put(JOIN + e.getKey().toString(), e.getValue().toString()));
            props.store(new FileOutputStream(propFile), "JoinMessages from " + new Date());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
