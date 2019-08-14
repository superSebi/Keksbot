package commands;

import core.JoinRoleHandler;
import core.PrefixHandler;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.Permission;
import net.dv8tion.jda.core.entities.Role;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import util.Emojis2;

import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class cmdSetJoinRole implements Command {
    @Override
    public boolean called(String[] args, MessageReceivedEvent event) {
        return false;
    }

    @Override
    public void action(String[] args, MessageReceivedEvent event) {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        String uhrzeit = sdf.format(new Date());
        Role idRole = event.getGuild().getRoleById(args[0]);
        if (event.getMember().hasPermission(Permission.MANAGE_SERVER)) {
            if (idRole == null) {
                Role mentionedRole = event.getMessage().getMentionedRoles().get(0);
                if (mentionedRole == null) {
                    System.out.println("Fehler");
                    return;
                }
                JoinRoleHandler.setRole(event.getGuild().getId(), mentionedRole.getId());
                JoinRoleHandler.saveRoles();
                event.getTextChannel().sendMessage(
                        new EmbedBuilder().setColor(Color.GREEN)
                                .setTitle(Emojis2.GEAR + "" + Emojis2.WHITE_SMALL_SQUARE + "<:Erfolgreich:504672060501393418> Erfolgreich")
                                .setDescription("Die Join-Role wurde in **`" + mentionedRole.getName() + "`** geändert.")
                                .setFooter("Ausgeführt von " + event.getAuthor().getName() + " um " + uhrzeit, event.getAuthor().getAvatarUrl())
                                .build()).queue();
                System.out.println(event.getAuthor().getName() + " hat die JoinRole auf " + event.getGuild().getName() + " in " + JoinRoleHandler.getRole(event.getGuild()).get().getName() + "geändert");
            } else {
                JoinRoleHandler.setRole(event.getGuild().getId(), idRole.getId());
                JoinRoleHandler.saveRoles();
                event.getTextChannel().sendMessage(
                        new EmbedBuilder().setColor(Color.GREEN)
                                .setTitle(Emojis2.GEAR + "" + Emojis2.WHITE_SMALL_SQUARE + "<:Erfolgreich:504672060501393418> Erfolgreich")
                                .setDescription("Die Join-Role wurde in **`" + idRole.getName() + "`** geändert.")
                                .setFooter("Ausgeführt von " + event.getAuthor().getName() + " um " + uhrzeit, event.getAuthor().getAvatarUrl())
                                .build()).queue();
                System.out.println(event.getAuthor().getName() + " hat die JoinRole auf " + event.getGuild().getName() + " in " + JoinRoleHandler.getRole(event.getGuild()).get().getName() + "geändert");
            }
        } else {
            event.getTextChannel().sendMessage(
                    new EmbedBuilder().setColor(Color.RED)
                            .setTitle("<:X_:504673235212697601> " + Emojis2.WHITE_SMALL_SQUARE + " Fehler")
                            .setDescription("Du benötigst die `MANAGE_SERVER` Berechtigung!")
                            .setFooter(PrefixHandler.getUserPrefix(event.getAuthor().getId(), event.getGuild().getId()) + "setjoinrole ~@role~", "https://images-ext-2.discordapp.net/external/kCCNbCY9MZ1HDrC1K2XkWHxPfHlcHMjNflzy202Mq_g/https/images-ext-1.discordapp.net/external/FHVAXut353LQ9xRaxChWHL2CJYKvZWlqhUvdmS-lRPo/https/cdn.discordapp.com/avatars/349879801064194060/b0814cc2cd9a3e8a1e3d133c1aa726fe.png")
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
