package commands;

import core.restHandler;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import util.Emojis2;

import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class cmdRip implements Command {

    @Override //unnötig einfach nicht beachten uwu
    public boolean called (String[] args, MessageReceivedEvent event) {
        return false;
    }
    // hier ist der embed
    @Override
    public void action (String[] args, MessageReceivedEvent event) {
        restHandler.setCommands(event.getGuild(), restHandler.getCommands(event.getGuild()));
        String Sooslul = " ";
        for (String s : args) {
            Sooslul += s + " ";
        }

        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        String uhrzeit = sdf.format(new Date());

        if (!(args.length == 0)) {
            event.getTextChannel().sendMessage(
                    new EmbedBuilder().setColor(Color.ORANGE) // Color.FARBE
                            .setTitle("RIP : " + Sooslul)
                            .setDescription("Er war ein ehrwürdiger Keks")
                            .addField("<:Erfolgreich:504672060501393418> Todeszeugniss erfolgreich ausgefüllt", "**Name:** \n" + Sooslul + "\n **Gestorben um:** \n" + uhrzeit, false)
                            .setFooter("<- Dein Grabstein, von dem KeksGott persönlich", "https://cdn.discordapp.com/attachments/506844894778621953/506846453159690270/rip.png").build()
            ).queue();
        } else {
            event.getTextChannel().sendMessage(
                    new EmbedBuilder().setColor(Color.RED) // Color.FARBE
                            .setTitle("<:X_:504673235212697601>" + Emojis2.WHITE_SMALL_SQUARE + "Fehler")
                            .setDescription("Du musst einen angeben, der sterben soll!").build()

            ).queue();

        }
    }

    @Override
    public void executed(boolean succes, MessageReceivedEvent event) {
        System.out.println("[Info] Der Befehl RIP wurde von " + event.getAuthor().getName() + " ausgeführt."); //sieht man nur in der Console
    }

    @Override //unötig einfach nicht beachten
    public String help() {
        return null;
    }
}
