package commands;

import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import util.Emojis;
import util.Emojis2;
import core.PrefixHandler;

import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class cmdSetServerPrefix implements Command {

    @Override
    public boolean called (String[] args, MessageReceivedEvent event) {
        return false;
    }

    @Override
    public void action (String[]  args, MessageReceivedEvent event) {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        String uhrzeit = sdf.format(new Date());
        //   event.getTextChannel().sendMessage ("Dieser Bot wurde von KeksOf (Sebl) geschrieben! Derzeit gibt es folgende Commands: -keks = Du kannst einen Keks essen. -deathbattle = Starte ein super krasses Battle gegen einen Freund etc -friendscore = Gebe einen weiteren User an und schaue wir sehr ihr Zusammen passt!  -- Version: 0.1").queue();
        if (args.length == 1) {
            if (args[0].length() <= 4) {
                if (event.getAuthor().getId().equals(event.getGuild().getOwnerId())) {
                    PrefixHandler.setServerPrefix(event.getGuild().getId(), args[0]);
                    PrefixHandler.savePrefixes();
                    event.getTextChannel().sendMessage(
                            new EmbedBuilder().setColor(Color.GREEN)
                            .setTitle(Emojis.GEAR + "" + Emojis2.WHITE_SMALL_SQUARE + "<:Erfolgreich:504672060501393418> Erfolgreich")
                            .setDescription("Der Server Prefix wurde erfolgreich in **`" + args[0] + "`** geändert.")
                            .setFooter("Ausgeführt von " + event.getAuthor().getName() + " um " + uhrzeit, event.getAuthor().getAvatarUrl())
                            .build()).queue();
                } else {
                    event.getTextChannel().sendMessage(
                            new EmbedBuilder().setColor(Color.RED)
                                    .setTitle("<:X_:504673235212697601>" + Emojis2.WHITE_SMALL_SQUARE + "Fehler")
                                    .setDescription("Den Server-Prefix kann nur der Server-Inhaber setzen.")
                                    .setFooter("Ausgeführt von " + event.getAuthor().getName() + " um " + uhrzeit, event.getAuthor().getAvatarUrl())
                                    .build()).queue();
                }
//            if (permsCore.checkPerms(event))
            //               return;
//            STATIC.PREFIX.replace(STATIC.PREFIX, args[0]);
            //           event.getTextChannel().sendMessage(
            //                   new EmbedBuilder().setColor(Color.GREEN)
            //                           .setTitle("<:Erfolgreich:504672060501393418> Erfolgreich")
            //                           .setDescription("**Prefix** wurde erfolgreich in `" + args[0] + "` geändert.")
            //                           .setFooter("Dieser Command kann Fehler beinhalten.", "https://images-ext-2.discordapp.net/external/kCCNbCY9MZ1HDrC1K2XkWHxPfHlcHMjNflzy202Mq_g/https/images-ext-1.discordapp.net/external/FHVAXut353LQ9xRaxChWHL2CJYKvZWlqhUvdmS-lRPo/https/cdn.discordapp.com/avatars/349879801064194060/b0814cc2cd9a3e8a1e3d133c1aa726fe.png")
            //                           .build()).queue();
        } else { ;
                event.getTextChannel().sendMessage(
                        new EmbedBuilder().setColor(Color.RED)
                                .setTitle("<:X_:504673235212697601>" + Emojis2.WHITE_SMALL_SQUARE + "Fehler")
                                .setDescription("`" + args[0] + "` ist zu lang für einen Prefix! (max. länge : 4)")
                                .setFooter(PrefixHandler.getUserPrefix(event.getAuthor().getId(),event.getGuild().getId()) + "setserverprefix ~Prefix~", "https://images-ext-2.discordapp.net/external/kCCNbCY9MZ1HDrC1K2XkWHxPfHlcHMjNflzy202Mq_g/https/images-ext-1.discordapp.net/external/FHVAXut353LQ9xRaxChWHL2CJYKvZWlqhUvdmS-lRPo/https/cdn.discordapp.com/avatars/349879801064194060/b0814cc2cd9a3e8a1e3d133c1aa726fe.png")
                                .build()).queue();
        }
        } else  {
            event.getTextChannel().sendMessage(
                    new EmbedBuilder().setColor(Color.RED)
                            .setTitle("<:X_:504673235212697601>" + Emojis2.WHITE_SMALL_SQUARE + "Fehler")
                            .setDescription("Du musst ein weiteres Argument angeben!")
                            .setFooter(PrefixHandler.getUserPrefix(event.getAuthor().getId(),event.getGuild().getId()) + "setserverprefix <Prefix>", "https://images-ext-2.discordapp.net/external/kCCNbCY9MZ1HDrC1K2XkWHxPfHlcHMjNflzy202Mq_g/https/images-ext-1.discordapp.net/external/FHVAXut353LQ9xRaxChWHL2CJYKvZWlqhUvdmS-lRPo/https/cdn.discordapp.com/avatars/349879801064194060/b0814cc2cd9a3e8a1e3d133c1aa726fe.png")
                            .build()).queue();
        }
    }

    @Override
    public void executed(boolean sucess, MessageReceivedEvent event) {
        System.out.println("[Info] Der Command setPrefix wurde ausgeführt!");

    }

    @Override
    public  String  help() {

        return null;
    }
}
