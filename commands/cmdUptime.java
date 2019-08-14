package commands;

import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import util.Emojis2;

import java.awt.*;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class cmdUptime implements Command {

    private String getTime(Date date, String pattern) {
        return new SimpleDateFormat(pattern).format(date);
    }
    private String getTimeDiff(Date date1, Date date2) {
        long diff = date1.getTime() - date2.getTime();
        long diffSeconds = diff / 1000 % 60;
        long diffMinutes = diff / (60 * 1000) % 60;
        long diffHours = diff / (60 * 60 * 1000) % 24;
        long diffDays = diff / (24 * 60 * 60 * 1000);
        return diffDays + " d, " + parseTimeNumbs(diffHours) + " h, " + parseTimeNumbs(diffMinutes) + " min, " + parseTimeNumbs(diffSeconds) + " sec";
    }
    private String parseTimeNumbs(long time) {
        String timeString = time + "";
        if (timeString.length() < 2)
            timeString = "0" + time;
        return timeString;
    }

    public static Date lastRestart;
    public static int reconnectCount = 0;


    @Override
    public boolean called(String[] args, MessageReceivedEvent event) {
        return false;
    }

    @Override
    public void action(String[] args, MessageReceivedEvent event) throws ParseException, IOException {
        event.getTextChannel().sendMessage(
                new EmbedBuilder()
                        .setTitle(Emojis2.COOKIE + "" + Emojis2.WHITE_SMALL_SQUARE + " Uptime des Bots")
                        .setColor(new Color(255, 71,0))
                        .setDescription("Erfahre, wie lang ich Online bin.")
                       .addField("Letzter Neustart:", getTime(lastRestart, "dd.MM.yyyy - HH:mm"), false)
                       .addField("Ich bin Online seit:", getTimeDiff(new Date(), lastRestart), false)
                        .addField("Abstürze seit letztem Neustart:", reconnectCount + "", false)
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
