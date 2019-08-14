package commands;

import core.restHandler;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.Permission;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import util.Emojis;
import util.Emojis2;

import java.awt.*;

public class cmdWhitelist implements Command{
    @Override
    public boolean called(String[] args, MessageReceivedEvent event) {
        return false;
    }

    @Override
    public void action(String[] args, MessageReceivedEvent event) {
        if (!event.getMember().hasPermission(Permission.MANAGE_SERVER)) {
            event.getTextChannel().sendMessage(new EmbedBuilder().setColor(Color.red).setTitle(Emojis.WRENCH +"" + Emojis2.WHITE_SMALL_SQUARE + "<:X_:504673235212697601> Auch, da ist was schief gelaufen...").setDescription("Ich wollte " + args[0] + " auf die Whitelist setzen, das du gar nicht das Recht (`MANAGE_SERVER`) hast! \n Ich will nicht gefeuert werden, also melde dich erst wieder wenn du das Recht hast!").build()).queue();
            return;
        }
        if (args[0].contains("enable")) {
            if (restHandler.WhiteListEnable(event.getGuild().getId()) == 0) {
                restHandler.setEnableWhiteList(event.getGuild().getId(), 1);
                event.getTextChannel().sendMessage("Die Whitelist wurde aktiviert \n Warnung: Jetzt können nur noch User, die auf der Whitelist sind, auf den Server!").queue();
                return;
            } else {
                restHandler.setEnableWhiteList(event.getGuild().getId(), 0);
                event.getTextChannel().sendMessage("Die Whitelist wurde deaktiviert!").queue();

            }
        }
        if (args[0].length() == 18) {
            restHandler.setWhiteList(event.getGuild().getId(), args[0]);
            event.getTextChannel().sendMessage(new EmbedBuilder().setTitle(Emojis2.GEAR + "" + Emojis2.WHITE_SMALL_SQUARE + " Erfolgreich").setDescription(" Der User mit der ID " + args[0] + " wurde zur Whitelist, des Servers, hinzugefügt.").build()).queue();
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
