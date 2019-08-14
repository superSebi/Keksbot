package commands;

import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

import java.util.Objects;

public class cmdShutdown implements Command {


    @Override
    public boolean called(String[] args, MessageReceivedEvent event) {
        return false;
    }

    @Override
    public void action(String[] args, MessageReceivedEvent event) {
        if (event.getAuthor().getId().equals("349879801064194060")) {
            Objects.requireNonNull(event.getGuild().getDefaultChannel()).sendMessage("Ich wurde von meinem Master per /shutdown gezwungen, zu sterben. \n Mein Status ist [hier] (http://213.32.97.201/) nachzulesen.").queue();
            cmdAbout.save();
            event.getJDA().shutdown();

        } else {
            event.getTextChannel().sendMessage("Du bist nicht mein Gott, also lass die Finger von dem Command ey").queue();
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
