package core;

import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.Role;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Optional;
import java.util.Properties;

public class JoinRoleHandler {
    private static final String JOIN_PREFIX = "role.";
    private static HashMap<String, String> rolesMap= new HashMap<>();

    public static Optional<Role> getRole (Guild g) {
        return g.getRoles().stream().filter(role -> role.getId().equals(rolesMap.get(role))).findFirst();
    }

    public static void setRole (String serverId, String roleId){
        rolesMap.put(serverId, roleId);
    }
    public static void loadRoles() {
        try {
            Properties props = new Properties();
            props.load(PrefixHandler.class.getClassLoader().getResourceAsStream("roles.properties"));

            props.entrySet().stream().filter(e->e.getKey().toString().startsWith(JOIN_PREFIX)).forEach(e-> rolesMap.put(e.getKey().toString().replaceFirst(JOIN_PREFIX,""),e.getValue().toString()));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void saveRoles() {
        try {
            Properties props = new Properties();
            File propFile = new File(PrefixHandler.class.getClassLoader().getResource("roles.properties").getPath());
            rolesMap.entrySet().stream().forEach(e-> props.put(JOIN_PREFIX + e.getKey().toString(),e.getValue().toString()));props.store(new FileOutputStream(propFile),"Roles from "+ new Date());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}


