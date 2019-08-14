package commands;

import core.restHandler;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import util.Emojis;
import util.Emojis2;

public class  cmdAutoWarn implements Command {
    @Override
    public boolean called(String[] args, MessageReceivedEvent event) {
        return false;
    }

    @Override
    public void action(String[] args, MessageReceivedEvent event) {
        restHandler.setCommands(event.getGuild(), restHandler.getCommands(event.getGuild()));

        event.getTextChannel().sendMessage(new EmbedBuilder().setTitle(Emojis.WRENCH + "" + Emojis2.WHITE_SMALL_SQUARE + " Auto-Warn System").setDescription( "Was ist das? \n Wenn jemand Wörter wie `wixxer` schreibt, wird er wegen `Bad Words` verwarnt. \n Wie funktioniert das? \n Erstelle einfach einen Channel mit Namen `log` und schon ist es aktiviert.").build()).queue();
    }

    @Override
    public void executed(boolean sucess, MessageReceivedEvent event) {

    }

    @Override
    public String help() {
        return null;
    }
}
