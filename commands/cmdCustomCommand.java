package commands;

import core.CustomCommandsHandler;
import core.PrefixHandler;
import core.restHandler;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import util.Emojis2;

import java.awt.*;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class cmdCustomCommand implements Command {
    @Override
    public boolean called(String[] args, MessageReceivedEvent event) {
        return false;
    }

    @Override
    public void action(String[] args, MessageReceivedEvent event) throws ParseException, IOException {
        restHandler.setCommands(event.getGuild(), restHandler.getCommands(event.getGuild()));
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        String uhrzeit = sdf.format(new Date());

        //   event.getTextChannel().sendMessage ("Dieser Bot wurde von KeksOf (Sebl) geschrieben! Derzeit gibt es folgende Commands: -keks = Du kannst einen Keks essen. -deathbattle = Starte ein super krasses Battle gegen einen Freund etc -friendscore = Gebe einen weiteren User an und schaue wir sehr ihr Zusammen passt!  -- Version: 0.1").queue();
        if (args.length > 0) {
            switch (args[0].toLowerCase()) {
                case "add":
                    if (args.length == 3) {
                        if (event.getAuthor().getId().equals(event.getGuild().getOwnerId())) {
                            if (CustomCommandsHandler.getServerCommand(event.getGuild().getId()) == null) {
                                CustomCommandsHandler.setServerCommand(event.getGuild().getId(), args[1]);
                                CustomCommandsHandler.setAnswer(event.getGuild().getId(), args[2]);
                                event.getTextChannel().sendMessage(
                                        new EmbedBuilder().setColor(Color.GREEN)
                                                .setTitle("<:customcmd:576127906959327242>" + "" + Emojis2.WHITE_SMALL_SQUARE + "<:Erfolgreich:504672060501393418> Erfolgreich")
                                                .setDescription("Der Bot reagiert nun auf **`" + args[1] + "`** mit **" + args[2] + "**.")
                                                .setFooter("Ausgeführt von " + event.getAuthor().getName() + ", um " + uhrzeit, event.getAuthor().getAvatarUrl())
                                                .build()).queue();
                            } else {
                                event.getTextChannel().sendMessage(
                                        new EmbedBuilder().setColor(Color.RED)
                                                .setTitle("<:X_:504673235212697601>" + Emojis2.WHITE_SMALL_SQUARE + "Fehler")
                                                .setDescription("Du kannst derzeit nur **einen** CustomCommand setzen.")
                                                .setFooter("Ausgeführt von " + event.getAuthor().getName() + " um " + uhrzeit, event.getAuthor().getAvatarUrl())
                                                .build()).queue();
                            }
                        } else {
                            event.getTextChannel().sendMessage(
                                    new EmbedBuilder().setColor(Color.RED)
                                            .setTitle("<:X_:504673235212697601>" + Emojis2.WHITE_SMALL_SQUARE + "Fehler")
                                            .setDescription("Denn CustomCommand kann nur der `ServerInhaber` setzen.")
                                            .setFooter("Ausgeführt von " + event.getAuthor().getName() + " um " + uhrzeit, event.getAuthor().getAvatarUrl())
                                            .build()).queue();
                        }

                    } else {
                        event.getTextChannel().sendMessage(
                                new EmbedBuilder().setColor(Color.RED)
                                        .setTitle("<:X_:504673235212697601>" + Emojis2.WHITE_SMALL_SQUARE + "Fehler")
                                        .setDescription("Du musst ein weiteres Argument angeben!")
                                        .setFooter(PrefixHandler.getUserPrefix(event.getAuthor().getId(), event.getGuild().getId()) + "setserverprefix <Prefix>", "https://images-ext-2.discordapp.net/external/kCCNbCY9MZ1HDrC1K2XkWHxPfHlcHMjNflzy202Mq_g/https/images-ext-1.discordapp.net/external/FHVAXut353LQ9xRaxChWHL2CJYKvZWlqhUvdmS-lRPo/https/cdn.discordapp.com/avatars/349879801064194060/b0814cc2cd9a3e8a1e3d133c1aa726fe.png")
                                        .build()).queue();
                    }
                    break;
                case "list":
                    event.getTextChannel().sendMessage(
                            new EmbedBuilder().setColor(Color.GREEN)
                                    .setTitle("<:customcmd:576127906959327242>" + "" + Emojis2.WHITE_SMALL_SQUARE + "<:Erfolgreich:504672060501393418> CustomCommand Liste auf `" + event.getGuild().getName() + "`")
                                    .setDescription("Der Bot reagiert hier auf **" + CustomCommandsHandler.getServerCommand(event.getGuild().getId()) + "** mit **" + CustomCommandsHandler.getCommandAnswer(event.getGuild().getId()) + "**.")
                                    .setFooter("Ausgeführt von " + event.getAuthor().getName() + " um " + uhrzeit, event.getAuthor().getAvatarUrl())
                                    .build()).queue();
                    break;
                case "clear":
                case "remove":
                case "delete":
                    if(event.getAuthor().getId().equals(event.getGuild().getOwnerId())) {
                        CustomCommandsHandler.setAnswer(event.getGuild().getId(), null);
                        CustomCommandsHandler.setServerCommand(event.getGuild().getId(), null);
                        event.getTextChannel().sendMessage(
                                new EmbedBuilder().setColor(Color.GREEN)
                                        .setTitle("<:customcmd:576127906959327242>" + "" + Emojis2.WHITE_SMALL_SQUARE + "<:Erfolgreich:504672060501393418> CustomCommand Liste auf `" + event.getGuild().getName() + "`")
                                        .setDescription("Der **CustomCmd** wurde erfolgreich **gelöscht**.")
                                        .setFooter("Ausgeführt von " + event.getAuthor().getName() + " um " + uhrzeit, event.getAuthor().getAvatarUrl())
                                        .build()).queue();
                    } else {
                        event.getTextChannel().sendMessage(
                                new EmbedBuilder().setColor(Color.RED)
                                        .setTitle("<:X_:504673235212697601>" + Emojis2.WHITE_SMALL_SQUARE + "Fehler")
                                        .setDescription("Denn CustomCommand kann nur der `ServerInhaber` löschen.")
                                        .setFooter("Ausgeführt von " + event.getAuthor().getName() + " um " + uhrzeit, event.getAuthor().getAvatarUrl())
                                        .build()).queue();
                    }
                break;
            }
            }else{
            event.getTextChannel().sendMessage(
                    new EmbedBuilder().setColor(Color.RED)
                            .setTitle("<:X_:504673235212697601>" + Emojis2.WHITE_SMALL_SQUARE + "Fehler")
                            .setDescription("Du musst ein weiteres Argument angeben!")
                            .setFooter(PrefixHandler.getUserPrefix(event.getAuthor().getId(), event.getGuild().getId()) + "ccmd add <Command> <Antwort>", event.getJDA().getSelfUser().getAvatarUrl())
                            .build()).queue();
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
