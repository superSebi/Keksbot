package commands;

import core.CustomCommandsHandler;
import core.PrefixHandler;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.Emote;
import net.dv8tion.jda.core.entities.Role;
import net.dv8tion.jda.core.entities.TextChannel;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import util.Emojis2;

import java.awt.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public class cmdServerinfo implements Command{

    @Override
    public boolean called (String[] args, MessageReceivedEvent event) {
        return false;
    }

    @Override
    public void action (String[]  args, MessageReceivedEvent event) {

        List<String> list = new ArrayList<>();
        for (Role role : event.getGuild().getRoles()) {
            String name = role.getName();
            list.add(name);

        }
        List<String> emotelist = new ArrayList<>();
        for (Emote emote : event.getGuild().getEmotes()) {
            String name = emote.getName();
            emotelist.add(name);

        } try {
            Optional<TextChannel> channel = Optional.ofNullable(event.getGuild().getTextChannelsByName("log", true).get(0));
            Optional<TextChannel> channel2 = Optional.ofNullable(event.getGuild().getTextChannelsByName("beste-ki", true).get(0));
            String CCommand = "Keinen Eingestellt";
            if ( CustomCommandsHandler.getServerCommand(event.getGuild().getId()) == null) {
                CCommand = "Keinen Eingestellt";
            } else {
                CCommand = CustomCommandsHandler.getServerCommand(event.getGuild().getId()) + " | " + CustomCommandsHandler.getCommandAnswer(event.getGuild().getId());
            }

            //   event.getTextChannel().sendMessage ("Dieser Bot wurde von KeksOf (Sebl) geschrieben! Derzeit gibt es folgende Commands: -keks = Du kannst einen Keks essen. -deathbattle = Starte ein super krasses Battle gegen einen Freund etc -friendscore = Gebe einen weiteren User an und schaue wir sehr ihr Zusammen passt!  -- Version: 0.1").queue();
            event.getTextChannel().sendMessage(
                    new EmbedBuilder().setColor(Color.BLUE)
                            .setTitle(Emojis2.INFORMATION_SOURCE + "" + Emojis2.WHITE_SMALL_SQUARE + "Serverinfos von " + event.getGuild().getName())
                            .setImage(event.getGuild().getIconUrl())
                            .setDescription("Prefix auf diesem Server: `" + PrefixHandler.getUserPrefix(event.getAuthor().getId(), event.getGuild().getId()) + "`")
                            .addField("**Region:**", event.getGuild().getRegion() + "", false)
                            .addField("**Inhaber:**", event.getGuild().getOwner().getEffectiveName() + " (" + event.getGuild().getOwnerId() + ")", false)
                            .addField("**Rollen:** (" + event.getGuild().getRoles().size() + ")", String.join(",", list), false)
                            .addField("**Emotes:** (" + event.getGuild().getEmotes().size() + ")", String.join(",", emotelist), false)
                            .addField("**Standard-Rolle:**", event.getGuild().getPublicRole() + "", false)
                            .addField("**Registrierte Mitglieder:**", event.getGuild().getMembers().size() + " insgesammt.", false)
                            .addField("**Text-Channels:**", event.getGuild().getTextChannels().size() + " insgesammt.", false)
                            .addField("**Log-Channel aktiv:**", (channel.isPresent() ? "Vorhanden" : "Nicht Vorhanden"), false)
                            .addField("**Autowarn-Aktiv:**", (channel.isPresent() ? "Aktiviert!" : "Nicht Aktiviert!"), false)
                            .addField("**CustomCommand**", CCommand, false)
                            .addField("**KI-Channel vorhanden:**", (channel2.isPresent() ? "Vorhanden!" : "Nicht Vorhanden!"), false)
                            .setFooter("Diese Serverinformationen sind von dem Stand: " + new Date().toLocaleString(), event.getGuild().getIconUrl())
                            .build()).queue();
        } catch (Exception e) {
            try {
                Optional<TextChannel> channel = Optional.ofNullable(event.getGuild().getTextChannelsByName("log", true).get(0));
                String CCommand = "Keinen Eingestellt";
                if (CustomCommandsHandler.getServerCommand(event.getGuild().getId()) == null) {
                    CCommand = "Keinen Eingestellt";
                } else {
                    CCommand = CustomCommandsHandler.getServerCommand(event.getGuild().getId());
                }

                event.getTextChannel().sendMessage(

                        new EmbedBuilder().setColor(Color.BLUE)
                                .setTitle(Emojis2.INFORMATION_SOURCE + "" + Emojis2.WHITE_SMALL_SQUARE + "Serverinfos von " + event.getGuild().getName())
                                .setImage(event.getGuild().getIconUrl())
                                .setDescription("Prefix auf diesem Server: `" + PrefixHandler.getUserPrefix(event.getAuthor().getId(), event.getGuild().getId()) + "`")
                                .addField("**Region:**", event.getGuild().getRegion() + "", false)
                                .addField("**Inhaber:**", event.getGuild().getOwner().getEffectiveName() + " (" + event.getGuild().getOwnerId() + ")", false)
                                .addField("**Rollen:** (" + event.getGuild().getRoles().size() + ")", String.join(",", list), false)
                                .addField("**Emotes:** (" + event.getGuild().getEmotes().size() + ")", String.join(",", emotelist), false)
                                .addField("**Standard-Rolle:**", event.getGuild().getPublicRole() + "", false)
                                .addField("**Registrierte Mitglieder:**", event.getGuild().getMembers().size() + " insgesammt.", false)
                                .addField("**Text-Channels:**", event.getGuild().getTextChannels().size() + " insgesammt.", false)
                                .addField("**Log-Channel aktiv:**", (channel.isPresent() ? "Vorhanden" : "Nicht Vorhanden"), false)
                                .addField("**Autowarn-Aktiv:**", (channel.isPresent() ? "Aktiviert!" : "Nicht Aktiviert!"), false)
                                .addField("**CustomCommand-Eingestellt:**", CCommand, false)
                                .addField("**KI-Channel vorhanden:**", "Nicht vorhanden!", false)

                                .setFooter("Diese Serverinformationen sind von dem Stand: " + new Date().toLocaleString(), event.getGuild().getIconUrl())
                                .build()).queue();
            } catch (Exception e2) {
                Optional<TextChannel> channel2 = Optional.ofNullable(event.getGuild().getTextChannelsByName("beste-ki", true).get(0));
                String CCommand = "Keinen Eingestellt";
                if (CustomCommandsHandler.getServerCommand(event.getGuild().getId()) == null) {
                    CCommand = "Keinen Eingestellt";
                } else {
                    CCommand = CustomCommandsHandler.getServerCommand(event.getGuild().getId());
                }

                event.getTextChannel().sendMessage(

                        new EmbedBuilder().setColor(Color.BLUE)
                                .setTitle(Emojis2.INFORMATION_SOURCE + "" + Emojis2.WHITE_SMALL_SQUARE + "Serverinfos von " + event.getGuild().getName())
                                .setImage(event.getGuild().getIconUrl())
                                .setDescription("Prefix auf diesem Server: `" + PrefixHandler.getUserPrefix(event.getAuthor().getId(), event.getGuild().getId()) + "`")
                                .addField("**Region:**", event.getGuild().getRegion() + "", false)
                                .addField("**Inhaber:**", event.getGuild().getOwner().getEffectiveName() + " (" + event.getGuild().getOwnerId() + ")", false)
                                .addField("**Rollen:** (" + event.getGuild().getRoles().size() + ")", String.join(",", list), false)
                                .addField("**Emotes:** (" + event.getGuild().getEmotes().size() + ")", String.join(",", emotelist), false)
                                .addField("**Standard-Rolle:**", event.getGuild().getPublicRole() + "", false)
                                .addField("**Registrierte Mitglieder:**", event.getGuild().getMembers().size() + " insgesammt.", false)
                                .addField("**Text-Channels:**", event.getGuild().getTextChannels().size() + " insgesammt.", false)
                                .addField("**Log-Channel aktiv:**", "Nicht Vorhanden!", false)
                                .addField("**Autowarn-Aktiv:**", "Nicht aktiviert!", false)
                                .addField("**CustomCommand-Eingestellt:**", CCommand, false)
                                .addField("**KI-Channel vorhanden:**", (channel2.isPresent() ? "Vorhanden!" : "Nicht Vorhanden!"), false)

                                .setFooter("Diese Serverinformationen sind von dem Stand: " + new Date().toLocaleString(), event.getGuild().getIconUrl())
                                .build()).queue();
            }
        }




    }

    @Override
    public void executed(boolean sucess, MessageReceivedEvent event) {
        System.out.println("[Info] Der Command help wurde ausgeführt!");

    }

    @Override
    public  String  help() {

        return null;
    }

}
