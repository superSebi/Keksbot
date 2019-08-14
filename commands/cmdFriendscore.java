package commands;

import core.restHandler;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import util.Emojis2;

import java.awt.*;

public class cmdFriendscore implements Command{

    @Override
    public boolean called (String[] args, MessageReceivedEvent event) {
        return false;
    }

    @Override
    public void action (String[]  args, MessageReceivedEvent event) {
        restHandler.setCommands(event.getGuild(), restHandler.getCommands(event.getGuild()));

        if(args.length == 1) {
            String authorName2 = event.getAuthor().getName();
            String nickname = args [0];
            event.getTextChannel().sendMessage("/answerfriendscore " +  args[0] + " " + authorName2).queue();
             } else {
            event.getTextChannel().sendMessage(
                    new EmbedBuilder().setColor(Color.RED)
                            .setTitle("<:X_:504673235212697601>"  + Emojis2.WHITE_SMALL_SQUARE + "Fehler")
                            .setDescription("Du darfst nur 1 Argument haben")
                            .setFooter("Fehler beim Ausführen des Commands.", event.getAuthor().getAvatarUrl())
                            .build()).queue();
                }
    }

    @Override
    public void executed(boolean sucess, MessageReceivedEvent event) {
        System.out.println("[Info] Der Command friendscore wurde ausgeführt!");

    }

    @Override
    public  String  help() {

        return null;
    }
}
