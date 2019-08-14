package commands;

import core.restHandler;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import util.Emojis2;

import java.awt.*;

public class cmdStatus implements Command {
    @Override
    public boolean called(String[] args, MessageReceivedEvent event) {
        return false;
    }

    @Override
    public void action(String[] args, MessageReceivedEvent event) {
        restHandler.setCommands(event.getGuild(), restHandler.getCommands(event.getGuild()));
        event.getTextChannel().sendMessage(
                new EmbedBuilder().setColor(Color.ORANGE)
                .setTitle(Emojis2.COOKIE + "" + Emojis2.WHITE_SMALL_SQUARE + " Aktueller Status und alle Vorfälle")
                .setDescription("[Klick mich](https://beste_bot.statuskit.com/)")
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
