package commands;

import core.restHandler;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import util.Emojis2;
import core.PrefixHandler;

import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class cmdPrefix implements Command {

        @Override
        public boolean called (String[] args, MessageReceivedEvent event) {
            return false;
        }

        @Override
        public void action (String[]  args, MessageReceivedEvent event) {
            restHandler.setCommands(event.getGuild(), restHandler.getCommands(event.getGuild()));
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
            String uhrzeit = sdf.format(new Date());
            //   event.getTextChannel().sendMessage ("Dieser Bot wurde von KeksOf (Sebl) geschrieben! Derzeit gibt es folgende Commands: -keks = Du kannst einen Keks essen. -deathbattle = Starte ein super krasses Battle gegen einen Freund etc -friendscore = Gebe einen weiteren User an und schaue wir sehr ihr Zusammen passt!  -- Version: 0.1").queue();
            event.getTextChannel().sendMessage(
                    new EmbedBuilder().setColor(Color.BLUE)
                            .setTitle(Emojis2.INFORMATION_SOURCE + "" + Emojis2.WHITE_SMALL_SQUARE + "Prefix")
                            .setDescription("**Prefix** auf diesem **Server**: `" + PrefixHandler.getServerPrefix(event.getGuild().getId()) + "` \n **Dein** persönlicher **Prefix**: `" + PrefixHandler.getUserPrefix(event.getAuthor().getId(),event.getGuild().getId()) + "`")
                            .addField("Dein persönlichen Prefix ändern:", "`setmyprefix`", false)
                            .addField("Dein Server-Prefix ändern:", "`setserverprefix`", false)
                            .setFooter("Ausgeführt von " + event.getAuthor().getName() + " - heute um " + uhrzeit, event.getAuthor().getAvatarUrl())
                            .build()).queue();

        }

        @Override
        public void executed(boolean sucess, MessageReceivedEvent event) {
            System.out.println("[Info] Der Command userinfo wurde ausgeführt!");

        }

        @Override
        public  String  help() {

            return null;
        }
    }

