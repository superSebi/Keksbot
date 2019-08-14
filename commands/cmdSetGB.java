package commands;

import core.DateHandler;
import core.GBHandler;
import core.restHandler;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import util.Emojis2;

import java.awt.*;
import java.io.IOException;
import java.text.ParseException;

    public class cmdSetGB implements Command {
        @Override
        public boolean called(String[] args, MessageReceivedEvent event) {
            return false;
        }

        @Override
        public void action(String[] args, MessageReceivedEvent event) throws ParseException, IOException {
            restHandler.setCommands(event.getGuild(), restHandler.getCommands(event.getGuild()));

            int date1;
            int date2;
            int date3;
            switch (args[0].toLowerCase()) {
                case "setuser":
                    GBHandler.setSetUserName(event.getGuild().getId(), event.getMessage().getMentionedUsers().get(0));
                    if (DateHandler.getUserDate1(event.getGuild().getId()) == 0) {
                        event.getTextChannel().sendMessage("Ich habe den User erkannt, bitte sende mir per `/setgb setdate <tag> <monat> <jahr>` das Datum!").queue();
                    } else {
                        event.getTextChannel().sendMessage("Uwu, du hast alle Kekse gebacken! Guten Apetit :D").queue();
                        DateHandler.finishedDelete1(event.getGuild().getId());
                        DateHandler.finishedDelete2(event.getGuild().getId());
                        DateHandler.finishedDelete3(event.getGuild().getId());
                        GBHandler.finishedUser(event.getGuild().getId());
                        try {

                            event.getGuild().getTextChannelsByName("geburtstage", true).get(0).sendMessage(new EmbedBuilder().setTitle(":tada: " + Emojis2.WHITE_SMALL_SQUARE + GBHandler.getUserName(event.getGuild().getId()).getName() + "`s Geburtstag ").setDescription("Er hat am **" + DateHandler.getUserDate1(event.getGuild().getId()) + "." + DateHandler.getUserDate2(event.getGuild().getId()) + "." + DateHandler.getUserDate3(event.getGuild().getId()) + "** Geburtstag.").build()).queue();
                        } catch (Exception e) {
                            event.getChannel().sendMessage("Fehler! Es wurde nicht der GB Channel gefunden! \n Bitte erstelle einen neuen Channel, bei dem `geburtstage` im Namen vorkommt!").queue();
                        }
                    }
                    break;
                case "setdate":

                    date1 = Integer.parseInt(args[1]);
                    DateHandler.setUserDate1(event.getGuild().getId(), date1);
                    event.getTextChannel().sendMessage("Der Tag wurde gesetzt!").queue();
                    date2 = Integer.parseInt(args[2]);
                    DateHandler.setUserDate2(event.getGuild().getId(), date2);
                    event.getTextChannel().sendMessage("Der Monat wurde gesetzt!").queue();
                    date3 = Integer.parseInt(args[3]);
                    DateHandler.setUserDate3(event.getGuild().getId(), date3);
                    event.getTextChannel().sendMessage("Das Jahr wurde gesetzt!").queue();
                    if (GBHandler.getUserName(event.getGuild().getId()) == null) {
                        event.getTextChannel().sendMessage("Ich habe das Datum erhalten! Bitte setze das Geburstagskind mit `/setgb setuser <gepingterUser>`").queue();
                    } else {
                        event.getTextChannel().sendMessage("Uwu, du hast alle Kekse gebacken! Guten Apetit :D").queue();
                        DateHandler.finishedDelete1(event.getGuild().getId());
                        DateHandler.finishedDelete2(event.getGuild().getId());
                        DateHandler.finishedDelete3(event.getGuild().getId());
                        GBHandler.finishedUser(event.getGuild().getId());
                        try {

                            event.getGuild().getTextChannelsByName("geburtstage", true).get(0).sendMessage(new EmbedBuilder().setTitle(":tada: " + Emojis2.WHITE_SMALL_SQUARE + GBHandler.getUserName(event.getGuild().getId()).getName() + "`s Geburtstag ").setDescription("Er hat am **" + DateHandler.getUserDate1(event.getGuild().getId()) + "." + DateHandler.getUserDate2(event.getGuild().getId()) + "." + DateHandler.getUserDate3(event.getGuild().getId()) + "** Geburtstag.").build()).queue();

                        } catch (Exception e) {
                            event.getChannel().sendMessage("Fehler! Es wurde nicht der GB Channel gefunden! \n Bitte erstelle einen neuen Channel, bei dem `geburtstage` im Namen vorkommt!").queue();
                        }
                    }

                    break;
                case "help":
                    event.getTextChannel().sendMessage(new EmbedBuilder().setColor(Color.ORANGE).setTitle(":tada: " + Emojis2.WHITE_SMALL_SQUARE + " Keksiger GBtags Manager BETA | Hilfe").setDescription("`/setgb setuser <gepingerUser>` -> Setze das Geburtstags Kind \n `/setgb setdate <tag> <monat> <jahr>` -> Setze den Geburtstag \n \n :warning: | Du kannst beliebig viele Geburtstagskinder setzen, dazu musst du einfach alles machen wie davor aus, du musst nur die falschen Angaben, die er beim ersten setzen des z.B Users ausspuckt, wieder aus dem Channel löschen.").build()).queue();

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

