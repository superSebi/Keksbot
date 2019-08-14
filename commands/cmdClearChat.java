package commands;

import core.restHandler;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.Permission;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.MessageHistory;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import util.Emojis;
import util.Emojis2;

import java.awt.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;


public class cmdClearChat implements Command {
    @Override
    public boolean called(String[] args, MessageReceivedEvent event) {
            return false;
        }
        private int getInt(String arg) {

        try {
            return Integer.parseInt(arg);
        } catch (Exception e) {
            return 0;
        }

    }

    @Override
    public void action(String[] args, MessageReceivedEvent event) {

        restHandler.setCommands(event.getGuild(), restHandler.getCommands(event.getGuild()));
        if(event.getMember().hasPermission(Permission.MESSAGE_MANAGE)) {
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
            String uhrzeit = sdf.format(new Date());
            try {
                MessageHistory history = new MessageHistory(event.getTextChannel());
                List<Message> msgs;
                if (args.length == 1 && args[0].equalsIgnoreCase("all")) {
                    try {
                        while (true) {
                            msgs = history.retrievePast(1).complete();
                            msgs.get(0).delete().queue();
                        }
                    } catch (Exception ex) {
                        //Nichts tun
                    }

                    Message answer = event.getTextChannel().sendMessage(new EmbedBuilder().setColor(Color.GREEN)
                            .setTitle(Emojis.WRENCH + "" + Emojis2.WHITE_SMALL_SQUARE + " ClearChat erfolgreich ausgeführt!")
                            .setDescription("Alle Narichten wurde erfolgreich gelöscht!")
                            .build()).complete();
                    try {
                        event.getGuild().getTextChannelsByName("log", true).get(0).sendMessage(new EmbedBuilder()
                                .setTitle(Emojis.WRENCH + event.getAuthor().getName() + " hat den ClearChat ausgeführt!")
                                .addField("Name des Users: ", "``" + event.getAuthor().getName() + "``", false)
                                .addField("Insgesammt gelöscht Nachrichten:", "``" + args[0] + " (alle im Channel)``", false)
                                .addField("Gelöscht um:", "``" + uhrzeit + "``", false)
                                .addField("Channel:", event.getTextChannel().getAsMention(), false)
                                .setFooter("INFO: Um den Log zu deaktivieren, lösche einfach diesen Channel", null)
                                .build()

                        ).queue();
                    } catch (Exception e) {

                    }

                    new Timer().schedule(new TimerTask() {
                        @Override
                        public void run() {
                            answer.delete().queue();
                        }
                    }, 3000);

                } else if (args.length < 1 || (args.length > 0 ? getInt(args[0]) : 1) == 1 && (args.length > 0 ? getInt(args[0]) : 1) < 2) {

                    event.getMessage().delete().queue();
                    msgs = history.retrievePast(2).complete();
                    msgs.get(0).delete().queue();

                    Message answer = event.getTextChannel().sendMessage(new EmbedBuilder().setColor(Color.GREEN)
                            .setTitle(Emojis.WRENCH + "" + Emojis2.WHITE_SMALL_SQUARE + " ClearChat erfolgreich ausgeführt!")
                            .setDescription("Die letzte Naricht wurde erfolgreich gelöscht!")
                            .build()).complete();
                    try {
                        event.getGuild().getTextChannelsByName("log", true).get(0).sendMessage(new EmbedBuilder()
                                .setTitle(Emojis.WRENCH + event.getAuthor().getName() + " hat den ClearChat ausgeführt!")
                                .addField("Name des Users: ", "``" + event.getAuthor().getName() + "``", false)
                                .addField("Insgesammt gelöscht Nachrichten:", "``" + args[0] + "``", false)
                                .addField("Gelöscht um:", "``" + uhrzeit + "``", false)
                                .addField("Channel:", event.getTextChannel().getAsMention(), false)
                                .setFooter("INFO: Um den Log zu deaktivieren, lösche einfach diesen Channel", null)
                                .build()

                        ).queue();
                    } catch (Exception e) {

                    }
                    new Timer().schedule(new TimerTask() {
                        @Override
                        public void run() {
                            answer.delete().queue();
                        }
                    }, 3000);

                } else if (args.length == 2) {
                    // 24/03/2013 21:54
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
                    StringBuilder builder = new StringBuilder();

                    for (String arg : args) {
                        builder.append(" " + arg);
                    }

                    try {
                        Date date = simpleDateFormat.parse(builder.toString());

                        boolean weiter = true;
                        try {
                            while (weiter) {
                                msgs = history.retrievePast(1).complete();
                                if (date.before(Date.from(msgs.get(0).getCreationTime().toZonedDateTime().toInstant()))) {
                                    msgs.get(0).delete().queue();
                                } else {
                                    weiter = false;
                                }

                            }

                            Message answer = event.getTextChannel().sendMessage(new EmbedBuilder().setColor(Color.GREEN)
                                    .setTitle(Emojis.WRENCH + "" + Emojis2.WHITE_SMALL_SQUARE + " ClearChat erfolgreich ausgeführt!")
                                    .setDescription(args[0] + " Narichten gelöscht!")
                                    .build()).complete();
                            try {
                                event.getGuild().getTextChannelsByName("log", true).get(0).sendMessage(new EmbedBuilder()
                                        .setTitle(Emojis.WRENCH + event.getAuthor().getName() + " hat den ClearChat ausgeführt!")
                                        .addField("Name des Users: ", "``" + event.getAuthor().getName() + "``", false)
                                        .addField("Insgesammt gelöscht Nachrichten:", "``" + args[0] + "``", false)
                                        .addField("Gelöscht um:", "``" + uhrzeit + "``", false)
                                        .addField("Channel:", event.getTextChannel().getAsMention(), false)
                                        .setFooter("INFO: Um den Log zu deaktivieren, lösche einfach diesen Channel", null)
                                        .build()

                                ).queue();
                            } catch (Exception e) {

                            }
                            new Timer().schedule(new TimerTask() {
                                @Override
                                public void run() {
                                    answer.delete().queue();
                                }
                            }, 3000);
                        } catch (Exception ex) {
                            //Nichts tun
                        }
                    } catch (ParseException ex) {
                        event.getTextChannel().sendMessage(new EmbedBuilder().setColor(Color.RED)
                                .addField("Fehler:", "Falsches Zeitformat.", false)
                                .addField("Description", "Bitte gebe das richtige Zeiformat ein:\n" + simpleDateFormat.format(new Date()), false)
                                .build()
                        ).queue();
                    }

                } else if (getInt(args[0]) <= 100) {

                    event.getMessage().delete().queue();
                    msgs = history.retrievePast(getInt(args[0])).complete();
                    event.getTextChannel().deleteMessages(msgs).queue();

                    Message answer = event.getTextChannel().sendMessage(new EmbedBuilder().setColor(Color.GREEN)
                            .setTitle(Emojis.WRENCH + "" + Emojis2.WHITE_SMALL_SQUARE + " ClearChat erfolgreich ausgeführt!")
                            .setDescription(args[0] + " Narichten gelöscht!")
                            .build()).complete();
                    try {
                        event.getGuild().getTextChannelsByName("log", true).get(0).sendMessage(new EmbedBuilder()
                                .setTitle(Emojis.WRENCH + event.getAuthor().getName() + " hat den ClearChat ausgeführt!")
                                .addField("Name des Users: ", "``" + event.getAuthor().getName() + "``", false)
                                .addField("Insgesammt gelöscht Nachrichten:", "``" + args[0] + "``", false)
                                .addField("Gelöscht um:", "``" + uhrzeit + "``", false)
                                .addField("Channel:", event.getTextChannel().getAsMention(), false)
                                .setFooter("INFO: Um den Log zu deaktivieren, lösche einfach diesen Channel", null)
                                .build()

                        ).queue();
                    } catch (Exception e) {

                    }

                    new Timer().schedule(new TimerTask() {
                        @Override
                        public void run() {
                            answer.delete().queue();
                        }
                    }, 3000);
                } else {
                    event.getTextChannel().sendMessage(new EmbedBuilder().setColor(Color.RED)
                            .addField("Fehler:", "Limit erreicht", false)
                            .addField("Description", "Es können nicht mehr als 100 Narichten gelöscht werden!", false)
                            .build()
                    ).queue();
                }


            } catch (Exception e) {
                event.getTextChannel().sendMessage(new EmbedBuilder().setColor(Color.RED)
                        .addField("Fehler:", e.getLocalizedMessage(), false)
                        .addField("Naricht:", e.getMessage(), false)
                        .build()
                ).queue();
            }
        } else {
            event.getTextChannel().sendMessage(new EmbedBuilder().setColor(Color.RED)
                    .addField("Fehler:", "Dir fehlt die `MESSAGE_MANAGE` Permission", false)
                    .build()
            ).queue();
        }
        }




    @Override
    public void executed(boolean sucess, MessageReceivedEvent event) {

        System.out.println("[Info] Der Command CC wurde, von " + event.getAuthor().getName() + ", im TextChannel " + event.getTextChannel().getName() + ", auf dem Server "  + event.getGuild().getName() + ", ausgeführt!");
    }

    @Override
    public String help() {
        return null;
    }
}
