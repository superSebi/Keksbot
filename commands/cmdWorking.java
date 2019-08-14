package commands;

import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import util.Emojis2;
import util.STATIC;

import java.awt.*;

public class cmdWorking implements Command {
    @Override
    public boolean called(String[] args, MessageReceivedEvent event) {
        return false;
    }

    @Override
    public void action(String[] args, MessageReceivedEvent event) {
        event.getTextChannel().sendMessage(
                new EmbedBuilder().setColor(Color.ORANGE)
                .setTitle(Emojis2.COOKIE + "" + Emojis2.WHITE_SMALL_SQUARE + " Woran wir gerade Arbeiten")
                .setDescription("Wir arbeiten der Zeit an der Version **11.1.0**: \n `/setlog`, `/setblacklist` \n Außerdem haben wir schon Ideen für die Version **12.0.0** wo die Funktion **WEBISTE-DASHBOARD** und **SPLATNET** hinzugefügt werden! \n *Version 11.0.0 wurde noch nicht angefangen zu programieren, wir halten uns Änderungen vor!*")
                .setFooter("Die Aktuelle Version ist " + STATIC.VERS + ", Änderungen vorbehalten", null)
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
