package commands;

import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class cmdAnnouncement implements Command {
    @Override
    public boolean called(String[] args, MessageReceivedEvent event) {
        return false;
    }

    @Override
    public void action(String[] args, MessageReceivedEvent event) {
        event.getTextChannel().sendMessage("404 | Dieser Command wurde gelöscht!").queue();
   /*     String out = "";
        for (String s : args) {
            out += s + " ";
        }
        event.getTextChannel().deleteMessageById(event.getMessageIdLong()).queue();
        if (!(args.length == 0)) {
            if (event.getMember().hasPermission(Permission.ADMINISTRATOR)) {
                Optional<TextChannel> channel = ChannelHandler.getChannel(event.getGuild());
                String finalOut = out;
                event.getTextChannel().sendMessage(
                        new EmbedBuilder().setColor(Color.GRAY)
                                .setTitle(Emojis2.PUBLIC_ADDRESS_LOUDSPEAKER + "" + Emojis2.WHITE_SMALL_SQUARE + "<:Erfolgreich:504672060501393418>Erfolgreich gesendet!")
                                .setDescription("Die Mitteilung **`(" + out + ")`** wurde erfolgreich in `" + (channel.isPresent() ? channel.get().getName() : "nicht gesetzt!") + "` gesendet!")
                                .build()).queue();
                channel.ifPresent(textChannel -> textChannel.sendMessage(
                        new EmbedBuilder().setColor(Color.GRAY)
                                .setTitle(Emojis2.PUBLIC_ADDRESS_LOUDSPEAKER + "" + Emojis2.WHITE_SMALL_SQUARE + "Mitteilung/Ankündigung!")
                                .setDescription(finalOut)
                                .build()).queue());
            } else {
                event.getTextChannel().sendMessage(
                        new EmbedBuilder().setColor(Color.RED)
                                .setTitle("<:X_:504673235212697601>" + Emojis2.WHITE_SMALL_SQUARE + "Fehler")
                                .setDescription("Um **Ankündigungen** machen zu können, fehlt dir die `ADMINISTRATOR` Berechtigung!")
                                .setFooter(PrefixHandler.getUserPrefix(event.getAuthor().getId(), event.getGuild().getId()) + "announcement <ankündigung/mitteilung>", "https://images-ext-2.discordapp.net/external/kCCNbCY9MZ1HDrC1K2XkWHxPfHlcHMjNflzy202Mq_g/https/images-ext-1.discordapp.net/external/FHVAXut353LQ9xRaxChWHL2CJYKvZWlqhUvdmS-lRPo/https/cdn.discordapp.com/avatars/349879801064194060/b0814cc2cd9a3e8a1e3d133c1aa726fe.png")
                                .build()).queue();
            }
            } else {
                event.getTextChannel().sendMessage(
                        new EmbedBuilder().setColor(Color.RED)
                                .setTitle("<:X_:504673235212697601>" + Emojis2.WHITE_SMALL_SQUARE + "Fehler")
                                .setDescription("Du musst angeben was angekündigt werden soll!")
                                .setFooter(PrefixHandler.getUserPrefix(event.getAuthor().getId(), event.getGuild().getId()) + "announcement <ankündigung/mitteilung>", "https://images-ext-2.discordapp.net/external/kCCNbCY9MZ1HDrC1K2XkWHxPfHlcHMjNflzy202Mq_g/https/images-ext-1.discordapp.net/external/FHVAXut353LQ9xRaxChWHL2CJYKvZWlqhUvdmS-lRPo/https/cdn.discordapp.com/avatars/349879801064194060/b0814cc2cd9a3e8a1e3d133c1aa726fe.png")
                                .build()).queue();
        }*/
    }

    @Override
    public void executed(boolean sucess, MessageReceivedEvent event) {

    }

    @Override
    public String help() {
        return null;
    }
}
