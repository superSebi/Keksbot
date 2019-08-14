package commands;

import core.restHandler;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.Permission;
import net.dv8tion.jda.core.entities.PrivateChannel;
import net.dv8tion.jda.core.entities.TextChannel;
import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import util.Emojis;
import util.Emojis2;

import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class cmdBan implements Command {
    @Override
    public boolean called(String[] args, MessageReceivedEvent event) {
        return false;
    }

    @Override
    public void action(String[] args, MessageReceivedEvent event) {
        restHandler.setCommands(event.getGuild(), restHandler.getCommands(event.getGuild()));
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        String uhrzeit = sdf.format(new Date());
        String reason = "Unbekannt";
        if (args.length > 2) {
            reason = String.join(" ", Arrays.asList(args).subList(1, args.length));
        }

        for (User pinged : event.getMessage().getMentionedUsers()) {
            if (event.getMember().hasPermission(Permission.BAN_MEMBERS)) {
                if (args.length > 1) {
                    if (!pinged.getId().equals(event.getGuild().getOwnerId())) {
                        if (!event.getGuild().getMember(event.getMessage().getMentionedUsers().get(0)).hasPermission(Permission.BAN_MEMBERS)) {
                            //   event.getTextChannel().sendMessage ("Dieser Bot wurde von KeksOf (Sebl) geschrieben! Derzeit gibt es folgende Commands: -keks = Du kannst einen Keks essen. -deathbattle = Starte ein super krasses Battle gegen einen Freund etc -friendscore = Gebe einen weiteren User an und schaue wir sehr ihr Zusammen passt!  -- Version: 0.1").queue();
                            event.getTextChannel().sendMessage(
                                    new EmbedBuilder().setColor(Color.GREEN)
                                            .setTitle("<:Erfolgreich:504672060501393418>" + Emojis2.WHITE_SMALL_SQUARE + "Erfolgreich")
                                            .setDescription("Der Spieler, `" + pinged.getName() + "` wurde wegen **" + reason + " **, gebannt.")
                                            .setFooter("Ausgeführt von " + event.getAuthor().getName() + " - heute um " + uhrzeit, event.getAuthor().getAvatarUrl())
                                            .build()).queue();
                            TextChannel channel = event.getGuild().getTextChannelsByName("log", true).get(0);
                            channel.sendMessage(
                                    new EmbedBuilder().setColor(Color.RED) // Color.FARBE
                                            .setTitle("<:confirm:509070945948925962>" + Emojis2.WHITE_SMALL_SQUARE + " BAN")
                                            .addField("Spieler", pinged.getName(), true)
                                            .addField("Gebannt von", event.getAuthor().getName(), true)
                                            .addField("Gebannt um", uhrzeit, true)
                                            .addField("Grund", reason + "", false)
                                            .build()
                            ).queue();
                            PrivateChannel pc = event.getMessage().getMentionedUsers().get(0).openPrivateChannel().complete();
                            pc.sendMessage(
                                    "Du wurdest von dem Server " + event.getGuild().getName() + " von " + event.getAuthor().getAsMention() + " (" + event.getMember().getRoles().get(0).getName() + ") gebannt! \n" +
                                            "Grund: " + reason + "\n" +
                                            "Falls du erneut auf dem Server kommen willst, schicke dem Owner eine PN und bitte ihn dich, dich zu entbannen. \n" +
                                            "Wenn du denkst dass dies ein Fehler ist, kontakiere den Server-Inhaber (" + event.getGuild().getOwner().getAsMention() + ")"
                            ).queue();
                            event.getGuild().getController().ban(event.getGuild().getMember(event.getMessage().getMentionedUsers().get(0)), 7).queue();
                        } else {
                            event.getTextChannel().sendMessage(
                                    new EmbedBuilder().setColor(Color.RED)
                                            .setTitle("<:X_:504673235212697601> " + Emojis2.WHITE_SMALL_SQUARE + " Fehler")
                                            .setDescription("Du darfst keine `Team Mitglieder` bannen!")
                                            .setFooter("Ausgeführt von " + event.getAuthor().getName() + " - heute um " + uhrzeit, event.getAuthor().getAvatarUrl())
                                            .build()).queue();
                        }
                    } else {
                        event.getTextChannel().sendMessage(
                                new EmbedBuilder().setColor(Color.RED)
                                        .setTitle("<:X_:504673235212697601> " + Emojis2.WHITE_SMALL_SQUARE + " Fehler")
                                        .setDescription("Du darfst nicht den `Server Inhaber` bannen!")
                                        .setFooter("Ausgeführt von " + event.getAuthor().getName() + " - heute um " + uhrzeit, event.getAuthor().getAvatarUrl())
                                        .build()).queue();
                    }
                } else {
                    event.getTextChannel().sendMessage(
                            new EmbedBuilder().setColor(Color.RED)
                                    .setTitle("<:X_:504673235212697601> " + Emojis2.WHITE_SMALL_SQUARE + " Fehler")
                                    .setDescription("Du musst angeben wer gekickt werden soll und einen Grund warum er gebannt werden soll!")
                                    .setFooter("Ausgeführt von " + event.getAuthor().getName() + " - heute um " + uhrzeit, event.getAuthor().getAvatarUrl())
                                    .build()).queue();
                }
            } else {
                event.getTextChannel().sendMessage(new EmbedBuilder().setColor(Color.red).setTitle(Emojis.WRENCH +"" + Emojis2.WHITE_SMALL_SQUARE + "<:X_:504673235212697601> Auch, da ist was schief gelaufen...").setDescription("Ich wollte" + pinged.getAsMention() + " bannen, aber da habe ich festgestellt, das du gar nicht das Recht (`BAN_MEMBERS`) hast! \n Ich will nicht gefeuert werden, also melde dich erst wieder wenn du das Recht hast!").build()).queue();
            }
        }
        if (args.length == 0) {
            event.getTextChannel().sendMessage(
                    new EmbedBuilder().setColor(Color.RED)
                            .setTitle("<:X_:504673235212697601> " + Emojis2.WHITE_SMALL_SQUARE + " Fehler")
                            .setDescription("Der Befehl `ban` muss folgender Weiße aussehen: \n /ban @Name <Grund>")
                            .setFooter("Ausgeführt von " + event.getAuthor().getName() + " - heute um " + uhrzeit, event.getAuthor().getAvatarUrl())
                            .build()).queue();
        }
    }

    @Override
    public void executed(boolean sucess, MessageReceivedEvent event) {
        System.out.println("[Info] Der Spieler " + event.getAuthor().getName() + " hat den Befehl Ban ausgeführt!");

    }

    @Override
    public String help() {

        return null;
    }
}
