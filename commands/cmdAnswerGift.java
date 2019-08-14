package commands;

import core.RandomInt;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import util.Emojis2;
import util.Gifts;
import core.PrefixHandler;

import java.awt.*;

public class cmdAnswerGift implements Command {
    @Override
    public boolean called(String[] args, MessageReceivedEvent event) {
        return false;
    }

    @Override
    public void action(String[] args, MessageReceivedEvent event) {
        String gift = String.format("%s", Gifts.values()[RandomInt.random(Gifts.values().length)].toString().toLowerCase());
            event.getTextChannel().deleteMessageById(event.getMessageId()).queue();
            event.getTextChannel().sendMessage(
                    new EmbedBuilder().setColor(Color.RED)
                            .setTitle("<:X_:504673235212697601> " + Emojis2.WHITE_SMALL_SQUARE + " Fehler")
                            .setDescription("Du hast alles richtig gemacht aber das Geschenk, dass ich öffnen wollte, war leer!. \n Bitte versuche es doch erneut :/")
                            .setFooter(PrefixHandler.getUserPrefix(event.getAuthor().getId(),event.getGuild().getId()) + "weihnachts-event gift-open", "https://images-ext-2.discordapp.net/external/kCCNbCY9MZ1HDrC1K2XkWHxPfHlcHMjNflzy202Mq_g/https/images-ext-1.discordapp.net/external/FHVAXut353LQ9xRaxChWHL2CJYKvZWlqhUvdmS-lRPo/https/cdn.discordapp.com/avatars/349879801064194060/b0814cc2cd9a3e8a1e3d133c1aa726fe.png")
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
