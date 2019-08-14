package commands;

import core.restHandler;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import util.Emojis2;

import java.awt.*;

public class cmdKI implements Command {
    @Override
    public boolean called(String[] args, MessageReceivedEvent event) {
        return false;
    }

    @Override
    public void action(String[] args, MessageReceivedEvent event) {
        restHandler.setCommands(event.getGuild(), restHandler.getCommands(event.getGuild()));
        event.getTextChannel().sendMessage(
                new EmbedBuilder().setColor(Color.GRAY)
                .setTitle("<:ki:536488024431656980> " + Emojis2.WHITE_SMALL_SQUARE + " KI")
                .setDescription("Um die KI zu nutzen, erstelle einen Channel mit dem Namen `beste-ki` \n Was ist eine KI?: \n KI bedeutet `**K**ünstliche **I**ntelligenz`. Sie kann mit dir reden, wie ein Mensch. \n Wie funktioniert es? \n Wenn du sie etwas fragen willst, mache ein ``?`` hinter deine Frage \n Außerdem reagiert Sie auf bestimmte Wörter wie `hasse` oder `liebe`")
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
