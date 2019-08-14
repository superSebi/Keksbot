package commands;

import core.PrefixHandler;
import core.restHandler;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import util.Emojis2;

import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class cmdBugReport implements Command {

    @Override
    public boolean called (String[] args, MessageReceivedEvent event) {
        return false;
    }

    @Override
    public void action (String[]  args, MessageReceivedEvent event) {
        restHandler.setCommands(event.getGuild(), restHandler.getCommands(event.getGuild()));
        String out = "";
        for (String s : args) {
            out += s + " ";
        }
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        String uhrzeit = sdf.format(new Date());
        if (!(args.length == 0)) {
                event.getTextChannel().sendMessage(
                        new EmbedBuilder().setColor(Color.RED)
                                .setTitle("<:Erfolgreich:504672060501393418>" + Emojis2.WHITE_SMALL_SQUARE + "Erfolgreich")
                                .setDescription("Dein Bug Report (`" + out + "`), wurde an unser Team gesendet! \n Vielen Dank, wir bemühen uns ihn schnellst möglichst zu fixen.")
                                .setFooter("Gemeldet von " + event.getAuthor().getName() + " um " + uhrzeit + " Uhr", event.getJDA().getSelfUser().getAvatarUrl())
                                .build()).queue();
            event.getJDA().getGuildById("504609411243704365").getTextChannelById("556786200082645015").sendMessage("**[BUGREPORT!]** Der Nutzer **" + event.getAuthor().getName() + "** hat einen Bug gemeldet, auf dem Server **"  + event.getGuild().getName() + "**, um " + uhrzeit + " Uhr -> **``" + out + "``** \n " +  event.getJDA().getGuildById("504609411243704365").getRoleById("516219228471164928").getAsMention()).queue();
        } else {
            event.getTextChannel().sendMessage(
                    new EmbedBuilder().setColor(Color.RED)
                            .setTitle("<:X_:504673235212697601>" + Emojis2.WHITE_SMALL_SQUARE + "Fehler")
                            .setDescription("Du musst ein weiteres Argument angeben!")
                            .setFooter(PrefixHandler.getUserPrefix(event.getAuthor().getId(),event.getGuild().getId()) + "bugreport <beschreibung des bugs>", event.getJDA().getSelfUser().getAvatarUrl())
                            .build()).queue();
        }

    }

    @Override
    public void executed(boolean sucess, MessageReceivedEvent event) {
        System.out.println("[Info] Der Command bugreport wurde ausgeführt!");

    }

    @Override
    public  String  help() {

        return null;
    }
}
