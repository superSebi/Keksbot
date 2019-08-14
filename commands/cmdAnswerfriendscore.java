package commands;

import core.RandomInt;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import util.Emojis2;

import java.awt.*;
import java.util.Collection;
import java.util.Random;

public class cmdAnswerfriendscore implements Command{

    private class AnswerFriendScoreThread implements Runnable
    {
        private MessageReceivedEvent event;
        private String args[];

        public AnswerFriendScoreThread(MessageReceivedEvent event,String args[]) {
            this.event = event;
            this.args = args;
        }

        @Override
        public void run() {
            String authorName2 = args[1];
            String nickname = args[0];
            Random zufallsgenerator = new Random();
            int ZuffalsZahl;
            ZuffalsZahl = 1+ zufallsgenerator.nextInt(100);


            event.getTextChannel().deleteMessageById(event.getMessage().getId()).queue();
            event.getTextChannel().sendMessage(
                    new EmbedBuilder().setColor(Color.GREEN)
                    .setTitle(authorName2 + ":revolving_hearts:" + nickname)
                    .setDescription(authorName2 + " passt " + ZuffalsZahl + "% zu " + nickname)
                    .build()
            ).queue();

        }
    }

    @Override
    public boolean called(String[] args, MessageReceivedEvent event) {
        return false;
    }


    @Override
    public void action(String[] args, MessageReceivedEvent event) {
        new Thread(new AnswerFriendScoreThread(event,args)).start();
    }

    @Override
    public void executed(boolean sucess, MessageReceivedEvent event) {
        System.out.println("[Info] Der Command answerdeathbattle wurde ausgeführt!");

    }

    @Override
    public String help() {

        return null;
    }
}
