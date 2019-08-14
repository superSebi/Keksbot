package commands;

import core.restHandler;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import util.Emojis2;

import java.awt.*;

public class cmdSupport implements Command {
    @Override
    public boolean called(String[] args, MessageReceivedEvent event) {
        return false;
    }

    @Override
    public void action(String[] args, MessageReceivedEvent event) {
        restHandler.setCommands(event.getGuild(), restHandler.getCommands(event.getGuild()));
        if (event.getJDA().getGuildById("504609411243704365").getId().equals(event.getGuild().getId())) {
            event.getTextChannel().sendMessage(
                    new EmbedBuilder().setColor(Color.ORANGE)
                            .setTitle(Emojis2.COOKIE + "" + Emojis2.WHITE_SMALL_SQUARE + "Support Anfrage erhalten!")
                            .setDescription("Da du auf unserem Support-Server bist und du `/support` eingeben hast, wurde ein Supporter informiert und wird sich um dich kümmern! \n Bitte etwas Geduld <a:discordload:525293213318250496> \n Schau doch wärend dem warten Pepe beim Tanzen zu <a:pepedance1:530802246846513163> <a:pepdance2:530802326185705472> \n ")
                            .build()
            ).queue();
            event.getJDA().getTextChannelById("556786200082645015").sendMessage(
                    event.getGuild().getRoleById("516219228471164928").getAsMention() + "\n Der User " + event.getAuthor().getAsMention() + " benötigt Support!").queue();
        } else {
            event.getTextChannel().sendMessage(
                    new EmbedBuilder().setColor(Color.ORANGE)
                            .setTitle(Emojis2.COOKIE + "" + Emojis2.WHITE_SMALL_SQUARE + "Support")
                            .setDescription("[Forum](https://keksofs.lima-city.de) \n [Discord Server](https://discord.gg/RY3aWrA)")
                            .build()
            ).queue();
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
