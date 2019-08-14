package commands;

import core.XPHandler;
import core.restHandler;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import util.Emojis2;

public class cmdLevel implements Command{
    @Override
    public boolean called(String[] args, MessageReceivedEvent event) {
        return false;
    }

    @Override
    public void action(String[] args, MessageReceivedEvent event){
        restHandler.setCommands(event.getGuild(), restHandler.getCommands(event.getGuild()));
        Integer Level = XPHandler.getUserLevel(event.getAuthor(), event.getGuild().getId());
        if (args.length == 0) {
        try {

            event.getTextChannel().sendMessage(new EmbedBuilder().setTitle("<:XP:564430132878770176> "+ Emojis2.WHITE_SMALL_SQUARE + " Keksiges-XP-System | Version 2.0 / Level-Details von " + event.getAuthor().getName() + "#" + event.getAuthor().getDiscriminator()).setDescription("Level **" + Level + "**").setImage(event.getAuthor().getAvatarUrl()).build()).queue();
        } catch (Exception e) {
            event.getTextChannel().sendMessage("Du bist noch nicht in der Datenbank registriert... \n Bitte schreibe etwas mehr!").queue();

        }
        } else {
       //    try {
                Integer LevelAnother = XPHandler.getUserLevel(event.getMessage().getMentionedUsers().get(0), event.getGuild().getId());
                event.getTextChannel().sendMessage(new EmbedBuilder().setTitle("<:XP:564430132878770176> " + Emojis2.WHITE_SMALL_SQUARE + " Keksiges-XP-System | Version 2.0 / Level-Details von " + event.getMessage().getMentionedUsers().get(0).getName() + "#" + event.getMessage().getMentionedUsers().get(0).getDiscriminator()).setDescription(" Level **" + LevelAnother + "**").setImage(event.getMessage().getMentionedUsers().get(0).getAvatarUrl()).build()).queue();

        //    } catch (Exception e) {
         //       event.getTextChannel().sendMessage("Der User muss erst Level 1 sein, sonst kann ich dir nur einen Fehler anzeigen qwq").queue();

        //    }
        } if (Level == 20) {
            event.getTextChannel().sendMessage(new EmbedBuilder().setTitle("<:XP:564430132878770176> "+ Emojis2.WHITE_SMALL_SQUARE + " Keksiges-XP-System | Version 2.0 / Level-Details von " + event.getAuthor().getName() + "#" + event.getAuthor().getDiscriminator()).setDescription("Der Typ ist richtig **krass** \n Er hat das maximale Level erreicht!").setImage(event.getAuthor().getAvatarUrl()).build()).queue();
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
