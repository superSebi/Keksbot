package commands;

import core.restHandler;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import util.Emojis2;

import java.awt.*;

public class cmdKeks implements Command {

    @Override
    public boolean called (String[] args, MessageReceivedEvent event) {
        return false;
    }

    @Override
    public void action (String[]  args, MessageReceivedEvent event) {
        restHandler.setCommands(event.getGuild(), restHandler.getCommands(event.getGuild()));
        if (event.getMessage().getMentionedUsers().get(0) == null) {
            event.getTextChannel().sendMessage(
                    new EmbedBuilder().setColor(Color.YELLOW)
                            .setTitle(Emojis2.SMILING_FACE_WITH_OPEN_MOUTH_HAND_TIGHT + "" + Emojis2.WHITE_SMALL_SQUARE + "Keks")
                            .setDescription("**Keks** wird gebacken...")
                            .addField("<:Erfolgreich:504672060501393418> Erfolgreich", "Hier iss! :cookie:", false)
                            .setFooter("Lass es dir schmecken :)", "https://images-ext-2.discordapp.net/external/kCCNbCY9MZ1HDrC1K2XkWHxPfHlcHMjNflzy202Mq_g/https/images-ext-1.discordapp.net/external/FHVAXut353LQ9xRaxChWHL2CJYKvZWlqhUvdmS-lRPo/https/cdn.discordapp.com/avatars/349879801064194060/b0814cc2cd9a3e8a1e3d133c1aa726fe.png")
                            .build()).queue();
        } else {
            event.getTextChannel().sendMessage(
                    new EmbedBuilder().setColor(Color.YELLOW)
                            .setTitle(Emojis2.SMILING_FACE_WITH_OPEN_MOUTH_HAND_TIGHT + "" + Emojis2.WHITE_SMALL_SQUARE + "Keks")
                            .setDescription(event.getAuthor().getName() + " backt für " + event.getMessage().getMentionedUsers().get(0).getName() + " einen Keks")
                            .addField("<:Erfolgreich:504672060501393418> Erfolgreich", event.getAuthor().getName() + ": Hier iss! :cookie: \n " + event.getMessage().getMentionedUsers().get(0).getName() + ": Danke... *lecker*" , false)
                            .setFooter("Lass es dir schmecken :)", "https://images-ext-2.discordapp.net/external/kCCNbCY9MZ1HDrC1K2XkWHxPfHlcHMjNflzy202Mq_g/https/images-ext-1.discordapp.net/external/FHVAXut353LQ9xRaxChWHL2CJYKvZWlqhUvdmS-lRPo/https/cdn.discordapp.com/avatars/349879801064194060/b0814cc2cd9a3e8a1e3d133c1aa726fe.png")
                            .build()).queue();
        }

    }

    @Override
    public void executed(boolean sucess, MessageReceivedEvent event) {
        System.out.println("[Info] Der Command Keks wurde ausgeführt!");

    }

    @Override
    public  String  help() {

        return null;
    }



}

