package commands;

import core.restHandler;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.Permission;
import net.dv8tion.jda.core.entities.TextChannel;
import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import util.Emojis;
import util.Emojis2;

import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class cmdWarn implements Command {

    @Override //unnötig einfach nicht beachten uwu
    public boolean called (String[] args, MessageReceivedEvent event) {
        return false;
    }
    @Override
    public void action (String[] args, MessageReceivedEvent event) {
        restHandler.setCommands(event.getGuild(), restHandler.getCommands(event.getGuild()));

        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        String uhrzeit = sdf.format(new Date());

        String reason = "Ungegründet";
        if (args.length > 1)
            reason = String.join(" ", Arrays.asList(args).subList(1, args.length));


            for (User pinged : event.getMessage().getMentionedUsers()) {
                if (event.getMember().hasPermission(Permission.KICK_MEMBERS)) {
                    event.getTextChannel().sendMessage(
                            new EmbedBuilder().setColor(Color.GREEN)
                                    .setTitle("<:Erfolgreich:504672060501393418>" + Emojis2.WHITE_SMALL_SQUARE + "Erfolgreich")
                                    .setDescription("Der Spieler, `" + pinged.getName() + "`, wurde wegen **" + reason + "**, vewarnt.")
                                    .setFooter("Ausgeführt von " + event.getAuthor().getName() + " - heute um " + uhrzeit, event.getAuthor().getAvatarUrl())
                                    .build()).queue();
                    TextChannel channel = event.getGuild().getTextChannelsByName("log", true).get(0);
                    channel.sendMessage(
                            new EmbedBuilder().setColor(Color.ORANGE) // Color.FARBE
                                    .setTitle("<:confirm:509070945948925962>" + Emojis2.WHITE_SMALL_SQUARE + " WARNUNG")
                                    .addField("Spieler", pinged.getName(), true)
                                    .addField("Gewarnt von", event.getAuthor().getName(), true)
                                    .addField("Gewarnt um", uhrzeit, true)
                                    .addField("Grund", reason + "", false)
                                    .build()
                    ).queue();

                } else {
                    event.getTextChannel().sendMessage(new EmbedBuilder().setColor(Color.red).setTitle(Emojis.WRENCH + "" + Emojis2.WHITE_SMALL_SQUARE + "<:X_:504673235212697601> Auch, da ist was schief gelaufen...").setDescription("Ich wollte" + pinged.getAsMention() + " kicken, aber da habe ich festgestellt, das du gar nicht das Recht (`KICK_USER`) hast! \n Ich will nicht gefeuert werden, also melde dich erst wieder wenn du das Recht hast!").build()).queue();
                }
                if (args.length == 0) {
                    event.getTextChannel().sendMessage(
                            new EmbedBuilder().setColor(Color.RED)
                                    .setTitle("<:X_:504673235212697601> " + Emojis2.WHITE_SMALL_SQUARE + " Fehler")
                                    .setDescription("Der Befehl `warn` muss folgenderweiße aussehen: \n `/warn (Spieler) <Grund>`")
                            .build()
                    ).queue();
                }
            }
        }

    @Override
    public void executed(boolean succes, MessageReceivedEvent event) {
        System.out.println("[Info] Der Befehl warn wurde von " + event.getAuthor().getName() + " ausgeführt."); //sieht man nur in der Console
    }

    @Override //unötig einfach nicht beachten
    public String help() {
        return null;
    }
}
