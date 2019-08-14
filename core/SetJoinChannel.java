package core;

import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.TextChannel;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Optional;
import java.util.Properties;

public class SetJoinChannel {
        private static final String CHANNEL_PREFIX = "joinchannel.";
        private static HashMap<String, String> channelMap= new HashMap<>();

        public static Optional<TextChannel> getJoinChannel (Guild g) {
            return g.getTextChannels().stream().filter(ch->ch.getId().equals(channelMap.get(g.getId()))).findFirst();
        }

        public static void setJoinChannel (String serverId, String channelId){
            channelMap.put(serverId, channelId);
        }
        public static void loadJoinChannels() {
            try {
                Properties props = new Properties();
                props.load(SetJoinChannel.class.getClassLoader().getResourceAsStream("joinchannel.properties"));

                props.entrySet().stream().filter(e->e.getKey().toString().startsWith(CHANNEL_PREFIX)).forEach(e-> channelMap.put(e.getKey().toString().replaceFirst(CHANNEL_PREFIX,""),e.getValue().toString()));

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public static void saveJoinChannels() {
            try {
                Properties props = new Properties();
                File propFile = new File(SetJoinChannel.class.getClassLoader().getResource("joinchannel.properties").getPath());
                channelMap.entrySet().stream().forEach(e-> props.put(CHANNEL_PREFIX + e.getKey().toString(),e.getValue().toString()));props.store(new FileOutputStream(propFile),"JoinChannels from "+ new Date());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

}

