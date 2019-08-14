package commands;

import core.restHandler;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import util.Emojis2;

import java.awt.*;

public class cmdInvite implements Command {
    @Override
    public boolean called(String[] args, MessageReceivedEvent event) {
        return false;
    }

    @Override
    public void action(String[] args, MessageReceivedEvent event) {
        restHandler.setCommands(event.getGuild(), restHandler.getCommands(event.getGuild()));
        event.getTextChannel().sendMessage(
                new EmbedBuilder().setColor(Color.ORANGE)
                    .setTitle(Emojis2.COOKIE + "" + Emojis2.WHITE_SMALL_SQUARE + "Lade mich zur Party, von deinem Freund, ein")
                    .setDescription("[Klick mich](https://discordapp.com/oauth2/authorize?client_id=493066387183632387&scope=bot&permissions=8)")
                .build()
        ).queue();
    }

    @Override
    public void executed(boolean sucess, MessageReceivedEvent event) {

    }

    @Override
    public String help() {
        return null;
    }
}
