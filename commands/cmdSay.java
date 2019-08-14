package commands;

import core.restHandler;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import util.Emojis2;
import core.PrefixHandler;

import java.awt.*;

public class cmdSay implements Command {
    @Override
    public boolean called (String[] args, MessageReceivedEvent event) {
        return false;
    }

    @Override
    public void action (String[]  args, MessageReceivedEvent event) {
        restHandler.setCommands(event.getGuild(), restHandler.getCommands(event.getGuild()));
        String out = "";
        for (String s : args) {
            out += s + " ";
        }
        event.getTextChannel().deleteMessageById(event.getMessageIdLong()).queue();
        if (!(args.length == 0)) {
            event.getTextChannel().sendMessage(
                    new EmbedBuilder().setColor(Color.YELLOW)
                            .setTitle(Emojis2.SMILING_FACE_WITH_OPEN_MOUTH_HAND_TIGHT + "" + Emojis2.WHITE_SMALL_SQUARE + "Ich sage...")
                            .setDescription(out)
                            .build()).queue();
        } else {
            event.getTextChannel().sendMessage(
                    new EmbedBuilder().setColor(Color.RED)
                            .setTitle("<:X_:504673235212697601>" + Emojis2.WHITE_SMALL_SQUARE + "Fehler")
                            .setDescription("Du musst angeben was ich sagen soll uwu")
                            .setFooter(PrefixHandler.getUserPrefix(event.getAuthor().getId(),event.getGuild().getId()) + "say <irgendwas>", "https://images-ext-2.discordapp.net/external/kCCNbCY9MZ1HDrC1K2XkWHxPfHlcHMjNflzy202Mq_g/https/images-ext-1.discordapp.net/external/FHVAXut353LQ9xRaxChWHL2CJYKvZWlqhUvdmS-lRPo/https/cdn.discordapp.com/avatars/349879801064194060/b0814cc2cd9a3e8a1e3d133c1aa726fe.png")
                            .build()).queue();
        }

    }

    @Override
    public void executed(boolean sucess, MessageReceivedEvent event) {
        System.out.println("[Info] Der User "+ event.getAuthor().getName() + "hat mich etwas sagen lassen und macht mich dafuer verantwortlich!");

    }

    @Override
    public  String  help() {

        return null;
    }
}
