package listeners;

import core.CommandHandler;
import core.CustomCommandsHandler;
import core.PrefixHandler;
import core.commandParser;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;
import util.Emojis2;

import java.awt.*;
import java.io.IOException;
import java.text.ParseException;

public class commandListener extends ListenerAdapter {

    public void onMessageReceived(MessageReceivedEvent event) {

        try {
            if (event.getMessage().getContentDisplay().startsWith(PrefixHandler.getUserPrefix(event.getAuthor().getId(), event.getGuild().getId())) && event.getMessage().getAuthor().getId() != event.getJDA().getSelfUser().getId()) {
                try {
                    CommandHandler.handleCommand(commandParser.parser(event.getMessage().getContentDisplay(), event));
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ParseException e) {
                    e.printStackTrace();
                }

            }
        } catch (Exception e) {

        }

        //       if (event.getJDA() != null && event.getJDA().getGuilds() != null) {
        //         Optional<Member> member = event.getJDA().getGuilds().stream().flatMap(g -> g.getMembers().stream().filter(m -> m.getNickname() != null && m.getNickname().equals(event.getMessage().getContentDisplay().replace("@", "")))).findFirst();
        //         if (member.isPresent()) {
        //            event.getTextChannel().sendMessage(
        //                     new EmbedBuilder().setColor(Color.ORANGE) // Color.FARBE
        //                             .setTitle(Emojis2.INFORMATION_SOURCE + "" + Emojis2.WHITE_SMALL_SQUARE + "Allgemeine Informationen")
        //                             .setDescription("Sachen zu vergessen ist normal! Hier ein paar allgemeine Infos :)")
        //                             .addField("Prefix", PrefixHandler.getUserPrefix(event.getAuthor().getId(), event.getGuild().getId()), true)
        //                             .addField("Alle Befehle", PrefixHandler.getUserPrefix(event.getAuthor().getId(), event.getGuild().getId()) + "help", true)
        //                             .addField("Allgemeine Infos (ohne Prefix)", "infos", true)
        //                             .build()
        //             ).queue();
        //      }
        try {
            if (event.getMessage().getContentDisplay().startsWith("@" + event.getGuild().getSelfMember().getEffectiveName())) {
                event.getTextChannel().sendMessage(
                        new EmbedBuilder().setColor(Color.ORANGE) // Color.FARBE
                                .setTitle(Emojis2.INFORMATION_SOURCE + "" + Emojis2.WHITE_SMALL_SQUARE + "Allgemeine Informationen")
                                .setDescription("Sachen zu vergessen ist normal! Hier ein paar allgemeine Infos :)")
                                .addField("Prefix", PrefixHandler.getUserPrefix(event.getAuthor().getId(), event.getGuild().getId()), true)
                                .addField("Alle Befehle", PrefixHandler.getUserPrefix(event.getAuthor().getId(), event.getGuild().getId()) + "help", true)
                                .addField("Allgemeine Infos (ohne Prefix)", "@Bester_Bot", true)
                                .build()
                ).queue();
            }
            try {
                if (event.getMessage().getContentDisplay().startsWith(CustomCommandsHandler.getServerCommand(event.getGuild().getId()))) {
                    event.getTextChannel().sendMessage(CustomCommandsHandler.getCommandAnswer(event.getGuild().getId())).queue();
                }
            } catch (Exception e) {

            }
        } catch (Exception e) {

        }
    }
}
