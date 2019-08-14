package commands;

import core.ProfileHandler;
import core.restHandler;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.Role;
import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import util.Emojis2;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class cmdProfile implements Command {
    @Override
    public boolean called(String[] args, MessageReceivedEvent event) {
        return false;
    }

    @Override
    public void action(String[] args, MessageReceivedEvent event) {
        restHandler.setCommands(event.getGuild(), restHandler.getCommands(event.getGuild()));
        if (args.length == 0) {
            List<String> list = new ArrayList<>();
            for (Role role : event.getMember().getRoles()) {
                String name = role.getName();
                list.add(name);

            }
            event.getTextChannel().sendMessage(
                    new EmbedBuilder().setColor(Color.WHITE)
                            .setTitle(Emojis2.CLIP_BOARD + "" + Emojis2.WHITE_SMALL_SQUARE + "Profil")
                            .setDescription("Von " + "**dir selber**")
                            .addField("**Name:**", event.getAuthor().getName() + "", false)
                            .addField("**ID:**", event.getAuthor().getId() + "", false)
                            .addField("**Account erstellt am:**", event.getAuthor().getCreationTime().toLocalDate() + "", false)
                            .addField("**Status:**", event.getMember().getOnlineStatus().name() + "", false)
                            .addField("**Gejoint am:**", event.getMember().getJoinDate().toLocalDate() + "", false)
                            .addField("**Rollen:** (" + event.getMember().getRoles().size() + ")", String.join(",", list), false)
                            .addField("**Beschreibung:**", ProfileHandler.getUserDesc(event.getAuthor().getId(), event.getGuild().getId()), false)
                            .addField("**Switch-Freundescode:**", ProfileHandler.getUserCode(event.getAuthor().getId()), false)
                            .setFooter("<- Avatar von dir selber", event.getAuthor().getAvatarUrl())
                            .build())
                    .queue();

        } if (args.length > 0){
            for (User pinged : event.getMessage().getMentionedUsers()) {
                    event.getTextChannel().sendMessage(
                            new EmbedBuilder().setColor(Color.WHITE)
                                    .setTitle(Emojis2.CLIP_BOARD + "" + Emojis2.WHITE_SMALL_SQUARE + "Profil")
                                    .setDescription("Von **" + pinged.getName() + "**")
                                    .addField("**Name:** ", pinged.getName() + "", false)
                                    .addField("**ID:** ", pinged.getId(), false)
                                    .addField("**Account erstellt am:**", pinged.getCreationTime().toLocalDate() + "", false)
                                    .addField("**Status:**", pinged.getJDA().getStatus().name().toString() + "", false)
                                    .addField("**Beschreibung:**", ProfileHandler.getUserDesc(pinged.getId(), event.getGuild().getId()), false)
                                    .addField("**Switch-Freundescode:**", ProfileHandler.getUserCode(pinged.getId()), false)
                                    .setFooter("<- Avatar von " + pinged.getName(), pinged.getAvatarUrl())
                                    .build()
                    ).queue();
                }
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
