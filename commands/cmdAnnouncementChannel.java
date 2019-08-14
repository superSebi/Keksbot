package commands;

import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class cmdAnnouncementChannel implements Command {
    @Override
    public boolean called(String[] args, MessageReceivedEvent event) {
        return false;
    }

    @Override
    public void action(String[] args, MessageReceivedEvent event) {
        event.getTextChannel().sendMessage("404 | Dieser Command wurde gelöscht!").queue();
        /*SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        String uhrzeit = sdf.format(new Date());
        Optional<TextChannel> channel = ChannelHandler.getChannel(event.getGuild());
        event.getTextChannel().sendMessage(
                new EmbedBuilder().setColor(Color.GRAY)
                        .setTitle(Emojis2.PUBLIC_ADDRESS_LOUDSPEAKER + "" + Emojis2.WHITE_SMALL_SQUARE + "Ankündigungschannel auf `" + event.getGuild().getName() + "`")
                        .setDescription("Der Ankündigungschannel auf `" + event.getGuild().getName() + "` ist **`" + (channel.isPresent() ? channel.get().getName() : "nicht gesetzt!") + "`**")
                        .setFooter("Ausgeführt von " + event.getAuthor().getName() + " um " + uhrzeit, event.getAuthor().getAvatarUrl())
                        .build()).queue();*/
    }

    @Override
    public void executed(boolean sucess, MessageReceivedEvent event) {

    }

    @Override
    public String help() {
        return null;
    }
}
