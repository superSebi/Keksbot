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

public class ChannelHandler {

    private static final String CHANNEL_PREFIX = "channel.";
    private static HashMap<String, String> channelMap= new HashMap<>();

    public static Optional<TextChannel> getChannel (Guild g) {
        return g.getTextChannels().stream().filter(ch->ch.getId().equals(channelMap.get(g.getId()))).findFirst();
    }

    public static void setChannel (String serverId, String channelId){
        channelMap.put(serverId, channelId);
    }
    public static void loadChannels() {
        try {
            Properties props = new Properties();
            props.load(PrefixHandler.class.getClassLoader().getResourceAsStream("channel.properties"));

            props.entrySet().stream().filter(e->e.getKey().toString().startsWith(CHANNEL_PREFIX)).forEach(e-> channelMap.put(e.getKey().toString().replaceFirst(CHANNEL_PREFIX,""),e.getValue().toString()));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void saveChannels() {
        try {
            Properties props = new Properties();
            File propFile = new File(PrefixHandler.class.getClassLoader().getResource("channel.properties").getPath());
            channelMap.entrySet().stream().forEach(e-> props.put(CHANNEL_PREFIX + e.getKey().toString(),e.getValue().toString()));props.store(new FileOutputStream(propFile),"Prefixes from "+ new Date());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
