package commands;

import core.restHandler;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

import java.awt.*;

public class cmdRegister implements Command {
    @Override
    public boolean called(String[] args, MessageReceivedEvent event) {
        return false;
    }

    @Override
    public void action(String[] args, MessageReceivedEvent event) {
        restHandler.setCommands(event.getGuild(), restHandler.getCommands(event.getGuild()));
        if (event.getJDA().getGuildById("464181921530773516").getId().equals(event.getGuild().getId())) {
            event.getJDA().getGuildById("464181921530773516").getTextChannelById("484712949152350210").sendMessage(
                        new EmbedBuilder().setColor(Color.GREEN)
                                .setTitle("Willkommen auf dem keksigsten Server der Milchstraße!")
                                .setDescription("Ich werde Ihnen nun ein paar Fragen stellen! \n Bist single oder vergeben?\n" +
                                        "Bist du fett oder dünn?\n" +
                                        "Folgst du KeksOfs auf twitter und / oder hast KeksOfs abbonniert?\n" +
                                        "Welche Ping Rolle willst du? -> LiveStream-Ping,Video-Ping und/oder News-Ping \n" +
                                        "Und welche Farbe? -> !farben")
                                .setFooter("Viel Spaß auf dem Server :D", event.getAuthor().getAvatarUrl()).build()
                ).queue();
        } else {
            event.getTextChannel().sendMessage("404 | Dieser Command wurde gelöscht!").queue();
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
