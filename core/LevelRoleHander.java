package core;

import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.Role;

import java.util.HashMap;
import java.util.Optional;

public class LevelRoleHander {
    private static HashMap<String, String> rolesMap= new HashMap<>();
    private static HashMap<String, Integer> xpMap= new HashMap<>();

    public static Optional<Role> getRole (Guild g) {
        return g.getRoles().stream().filter(role -> role.getId().equals(rolesMap.get(role))).findFirst();
    }

    public static void setRole (String serverId, String roleId){
        rolesMap.put(serverId, roleId);
    }
    public static void setXP (String serverId, int xp){
        xpMap.put(serverId, xp);
    }
    public static Integer getXP (String serverId){
        xpMap.getOrDefault(serverId, 0);
        return null;
    }
}
