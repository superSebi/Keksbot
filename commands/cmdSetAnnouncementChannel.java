package commands;

import core.ChannelHandler;
import core.restHandler;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.Permission;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import util.Emojis2;
import core.PrefixHandler;

import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class cmdSetAnnouncementChannel implements Command {
    @Override
    public boolean called(String[] args, MessageReceivedEvent event) {
        return false;
    }

    @Override
    public void action(String[] args, MessageReceivedEvent event) {
        restHandler.setCommands(event.getGuild(), restHandler.getCommands(event.getGuild()));
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        String uhrzeit = sdf.format(new Date());
            if (event.getMember().hasPermission(Permission.MANAGE_CHANNEL)) {
                if (args.length == 1) {
                    ChannelHandler.setChannel(event.getGuild().getId(), event.getMessage().getMentionedChannels().get(0).getId());
                    ChannelHandler.saveChannels();
                    event.getTextChannel().sendMessage(
                            new EmbedBuilder().setColor(Color.GREEN)
                                    .setTitle(Emojis2.PUBLIC_ADDRESS_LOUDSPEAKER + "" + Emojis2.WHITE_SMALL_SQUARE + "<:Erfolgreich:504672060501393418> Erfolgreich")
                                    .setDescription("Der Mitteilungschannel wurde in **`" + args[0] + "`** geändert.")
                                    .setFooter("Ausgeführt von " + event.getAuthor().getName() + " um " + uhrzeit, event.getAuthor().getAvatarUrl())
                                    .build()).queue();
                } else {
                    event.getTextChannel().sendMessage(
                            new EmbedBuilder().setColor(Color.RED)
                                    .setTitle("<:X_:504673235212697601> " + Emojis2.WHITE_SMALL_SQUARE + " Fehler")
                                    .setDescription("Du musst den Channel, den du als Ankündigungschannel haben willst, pingen (#channel)")
                                    .setFooter(PrefixHandler.getUserPrefix(event.getAuthor().getId(), event.getGuild().getId()) + "setchannel ~#ChannelName~", "https://images-ext-2.discordapp.net/external/kCCNbCY9MZ1HDrC1K2XkWHxPfHlcHMjNflzy202Mq_g/https/images-ext-1.discordapp.net/external/FHVAXut353LQ9xRaxChWHL2CJYKvZWlqhUvdmS-lRPo/https/cdn.discordapp.com/avatars/349879801064194060/b0814cc2cd9a3e8a1e3d133c1aa726fe.png")
                                    .build()).queue();
                }
            } else {
                event.getTextChannel().sendMessage(
                        new EmbedBuilder().setColor(Color.RED)
                                .setTitle("<:X_:504673235212697601> " + Emojis2.WHITE_SMALL_SQUARE + " Fehler")
                                .setDescription("Du benötigst die `MANAGE_CHANNEL` Berechtigung!")
                                .setFooter(PrefixHandler.getUserPrefix(event.getAuthor().getId(), event.getGuild().getId()) + "setchannel ~#ChannelName~", "https://images-ext-2.discordapp.net/external/kCCNbCY9MZ1HDrC1K2XkWHxPfHlcHMjNflzy202Mq_g/https/images-ext-1.discordapp.net/external/FHVAXut353LQ9xRaxChWHL2CJYKvZWlqhUvdmS-lRPo/https/cdn.discordapp.com/avatars/349879801064194060/b0814cc2cd9a3e8a1e3d133c1aa726fe.png")
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
