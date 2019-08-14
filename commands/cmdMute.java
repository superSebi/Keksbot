package commands;

import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.*;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.managers.GuildController;
import util.Emojis2;

import java.awt.*;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class cmdMute implements Command {
    private void sendErrorMsg(MessageReceivedEvent event, String content) {
        event.getTextChannel().sendMessage( new EmbedBuilder().setColor(Color.RED)
                .setDescription("<:X_:504673235212697601> | " + content)
                .build()
        ).queue();
    }


    @Override
    public boolean called(String[] args, MessageReceivedEvent event) {
        return false;
    }

    @Override
    public void action(String[] args, MessageReceivedEvent event) throws ParseException, IOException {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        String uhrzeit = sdf.format(new Date());
        Guild guild = event.getGuild();
        GuildController controller = guild.getController();

        List<Member> mentionedMembers = event.getMessage().getMentionedMembers();

        if(mentionedMembers.isEmpty()) {
            sendErrorMsg(event,"Du musst den Bösewicht pingen");
            return;
        }

        Member toMute = mentionedMembers.get(0);

        Role muteRole = guild.getRoles().stream().filter(r->r.getName().equals("Muted")).findFirst().orElse(null);

        if(muteRole == null) {
            sendErrorMsg(event, "Bitte erstelle eine Rolle mit dem Namen `Muted`");
            return;
        }

        controller.addSingleRoleToMember(toMute, muteRole).queue(success->{
            event.getTextChannel().sendMessage(
                    new EmbedBuilder().setColor(Color.GREEN)
                            .setTitle("<:Erfolgreich:504672060501393418>" + Emojis2.WHITE_SMALL_SQUARE + "Erfolgreich")
                            .setDescription("Der Spieler `" + toMute.getUser().getName() + "` wurde gemuted.")
                            .setFooter("Ausgeführt von " + event.getAuthor().getName() + " - heute um " + uhrzeit, event.getAuthor().getAvatarUrl())
                            .build()).queue();
            TextChannel channel = event.getGuild().getTextChannelsByName("log", true).get(0);
            channel.sendMessage(
                    new EmbedBuilder().setColor(Color.ORANGE) // Color.FARBE
                            .setTitle("<:confirm:509070945948925962>" + Emojis2.WHITE_SMALL_SQUARE + " MUTE")
                            .addField("Spieler", toMute.getUser().getName(), true)
                            .addField("Gekickt von", event.getAuthor().getName(), true)
                            .addField("Gekickt um", uhrzeit, true)
                            .build()
            ).queue();
            PrivateChannel pc = event.getMessage().getMentionedUsers().get(0).openPrivateChannel().complete();
            pc.sendMessage(
                    "Du wurdest auf dem Server " + event.getGuild().getName() + " von " + event.getAuthor().getAsMention() + " (" + event.getMember().getRoles().get(0).getName() + ") gemutet. \n" +

                            " \n" +
                            "Wenn du denkst das du man dich entmuten soll, kontakiere den Server-Inhaber (" + event.getGuild().getOwner().getAsMention() + ")"
            ).queue();
            event.getGuild().getController().kick(event.getGuild().getMember(event.getMessage().getMentionedUsers().get(0))).queue();
        }, error->{
            sendErrorMsg(event, "Fehler beim Mute von " + toMute.getUser().getName() + ": " + error);
        });
    }

    @Override
    public void executed(boolean sucess, MessageReceivedEvent event) {

    }

    @Override
    public String help() {
        return null;
    }
}
