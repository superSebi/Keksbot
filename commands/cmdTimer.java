package commands;

import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import util.Emojis2;

import java.awt.*;
import java.io.IOException;
import java.text.ParseException;
import java.util.Arrays;
import java.util.Timer;
import java.util.TimerTask;

public class cmdTimer implements Command {
    @Override
    public boolean called(String[] args, MessageReceivedEvent event) {
        return false;
    }

    @Override
    public void action(String[] args, MessageReceivedEvent event) throws ParseException, IOException {
        if (args.length > 0) {
                String timer = "Nicht bennant";
                timer = String.join(" ", Arrays.asList(args).subList(1, args.length));
                User victim = event.getAuthor();
                int timeout;
                try {
                    StringBuilder sb = new StringBuilder();
                    Arrays.stream(args).forEach(s -> sb.append(" " + s));
                    timeout = Integer.parseInt(sb.toString().substring(0).replace(timer, "").replaceAll(" ", ""));
                } catch (Exception e) {
                    timeout = 0;
                }
                event.getTextChannel().sendMessage(new EmbedBuilder().setColor(Color.GREEN).setTitle(":alarm_clock:" + Emojis2.WHITE_SMALL_SQUARE + " Timer").setDescription(" Dein Timer (" + timer + ") endet in " + timeout + " Minuten. \n Der Bot sagt dir per PN bescheid").build()).queue();
                if (timeout > 0) {
                    String finalTimer = timer;
                    new Timer().schedule(new TimerTask() {
                        @Override
                        public void run() {
                            victim.openPrivateChannel().complete().sendMessage(" Dein Timer (" + finalTimer + ") ist abgelaufen!").queue();
                        }
                    }, timeout * 1000 * 60);
                } else {
                    event.getTextChannel().sendMessage("Fehler").queue();
                }
            } else {
                event.getTextChannel().sendMessage("Deine Naricht muss etwas länger sein.").queue();
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
