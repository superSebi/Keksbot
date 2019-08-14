package commands;

import core.restHandler;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import util.Emojis;
import util.Emojis2;

import java.awt.*;

public class cmdSetLog implements  Command{
    @Override
    public boolean called(String[] args, MessageReceivedEvent event) {
        return false;
    }

    @Override
    public void action(String[] args, MessageReceivedEvent event) {
        restHandler.setCommands(event.getGuild(), restHandler.getCommands(event.getGuild()));
        event.getTextChannel().sendMessage(new EmbedBuilder().setColor(Color.GRAY)
        .setTitle(Emojis.WRENCH + "" + Emojis2.WHITE_SMALL_SQUARE + " Log Settings")
        .setDescription("Stelle ein, was der Bot in den Log senden soll, und was nicht. \n Weitere hilfe mit ``/setlog help`` \n \n \n 1. Bans: **on** \n 2. Kicks: **on** \n 3. Message Edeting: **on** \n 4. Server-Join: **on** \n 5. Server-Leave: **on** \n 6. Nickname change: **on** \n 7. Role adding: **on** \n 8. Unbans: **on** \n 9. Channel Deleting: **on**")
        .build()).queue();

        switch (args[0].toLowerCase()) {
            case "help":
                try {

                    event.getTextChannel().sendMessage(new EmbedBuilder().setColor(Color.GRAY)
                            .setTitle(Emojis.WRENCH + "" + Emojis2.WHITE_SMALL_SQUARE + " Log Settings")
                            .setDescription("Stelle ein, was in den Channel " + event.getGuild().getTextChannelsByName("log", true).get(0).getAsMention() + " gesendet werden soll. \n Schreibe dazu einfach `/setlog <Zahl>` um es anzuschalten bzw auszuschalten.")

                            .build()).queue();
                } catch (Exception e) {
                    event.getTextChannel().sendMessage("**Du bist witzig** \n Wie willst du den Log einstellen, wenn du ihn nicht mal auf deinem Server hast?").queue();
                }
            break;

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
