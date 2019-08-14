package commands;

import core.restHandler;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.Permission;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import util.Emojis;
import util.Emojis2;

import java.awt.*;

public class cmdSetLimit implements Command {
    @Override
    public boolean called(String[] args, MessageReceivedEvent event) {
        return false;
    }

    @Override
    public void action(String[] args, MessageReceivedEvent event) {

        restHandler.setCommands(event.getGuild(), restHandler.getCommands(event.getGuild()));

        if (!event.getMember().hasPermission(Permission.MANAGE_SERVER)) {
            event.getTextChannel().sendMessage(new EmbedBuilder().setColor(Color.red).setTitle(Emojis.WRENCH +"" + Emojis2.WHITE_SMALL_SQUARE + "<:X_:504673235212697601> Auch, da ist was schief gelaufen...").setDescription("Ich wollte " + args[0] + " tun, aber da habe ich festgestellt, das du gar nicht das Recht (`MANAGE_SERVER`) hast! \n Ich will nicht gefeuert werden, also melde dich erst wieder wenn du das Recht hast!").build()).queue();
            return;
        }
        try {

            int limit = Integer.parseInt(args[0]);
            restHandler.setSetServerLimit(event.getGuild().getId(), limit);
            event.getTextChannel().sendMessage(new EmbedBuilder().setColor(Color.GREEN).setTitle(Emojis2.GEAR + "" + Emojis2.WHITE_SMALL_SQUARE + " Erfolgreich").setDescription("Das neue Userlimit ist **" + limit + "**").build()).queue();
        } catch (Exception e) {
            event.getTextChannel().sendMessage(new EmbedBuilder().setColor(Color.RED).setTitle(Emojis2.GEAR + "" + Emojis2.WHITE_SMALL_SQUARE + "Fehler").setDescription("Bitte gebe eine Zahl als limit ein!").build()).queue();
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
