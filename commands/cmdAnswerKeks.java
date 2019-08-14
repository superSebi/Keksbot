package commands;

import core.RandomInt;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import util.Emojis2;
import util.Pictures;

import java.awt.*;

public class cmdAnswerKeks implements Command {

    @Override
    public boolean called(String[] args, MessageReceivedEvent event) {
        return false;
    }


    @Override
    public void action(String[] args, MessageReceivedEvent event) {
        String bild = String.format("%s", Pictures.values()[RandomInt.random(Pictures.values().length)].toString().toLowerCase());
        event.getTextChannel().deleteMessageById(event.getMessageId()).queue();
        event.getTextChannel().sendMessage(
                new EmbedBuilder().setColor(Color.YELLOW)
                        .setTitle(Emojis2.SMILING_FACE_WITH_OPEN_MOUTH_HAND_TIGHT + "" + Emojis2.WHITE_SMALL_SQUARE + "<:Erfolgreich:504672060501393418> Erfolgreich")
                        .setDescription("Ist der nicht süß <3")
                        .setImage(bild)
                        .setImage("https://cdn-images-1.medium.com/max/1600/1*qdFdhbR00beEaIKDI_WDCw.gif")
                        .build()
        ).queue();
    }





    @Override
    public void executed(boolean sucess, MessageReceivedEvent event) {
        System.out.println("[Info] Der Command answerfriendscore wurde ausgeführt!");

    }

    @Override
    public String help() {

        return null;
    }
}
