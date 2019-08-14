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

public class cmdKick implements Command {

    @Override
    public boolean called(String[] args, MessageReceivedEvent event) {
        return false;
    }

    @Override
    public void action(String[] args, MessageReceivedEvent event) {
        restHandler.setCommands(event.getGuild(), restHandler.getCommands(event.getGuild()));
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        String uhrzeit = sdf.format(new Date());
        String reason = "Ungegründet";
            reason = String.join(" ", Arrays.asList(args).subList(1, args.length));
            for (User pinged : event.getMessage().getMentionedUsers()) {
                if (event.getMember().hasPermission(Permission.KICK_MEMBERS)) {
                    if (args.length >= 1) {
                        if (!pinged.getId().equals(event.getGuild().getOwnerId())) {
                            if (!event.getGuild().getMember(event.getMessage().getMentionedUsers().get(0)).hasPermission(Permission.KICK_MEMBERS)) {
                                if (!event.getMessage().getMentionedUsers().get(0).isBot()) {
                                //   event.getTextChannel().sendMessage ("Dieser Bot wurde von KeksOf (Sebl) geschrieben! Derzeit gibt es folgende Commands: -keks = Du kannst einen Keks essen. -deathbattle = Starte ein super krasses Battle gegen einen Freund etc -friendscore = Gebe einen weiteren User an und schaue wir sehr ihr Zusammen passt!  -- Version: 0.1").queue();
                                event.getTextChannel().sendMessage(
                                        new EmbedBuilder().setColor(Color.GREEN)
                                                .setTitle("<:Erfolgreich:504672060501393418>" + Emojis2.WHITE_SMALL_SQUARE + "Erfolgreich")
                                                .setDescription("Der Spieler `" + pinged.getName() + "` wurde wegen **" + reason + " ** gekickt.")
                                                .setFooter("Ausgeführt von " + event.getAuthor().getName() + " - heute um " + uhrzeit, event.getAuthor().getAvatarUrl())
                                                .build()).queue();
                                TextChannel channel = event.getGuild().getTextChannelsByName("log", true).get(0);
                                    channel.sendMessage(
                                        new EmbedBuilder().setColor(Color.RED) // Color.FARBE
                                                .setTitle("<:confirm:509070945948925962>" + Emojis2.WHITE_SMALL_SQUARE + " KICK")
                                                .addField("Spieler", pinged.getName(), true)
                                                .addField("Gekickt von", event.getAuthor().getName(), true)
                                                .addField("Gekickt um", uhrzeit, true)
                                                .addField("Grund", reason + "", false)
                                                .build()
                                ).queue();
                                    PrivateChannel pc = event.getMessage().getMentionedUsers().get(0).openPrivateChannel().complete();
                                    pc.sendMessage(
                                            "Du wurdest von dem Server " + event.getGuild().getName() + " von " + event.getAuthor().getAsMention() + " (" + event.getMember().getRoles().get(0).getName() + ") gekickt. \n" +
                                                    "Grund: " + reason + "\n" +
                                                    "Falls du erneut auf dem Server kommen willst, verhalte dich besser! \n" +
                                                    "Wenn du denkst dass dies ein Fehler ist kontakiere den Server-Inhaber (" + event.getGuild().getOwner().getAsMention() + ")"
                                    ).queue();
                                    event.getGuild().getController().kick(event.getGuild().getMember(event.getMessage().getMentionedUsers().get(0))).queue();
                                } else {
                                    event.getTextChannel().sendMessage(
                                            new EmbedBuilder().setColor(Color.RED)
                                                    .setTitle("<:X_:504673235212697601> " + Emojis2.WHITE_SMALL_SQUARE + "Unerwarteter Fehler: `Konnte keine PN zu einem Bot senden, Bot Error`")
                                                    .setDescription("Es gab einen Uerwarteten Fehler, Fehler Details: \n  `java.lang.UnsupportedOperationException: Cannot send a private message between bots.\n" +
                                                            "        at net.dv8tion.jda.core.entities.impl.PrivateChannelImpl.checkBot(PrivateChannelImpl.java:211)\n" +
                                                            "        at net.dv8tion.jda.core.entities.impl.PrivateChannelImpl.sendMessage(PrivateChannelImpl.java:143)\n" +
                                                            "        at commands.cmdKick.action(cmdKick.java:58)\\n\" +\n" +
                                                            "                                                            \"        at core.CommandHandler.handleCommand(CommandHandler.java:21)\\n\" +\n" +
                                                            "                                                            \"        at listeners.commandListener.onMessageReceived(commandListener.java:20)\\n\" +\n" +
                                                            "                                                            \"        at net.dv8tion.jda.core.hooks.ListenerAdapter.onEvent(ListenerAdapter.java:410)\\n\" +\n" +
                                                            "                                                            \"        at net.dv8tion.jda.core.hooks.InterfacedEventManager.handle(InterfacedEventManager.java:84)\n" +
                                                            "        at net.dv8tion.jda.core.handle.MessageCreateHandler.handleInternally(MessageCreateHandler.java:127)\n" +
                                                            "        at net.dv8tion.jda.core.handle.SocketHandler.handle(SocketHandler.java:37)\n" +
                                                            "        at net.dv8tion.jda.core.requests.WebSocketClient.onDispatch(WebSocketClient.java:994)\n" +
                                                            "        at net.dv8tion.jda.core.requests.WebSocketClient.onEvent(WebSocketClient.java:892)\n" +
                                                            "        at net.dv8tion.jda.core.requests.WebSocketClient.handleEvent(WebSocketClient.java:871)\n" +
                                                            "        at net.dv8tion.jda.core.requests.WebSocketClient.onBinaryMessage(WebSocketClient.java:1029)\n" +
                                                            "        at com.neovisionaries.ws.client.ListenerManager.callOnBinaryMessage(ListenerManager.java:368)\n" +
                                                            "        at com.neovisionaries.ws.client.ReadingThread.callOnBinaryMessage(ReadingThread.java:270)\n" +
                                                            "        at com.neovisionaries.ws.client.ReadingThread.handleBinaryFrame(ReadingThread.java:990)\n" +
                                                            "        at com.neovisionaries.ws.client.ReadingThread.handleFrame(ReadingThread.java:749)\n" +
                                                            "        at com.neovisionaries.ws.client.ReadingThread.main(ReadingThread.java:108)\n" +
                                                            "        at com.neovisionaries.ws.client.ReadingThread.runMain(ReadingThread.java:64)\n" +
                                                            "        at com.neovisionaries.ws.client.WebSocketThread.run(WebSocketThread.java:45)` \n **Lösung:** Ich als Bot, kann keine Bots kicken, bitte mache dies per Hand :/")
                                                    .setFooter("Fehler ausgelösst von " + event.getAuthor().getName() + " - heute um " + uhrzeit, event.getAuthor().getAvatarUrl())
                                                    .build()).queue();
                                }
                            } else {
                                event.getTextChannel().sendMessage(
                                        new EmbedBuilder().setColor(Color.RED)
                                                .setTitle("<:X_:504673235212697601> " + Emojis2.WHITE_SMALL_SQUARE + " Fehler")
                                                .setDescription("Du darfst keine `Team Mitglieder` kicken!")
                                                .setFooter("Ausgeführt von " + event.getAuthor().getName() + " - heute um " + uhrzeit, event.getAuthor().getAvatarUrl())
                                                .build()).queue();
                            }
                        } else {
                            event.getTextChannel().sendMessage(
                                    new EmbedBuilder().setColor(Color.RED)
                                            .setTitle("<:X_:504673235212697601> " + Emojis2.WHITE_SMALL_SQUARE + " Fehler")
                                            .setDescription("Du darfst nicht den `Server Inhaber` kicken!")
                                            .setFooter("Ausgeführt von " + event.getAuthor().getName() + " - heute um " + uhrzeit, event.getAuthor().getAvatarUrl())
                                            .build()).queue();
                        }
                    } else {
                        event.getTextChannel().sendMessage(
                                new EmbedBuilder().setColor(Color.RED)
                                        .setTitle("<:X_:504673235212697601> " + Emojis2.WHITE_SMALL_SQUARE + " Fehler")
                                        .setDescription("Du musst angeben wer gekickt werden soll und einen Grund warum er gekickt werden soll!")
                                        .setFooter("Ausgeführt von " + event.getAuthor().getName() + " - heute um " + uhrzeit, event.getAuthor().getAvatarUrl())
                                        .build()).queue();
                    }
                } else {
                    event.getTextChannel().sendMessage(new EmbedBuilder().setColor(Color.red).setTitle(Emojis.WRENCH +"" + Emojis2.WHITE_SMALL_SQUARE + "<:X_:504673235212697601> Auch, da ist was schief gelaufen...").setDescription("Ich wollte" + pinged.getAsMention() + " kicken, aber da habe ich festgestellt, das du gar nicht das Recht (`KICK_USERS`) hast! \n Ich will nicht gefeuert werden, also melde dich erst wieder wenn du das Recht hast!").build()).queue();
                }
            }
            if (args.length == 0) {
                event.getTextChannel().sendMessage(
                        new EmbedBuilder().setColor(Color.RED)
                                .setTitle("<:X_:504673235212697601> " + Emojis2.WHITE_SMALL_SQUARE + " Fehler")
                                .setDescription("Der Befehl `kick` muss folgend aussehen: \n /kick @Name <Grund>")
                                .setFooter("Ausgeführt von " + event.getAuthor().getName() + " - heute um " + uhrzeit, event.getAuthor().getAvatarUrl())
                                .build()).queue();
            }
        }

    @Override
    public void executed(boolean sucess, MessageReceivedEvent event) {
        System.out.println("[Info] Der Spieler " + event.getAuthor().getName() + " hat den befehl Kick ausgeführt!");

    }

    @Override
    public String help() {

        return null;
    }
}


