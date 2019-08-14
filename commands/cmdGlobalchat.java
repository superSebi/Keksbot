package commands;

import core.restHandler;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import util.Emojis2;

public class cmdGlobalchat implements Command {
    @Override
    public boolean called(String[] args, MessageReceivedEvent event) {
        return false;
    }

    @Override
    public void action(String[] args, MessageReceivedEvent event) {
        restHandler.setCommands(event.getGuild(), restHandler.getCommands(event.getGuild()));
        event.getTextChannel().sendMessage(new EmbedBuilder().setTitle("<:globalchat:580795455013781505>" + Emojis2.WHITE_SMALL_SQUARE + " Keksiger-Globalchat").setDescription( "Was ist das? \n Rede mit allen Keksen, die den Bot nutzen. \n Wie funktioniert das? \n Erstelle einfach einen Channel mit Namen `keksiger-globalchat` und schon ist er aktiviert.").build()).queue();
    }

    @Override
    public void executed(boolean sucess, MessageReceivedEvent event) {

    }

    @Override
    public String help() {
        return null;
    }
}
