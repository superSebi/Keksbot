package commands;

import core.restHandler;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.Role;
import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import util.Emojis2;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class cmdAvatar implements Command {
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
                            .setTitle(Emojis2.INFORMATION_SOURCE + "" + Emojis2.WHITE_SMALL_SQUARE + " Avatar")
                            .setDescription("Von " + "**dir selber**")
                            .setImage(event.getAuthor().getAvatarUrl())
                            .build())
                    .queue();

        } if (args.length > 0){
            for (User pinged : event.getMessage().getMentionedUsers()) {
                event.getTextChannel().sendMessage(
                        new EmbedBuilder().setColor(Color.WHITE)
                                .setTitle(Emojis2.INFORMATION_SOURCE + "" + Emojis2.WHITE_SMALL_SQUARE + " Avatar")
                                .setDescription("Von " + "**" + pinged.getName() + "**")
                                .setImage(pinged.getAvatarUrl())
                                .build())
                        .queue();
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
