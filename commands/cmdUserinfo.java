package commands;

import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import util.Emojis2;
import util.STATIC;

import java.awt.*;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class cmdUserinfo implements Command{

        @Override
        public boolean called (String[] args, MessageReceivedEvent event) {
            return false;
        }

        @Override
        public void action (String[]  args, MessageReceivedEvent event) {
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
            String uhrzeit = sdf.format(new Date());
            //   event.getTextChannel().sendMessage ("Dieser Bot wurde von KeksOf (Sebl) geschrieben! Derzeit gibt es folgende Commands: -keks = Du kannst einen Keks essen. -deathbattle = Starte ein super krasses Battle gegen einen Freund etc -friendscore = Gebe einen weiteren User an und schaue wir sehr ihr Zusammen passt!  -- Version: 0.1").queue();
                    event.getTextChannel().sendMessage("Dieser Command wurde durch /profile erlöst!").queue();

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

