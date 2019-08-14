package commands;

import core.restHandler;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.Permission;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import util.Emojis;
import util.Emojis2;

import java.awt.*;

public class cmdIDBan implements Command {
    @Override
    public boolean called(String[] args, MessageReceivedEvent event) {
        return false;
    }

    @Override
    public void action(String[] args, MessageReceivedEvent event) {
        restHandler.setCommands(event.getGuild(), restHandler.getCommands(event.getGuild()));
        if (!event.getMember().hasPermission(Permission.BAN_MEMBERS)) {
            event.getTextChannel().sendMessage(new EmbedBuilder().setColor(Color.red).setTitle(Emojis.WRENCH +"" + Emojis2.WHITE_SMALL_SQUARE + "<:X_:504673235212697601> Auch, da ist was schief gelaufen...").setDescription("Ich wollte " + args[0] + " bannen, aber da habe ich festgestellt, das du gar nicht das Recht (`BAN_MEMBERS`) hast! \n Ich will nicht gefeuert werden, also melde dich erst wieder wenn du das Recht hast!").build()).queue();
            return;
        }
        if (args[0].equals("remove")) {
            if (restHandler.getIDBan(event.getGuild().getId(), args[1]) != null) {
                restHandler.removeIDBan(event.getGuild().getId(), args[1]);
                event.getTextChannel().sendMessage(new EmbedBuilder().setColor(Color.GREEN).setTitle(Emojis2.GEAR + "" + Emojis2.WHITE_SMALL_SQUARE + " Erfolgreich").setDescription("Der User mit der ID **" + args[1] + "** wurde von der Blacklist entfernt. \n Um ihn wieder zur Blacklist hinzuzufügen gebe  `/idban " + args[1] + "` ein.").build()).queue();

            } else {
                event.getTextChannel().sendMessage(new EmbedBuilder().setColor(Color.RED).setTitle(Emojis2.GEAR + "" + Emojis2.WHITE_SMALL_SQUARE + "Fehler").setDescription("Deine angebene ID steht nicht auf der Blacklist!").build()).queue();
            }


            return;
        }
        if (args[0].length() == 18) {
            restHandler.setSetIDBan(event.getGuild().getId(), args[0]);
            event.getTextChannel().sendMessage(new EmbedBuilder().setColor(Color.GREEN).setTitle(Emojis2.GEAR + "" + Emojis2.WHITE_SMALL_SQUARE + " Erfolgreich").setDescription("Der User mit der ID **" + args[0] + "** kann diesem Server nicht mehr betretten. \n Um ihn von der Blacklist zu removen gebe `/idban remove " + args[0] + "` ein.").build()).queue();

        } else {
            event.getTextChannel().sendMessage(new EmbedBuilder().setColor(Color.RED).setTitle(Emojis2.GEAR + "" + Emojis2.WHITE_SMALL_SQUARE + "Fehler").setDescription("Deine angebenen Argumente sind keine gültige ID!").build()).queue();
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
