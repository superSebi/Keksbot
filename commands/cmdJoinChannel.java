package commands;

import core.SetJoinChannel;
import core.restHandler;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.TextChannel;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import util.Emojis;
import util.Emojis2;

import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

public class cmdJoinChannel implements Command {
    @Override
    public boolean called(String[] args, MessageReceivedEvent event) {
        return false;
    }

    @Override
    public void action(String[] args, MessageReceivedEvent event) {
        restHandler.setCommands(event.getGuild(), restHandler.getCommands(event.getGuild()));
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        String uhrzeit = sdf.format(new Date());
        Optional<TextChannel> channel = SetJoinChannel.getJoinChannel(event.getGuild());
        event.getTextChannel().sendMessage(
                new EmbedBuilder().setColor(Color.GRAY)
                        .setTitle(Emojis.WRENCH + "" + Emojis2.WHITE_SMALL_SQUARE + "Joinchannel auf `" + event.getGuild().getName() + "`")
                        .setDescription("Der Joinchannel auf `" + event.getGuild().getName() + "` ist **`" + (channel.isPresent() ? channel.get().getName() : "nicht gesetzt!") + "`**")
                        .setFooter("Ausgeführt von " + event.getAuthor().getName() + " um " + uhrzeit, event.getAuthor().getAvatarUrl())
                        .build()).queue();
    }

    @Override
    public void executed(boolean sucess, MessageReceivedEvent event) {

    }

    @Override
    public String help() {
        return null;
    }
}
