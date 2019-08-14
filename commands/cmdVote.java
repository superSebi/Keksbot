package commands;

import core.restHandler;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.Member;
import net.dv8tion.jda.core.entities.Role;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.managers.GuildController;
import org.discordbots.api.client.DiscordBotListAPI;
import util.Emojis2;

import java.awt.*;

public class cmdVote implements Command {

    @Override
    public boolean called(String[] args, MessageReceivedEvent event) {
        return false;
    }

    @Override
    public void action(String[] args, MessageReceivedEvent event) {
        restHandler.setCommands(event.getGuild(), restHandler.getCommands(event.getGuild()));
        DiscordBotListAPI api = new DiscordBotListAPI.Builder()
                .token("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6IjQ5MzA2NjM4NzE4MzYzMjM4NyIsImJvdCI6dHJ1ZSwiaWF0IjoxNTY1NTU1MjY5fQ.zsDZKb2eCyu48GeAr32dBAyGCWz9hHSiBFeKv9Pk8j8")
                .botId("493066387183632387")
                .build();
        String userId = event.getAuthor().getId();
        api.hasVoted(userId).whenComplete((hasVoted, e) -> {
            if(hasVoted) {
                event.getTextChannel().sendMessage(
                        new EmbedBuilder().setColor(Color.ORANGE)
                                .setTitle(Emojis2.COOKIE + "" + Emojis2.WHITE_SMALL_SQUARE + "Vote for me")
                                .setDescription("Du hast heute schon gevotet <3 \n Du hast die Unterstützer Rolle und 100XP nicht erhalten? Gebe einfach nochmal `/vote` ein \n Vielen Dank für deine Unterstützung <3")
                                .build()).queue();
                try {

                    GuildController c = event.getGuild().getController();
                    Member m = event.getMember();
                    Guild g = event.getJDA().getGuildById("504609411243704365");
                    Role muteRole = g.getRoles().stream().filter(r -> r.getName().equals("Unterstützer (Online)")).findFirst().orElse(null);
                    c.addSingleRoleToMember(m, muteRole).queue();
                } catch (Exception er) {
                    event.getTextChannel().sendMessage(":x: | Du musst auf dem KeksBot Support Server sein!").queue();
                }
            }
            else
                event.getTextChannel().sendMessage(
                        new EmbedBuilder().setColor(Color.ORANGE)
                                .setTitle(Emojis2.COOKIE + "" + Emojis2.WHITE_SMALL_SQUARE + "Vote for me")
                                .setDescription("Du hast noch nicht gevotet! \n Wenn du votest erhälst du die Unterstützer Rolle und 100XP! Also worauf wartest du :D \n [Klick mich](https://discordbots.org/bot/493066387183632387/vote)")
                                .build()).queue();
            Guild g = event.getJDA().getGuildById("504609411243704365");
            Role muteRole = g.getRoles().stream().filter(r -> r.getName().equals("Unterstützer")).findFirst().orElse(null);
            event.getMember().getRoles().remove(muteRole);

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

