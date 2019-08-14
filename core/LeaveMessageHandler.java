package core;

import net.dv8tion.jda.core.entities.Role;
import net.dv8tion.jda.core.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.core.events.guild.member.GuildMemberLeaveEvent;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;

public class LeaveMessageHandler {

    public static final String DEFAULT_LEAVE = "%USER% hat den Server verlassen %NEWLINE% Jetzt sind wir nur noch zu %NUMBER% :(";
    private static final String LEAVE = "leave.";
    private static HashMap<String, String> leaveMap= new HashMap<>();


    public static String [] getLeaveMessage(String serverID, GuildMemberLeaveEvent event) {
        String messageTemplate = leaveMap.getOrDefault(serverID, DEFAULT_LEAVE);
        messageTemplate = messageTemplate.replace("%USER%", event.getMember().getUser().getName());
        messageTemplate = messageTemplate.replace("%NUMBER%", Integer.toString(event.getGuild().getMembers().size()));
        List<String> list = new ArrayList<>();
        for (Role role : event.getMember().getRoles()) {
            String name = role.getName();
            list.add(name);
        }
        return messageTemplate.replace("%ROLES%", String.join(",", list)).split("%NEWLINE%");
    }

    public static void setLeaveMessage (String serverId, String desc){

        leaveMap.put(serverId, desc);
    }
    static void loadLeaveMessages() {
        try {
            Properties props = new Properties();
            props.load(LeaveMessageHandler.class.getClassLoader().getResourceAsStream("leavemessage.properties"));
            props.entrySet().stream().filter(e -> e.getKey().toString().startsWith(LEAVE)).forEach(e -> leaveMap.put(e.getKey().toString().replaceFirst(LEAVE, ""), e.getValue().toString()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void saveLeaveMessages() {
        try {
            Properties props =new Properties();
            File propFile = new File(LeaveMessageHandler.class.getClassLoader().getResource("leavemessage.properties").getPath());
            leaveMap.entrySet().stream().forEach(e -> props.put(LEAVE + e.getKey().toString(), e.getValue().toString()));
            props.store(new FileOutputStream(propFile), "JoinMessages from " + new Date());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
