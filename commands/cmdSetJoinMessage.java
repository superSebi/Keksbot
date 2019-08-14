package commands;

import core.JoinMessageHandler;
import core.restHandler;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.Permission;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import util.Emojis2;
import core.PrefixHandler;

import java.awt.*;
;

public class cmdSetJoinMessage implements Command {

    @Override
    public boolean called(String[] args, MessageReceivedEvent event) {
        return false;
    }

    @Override
    public void action(String[] args, MessageReceivedEvent event) {
        restHandler.setCommands(event.getGuild(), restHandler.getCommands(event.getGuild()));
        String joinmessage = "";
        for (String s : args) {
            joinmessage += s + " ";
        }

            if (event.getMember().hasPermission(Permission.MANAGE_SERVER)) {
                if (args.length > 0) {
                    String serverid = event.getGuild().getId();JoinMessageHandler.setJoinMessage(serverid, joinmessage);
                    event.getTextChannel().sendMessage(
                            new EmbedBuilder().setColor(Color.BLUE)
                                    .setTitle(Emojis2.GEAR + "" + Emojis2.WHITE_SMALL_SQUARE + "<:Erfolgreich:504672060501393418> Erfolgreich")
                                    .addField("**Gesetzte Join-Message:**", joinmessage, false)
                                    .build())
                            .queue();
                    JoinMessageHandler.saveJoinMessages();
                } else {
                    event.getTextChannel().sendMessage(
                            new EmbedBuilder().setColor(Color.RED)
                                    .setTitle(Emojis2.GEAR + "" + Emojis2.WHITE_SMALL_SQUARE + " <:X_:504673235212697601> Fehler")
                                    .setDescription("Der Befehl `/setjoinmessage` muss Argumente beinhalten \n `/setjoinmessage <joinmessage>` \n `%USER%` schreibst du wenn du den Namen des Users bekommen willst \n `%NUMBER%` kann genutzt werden wenn du die neue Mitglieder Anzahl bekommen willst \n Mit `%NEWLINE%` machst du eine neue Zeile \n Mit `%ROLES%` bekommst du die Rollen des Users")
                                    .setFooter(PrefixHandler.getUserPrefix(event.getAuthor().getId(), event.getGuild().getId()) + "setjoinmessage ~joinmessage~", "https://images-ext-2.discordapp.net/external/kCCNbCY9MZ1HDrC1K2XkWHxPfHlcHMjNflzy202Mq_g/https/images-ext-1.discordapp.net/external/FHVAXut353LQ9xRaxChWHL2CJYKvZWlqhUvdmS-lRPo/https/cdn.discordapp.com/avatars/349879801064194060/b0814cc2cd9a3e8a1e3d133c1aa726fe.png")
                                    .build()).queue();
                }

            } else {
                event.getTextChannel().sendMessage(
                        new EmbedBuilder().setColor(Color.RED)
                                .setTitle(Emojis2.GEAR + "" + Emojis2.WHITE_SMALL_SQUARE + " <:X_:504673235212697601> Fehler")
                                .setDescription("Dir fehlt die `MANAGE_SERVER` Permission!")
                                .setFooter(PrefixHandler.getUserPrefix(event.getAuthor().getId(), event.getGuild().getId()) + "setjoinmessage ~joinmessage~", "https://images-ext-2.discordapp.net/external/kCCNbCY9MZ1HDrC1K2XkWHxPfHlcHMjNflzy202Mq_g/https/images-ext-1.discordapp.net/external/FHVAXut353LQ9xRaxChWHL2CJYKvZWlqhUvdmS-lRPo/https/cdn.discordapp.com/avatars/349879801064194060/b0814cc2cd9a3e8a1e3d133c1aa726fe.png")
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
