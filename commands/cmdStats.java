package commands;

import core.restHandler;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import util.Emojis2;

import java.awt.*;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class cmdStats implements Command {
    @Override
    public boolean called(String[] args, MessageReceivedEvent event) {
        return false;
    }

    @Override
    public void action(String[] args, MessageReceivedEvent event) throws ParseException, IOException {
        restHandler.setCommands(event.getGuild(), restHandler.getCommands(event.getGuild()));
        if (event.getAuthor().getId().equals("349879801064194060")) {
            List<String> list = new ArrayList<>();
            for (Guild guild : event.getJDA().getGuilds()) {
                String name = guild.getName();
                list.add(name);

            }
            event.getTextChannel().sendMessage(
                    new EmbedBuilder().setColor(Color.ORANGE)
                            .setTitle(Emojis2.COOKIE + "" + Emojis2.WHITE_SMALL_SQUARE + " Stats")
                            .setDescription("Die aktuellen Stats von mir")
                            .addField("**Server-Anzahl:**", event.getJDA().getGuilds().size() + " Servern", false)
                            .addField("**User-Anzahl:**", event.getJDA().getUsers().size() + " Users", false)
                            .addField("**Alle Server auf denen ich bin:**", String.join(",", list), false)
                            .setFooter("<- Mein krasses Avatar :)", "https://images-ext-2.discordapp.net/external/C-n0QEByCLxwcSk8xuED96wKyIU4vsBDb9ch7t_LvT8/https/images-ext-2.discordapp.net/external/kCCNbCY9MZ1HDrC1K2XkWHxPfHlcHMjNflzy202Mq_g/https/images-ext-1.discordapp.net/external/FHVAXut353LQ9xRaxChWHL2CJYKvZWlqhUvdmS-lRPo/https/cdn.discordapp.com/avatars/349879801064194060/b0814cc2cd9a3e8a1e3d133c1aa726fe.png")
                            .build()).queue();
        } else {
            event.getTextChannel().sendMessage("❌ | Dieser Command ist aus Datenschutzgründen nur noch für `KeksOfs | Sebl#6013` freigeschalten.").queue();
        }
    }

    @Override
    public void executed(boolean sucess, MessageReceivedEvent event) {

    }

    @Override
    public String help() {
        return null;
    }
}
