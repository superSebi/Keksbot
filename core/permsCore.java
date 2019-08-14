package core;

import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.entities.Role;
import util.Emojis2;
import util.STATIC;

import java.awt.*;
import java.util.Arrays;

public class permsCore {

    public static boolean checkPerms(MessageReceivedEvent event) {

        for ( Role r : event.getGuild().getMember(event.getAuthor()).getRoles() ) {

            if (Arrays.stream(STATIC.PERMS).parallel().anyMatch(r.getName()::contains))
                return false;
            else
                event.getTextChannel().sendMessage(
                        new EmbedBuilder().setColor(Color.RED)
                                .setTitle("<:X_:504673235212697601>" + Emojis2.WHITE_SMALL_SQUARE + " Fehler")
                                .setDescription("Du hast nicht die benötigten Berechtigungen. (" + STATIC.PERMS + ")")
                                .setFooter("Bei einem Fehler melde dich bei dem Serverinhaber!", "https://images-ext-2.discordapp.net/external/kCCNbCY9MZ1HDrC1K2XkWHxPfHlcHMjNflzy202Mq_g/https/images-ext-1.discordapp.net/external/FHVAXut353LQ9xRaxChWHL2CJYKvZWlqhUvdmS-lRPo/https/cdn.discordapp.com/avatars/349879801064194060/b0814cc2cd9a3e8a1e3d133c1aa726fe.png")
                                .build()).queue();
        }
         return true;
        }
    }

