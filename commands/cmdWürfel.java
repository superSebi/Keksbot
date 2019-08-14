package commands;

import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import util.Emojis2;

import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class cmdWürfel implements Command {

    @Override
    public boolean called (String[] args, MessageReceivedEvent event) {
        return false;
    }

    @Override
    public void action (String[]  args, MessageReceivedEvent event) {

        Random wuerfel = new Random();
        int augenZahl;
        augenZahl = 1 + wuerfel.nextInt(6);
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        String uhrzeit = sdf.format(new Date());
        //   event.getTextChannel().sendMessage ("Dieser Bot wurde von KeksOf (Sebl) geschrieben! Derzeit gibt es folgende Commands: -keks = Du kannst einen Keks essen. -deathbattle = Starte ein super krasses Battle gegen einen Freund etc -friendscore = Gebe einen weiteren User an und schaue wir sehr ihr Zusammen passt!  -- Version: 0.1").queue();
        event.getTextChannel().sendMessage(
                new EmbedBuilder().setColor(Color.YELLOW)
                        .setTitle(Emojis2.SMILING_FACE_WITH_OPEN_MOUTH_HAND_TIGHT + "" +  Emojis2.WHITE_SMALL_SQUARE + "Würfel")
                        .setDescription("**Würfel** wird gewürfelt...")
                        .addField("<:Erfolgreich:504672060501393418> Erfolgreich", "Die Zahl ist **" + augenZahl + "**", false)
                        .setFooter("Gewürfelt von " + event.getAuthor().getName() + " - heute um " + uhrzeit, event.getAuthor().getAvatarUrl())
                        .build()).queue();

    }

    @Override
    public void executed(boolean sucess, MessageReceivedEvent event) {
        System.out.println("[Info] Der Command würfel wurde ausgeführt!");

    }

    @Override
    public  String  help() {

        return null;
    }
}
