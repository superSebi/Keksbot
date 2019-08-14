package commands;

import core.ProfileHandler;
import core.restHandler;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import util.Emojis2;
import core.PrefixHandler;

import java.awt.*;

public class cmdSetFriendCode implements Command {
    @Override
    public boolean called(String[] args, MessageReceivedEvent event) {
        return false;
    }

    @Override
    public void action(String[] args, MessageReceivedEvent event) {
        restHandler.setCommands(event.getGuild(), restHandler.getCommands(event.getGuild()));
        String userid = event.getAuthor().getId();
        String freundescode = args[0];
        ProfileHandler.setUserCode(userid, freundescode);
        if (args.length > 0) {
            event.getTextChannel().sendMessage(
                    new EmbedBuilder().setColor(Color.BLUE)
                            .setTitle(Emojis2.CLIP_BOARD + "" + Emojis2.WHITE_SMALL_SQUARE + "<:Erfolgreich:504672060501393418> Erfolgreich")
                            .addField("**Gesetzter Freundescode:**", freundescode, false)
                            .build())
                    .queue();
        } else {
            event.getTextChannel().sendMessage(
                    new EmbedBuilder().setColor(Color.RED)
                            .setTitle("<:X_:504673235212697601> " + Emojis2.WHITE_SMALL_SQUARE + " Fehler")
                            .setDescription("Du musst deinen Freundescode angeben!")
                            .setFooter(PrefixHandler.getUserPrefix(event.getAuthor().getId(), event.getGuild().getId()) + "setfriendcode ~Freundescode~", event.getJDA().getSelfUser().getAvatarUrl())
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
