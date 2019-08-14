package commands;

import core.restHandler;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import util.Emojis2;

import java.awt.*;

public class cmdEvent implements Command {
    @Override
    public boolean called(String[] args, MessageReceivedEvent event) {
        return false;
    }

    @Override
    public void action(String[] args, MessageReceivedEvent event) {
        restHandler.setCommands(event.getGuild(), restHandler.getCommands(event.getGuild()));
        event.getTextChannel().sendMessage(
                new EmbedBuilder().setColor(Color.YELLOW)
                        .setTitle("<:event:522761329853988864>" + Emojis2.WHITE_SMALL_SQUARE + "Derzeit läuft **2x-XP**")
                        .setDescription("Derzeit läuft das **2x-XP** Event! \n Erhalte **zweifach** so viel XP, wie normal!w")
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
