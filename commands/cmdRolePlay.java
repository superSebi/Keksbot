package commands;

import core.PrefixHandler;
import core.RandomInt;
import core.restHandler;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import util.Emojis2;
import util.Pictures;

import java.awt.*;
import java.io.IOException;
import java.text.ParseException;
import java.util.Arrays;

public class cmdRolePlay implements Command {
    @Override
    public boolean called(String[] args, MessageReceivedEvent event) {
        return false;
    }

    @Override
    public void action(String[] args, MessageReceivedEvent event) throws ParseException, IOException {
        restHandler.setCommands(event.getGuild(), restHandler.getCommands(event.getGuild()));
        String bild = String.format("%s", Pictures.values()[RandomInt.random(Pictures.values().length)].toString().toLowerCase());
        String msg = "";
        msg = String.join(" ", Arrays.asList(args).subList(1, args.length));
        if (!(args.length == 0)) {
            switch (args[0].toLowerCase()) {

                case "kiss":
                    try {
                        event.getTextChannel().sendMessage(
                                new EmbedBuilder().setColor(Color.YELLOW)
                                        .setTitle(Emojis2.SMILING_FACE_WITH_OPEN_MOUTH_HAND_TIGHT + "" + Emojis2.WHITE_SMALL_SQUARE + "<:Erfolgreich:504672060501393418> Erfolgreich")
                                        .setDescription("**" + msg  + "**, du wurdest von **" + event.getAuthor().getName()+ "** geküsst.")
                                        .setImage("http://fanaru.com/anime-boys/image/184051-anime-boys-kawaii-kiss.gif")
                                        .build()
                        ).queue();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case "hug":
                    try {
                        event.getTextChannel().sendMessage(
                                new EmbedBuilder().setColor(Color.YELLOW)
                                        .setTitle(Emojis2.SMILING_FACE_WITH_OPEN_MOUTH_HAND_TIGHT + "" + Emojis2.WHITE_SMALL_SQUARE + "<:Erfolgreich:504672060501393418> Erfolgreich")
                                        .setDescription("**" + msg   + "**, du wurdest von **" + event.getAuthor().getName()+ "** umarmt.")
                                        .setImage("https://media.giphy.com/media/l2QDM9Jnim1YVILXa/giphy.gif")
                                        .build()
                        ).queue();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case "fuck":
                    try {
                        event.getTextChannel().sendMessage(
                                new EmbedBuilder().setColor(Color.YELLOW)
                                        .setTitle(Emojis2.SMILING_FACE_WITH_OPEN_MOUTH_HAND_TIGHT + "" + Emojis2.WHITE_SMALL_SQUARE + "<:Erfolgreich:504672060501393418> Erfolgreich")
                                        .setDescription("**" + msg + "**, du wurdest von **" + event.getAuthor().getName()+ "** gefic*t.")
                                        .setImage("https://media1.tenor.com/images/fcad94cf1687b68c411263c0c2c29d31/tenor.gif?itemid=5011520")
                                        .build()
                        ).queue();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case "wink":
                    try {
                        event.getTextChannel().sendMessage(
                                new EmbedBuilder().setColor(Color.YELLOW)
                                        .setTitle(Emojis2.SMILING_FACE_WITH_OPEN_MOUTH_HAND_TIGHT + "" + Emojis2.WHITE_SMALL_SQUARE + "<:Erfolgreich:504672060501393418> Erfolgreich")
                                        .setDescription("**" +  msg + "**, **" + event.getAuthor().getName() + "** winkt dir zu.")
                                        .setImage("https://media1.tenor.com/images/a251caa1a2f4ca8db9da1ec9dfd95c2b/tenor.gif?itemid=13358680")
                                        .build()
                        ).queue();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case "kill":
                    try {
                        event.getTextChannel().sendMessage(
                                new EmbedBuilder().setColor(Color.YELLOW)
                                        .setTitle(Emojis2.SMILING_FACE_WITH_OPEN_MOUTH_HAND_TIGHT + "" + Emojis2.WHITE_SMALL_SQUARE + "<:Erfolgreich:504672060501393418> Erfolgreich")
                                        .setDescription("**" + msg  + "**,**" + event.getAuthor().getName()+ "** hat versucht dich zu töten! \n Zum weiteren Kampf `/deathbattle`.")
                                        .setImage("https://media0.giphy.com/media/yGZnLLLmHVEB2/source.gif")
                                        .build()
                        ).queue();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
            }
        } else {
            event.getTextChannel().sendMessage(
                    new EmbedBuilder().setColor(Color.RED)
                            .setTitle("<:X_:504673235212697601> " + Emojis2.WHITE_SMALL_SQUARE + " Fehler")
                            .setDescription("Du hast alles richtig gemacht aber das Gif, das ich zeigen wollte, ist nicht mehr verfügbar. \n Bitte versuche es doch erneut :/")
                            .setFooter(PrefixHandler.getUserPrefix(event.getAuthor().getId(),event.getGuild().getId()) + "roleplay <subcmd>", "https://images-ext-2.discordapp.net/external/kCCNbCY9MZ1HDrC1K2XkWHxPfHlcHMjNflzy202Mq_g/https/images-ext-1.discordapp.net/external/FHVAXut353LQ9xRaxChWHL2CJYKvZWlqhUvdmS-lRPo/https/cdn.discordapp.com/avatars/349879801064194060/b0814cc2cd9a3e8a1e3d133c1aa726fe.png")
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
