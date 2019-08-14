package commands;

import core.restHandler;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.Permission;
import net.dv8tion.jda.core.entities.Member;
import net.dv8tion.jda.core.entities.VoiceChannel;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import util.Emojis;
import util.Emojis2;

import java.awt.*;
import java.io.IOException;
import java.text.ParseException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;

public class cmdVKick implements Command {
    public static HashMap<Member, VoiceChannel> kicks = new HashMap<>();

    @Override
    public boolean called(String[] args, MessageReceivedEvent event) {
        return false;
    }

    @Override
    public void action(String[] args, MessageReceivedEvent event) throws ParseException, IOException {
        restHandler.setCommands(event.getGuild(), restHandler.getCommands(event.getGuild()));
        if (event.getMember().hasPermission(Permission.VOICE_MOVE_OTHERS)) {
            if (args.length > 0) {
                if (event.getMessage().getMentionedUsers().size() > 0) {

                    int timeout;
                    try {
                        StringBuilder sb = new StringBuilder();
                        Arrays.stream(args).forEach(s -> sb.append(" " + s));
                        timeout = Integer.parseInt(sb.toString().substring(1).replace("@" + event.getGuild().getMember(event.getMessage().getMentionedUsers().get(0)).getEffectiveName(), "").replaceAll(" ", ""));
                    } catch (Exception e) {
                        timeout = 0;
                    }


                    if (event.getGuild().getAfkChannel() == null) {
                        event.getTextChannel().sendMessage("Es ist kein AFK Channel vorhanden! Bitte erstelle diesen!").queue();
                        return;
                    }

                    Member victim = event.getGuild().getMember(event.getMessage().getMentionedUsers().get(0));
                    VoiceChannel current = event.getMember().getVoiceState().getChannel();
                    event.getGuild().getController().moveVoiceMember(victim, event.getGuild().getAfkChannel()).queue();

                    if (timeout > 0) {

                        kicks.put(victim, current);

                        event.getTextChannel().sendMessage(new EmbedBuilder()
                                .setColor(new Color(0xFF0036))
                                .setTitle(Emojis.WRENCH + "" + Emojis2.WHITE_SMALL_SQUARE + "Voice-Kick")
                                .setDescription(event.getAuthor().getAsMention() + " (*" + event.getMember().getRoles().get(0).getName() + "*) hat " + victim.getAsMention() +
                                        ", aus dem Channel `" + current.getName() + "`, mit einem Zeitbann der  **" + timeout + " Sekunden** geht, gekickt.")
                                .build()
                        ).queue();

                        new Timer().schedule(new TimerTask() {
                            @Override
                            public void run() {
                                kicks.remove(event.getGuild().getMember(event.getMessage().getMentionedUsers().get(0)));
                                victim.getUser().openPrivateChannel().complete().sendMessage("Dein Talk Timeout ist vorbei! Du kannst nun wieder dem Channel, `" + current.getName() + "`, beitretten.").queue();
                            }
                        }, timeout * 1000);
                    } else {
                        event.getTextChannel().sendMessage(new EmbedBuilder()
                                .setTitle(Emojis.WRENCH + "" + Emojis2.WHITE_SMALL_SQUARE + "Voice-Kick")
                                .setColor(new Color(0xFF0048))
                                .setDescription(event.getAuthor().getAsMention() + " (*" + event.getMember().getRoles().get(0).getName() + "*) hat " + victim.getAsMention() +
                                        ", aus dem Channel `" + current.getName() + "` gekickt.")
                                .build()
                        ).queue();
                    }

                } else {
                    event.getTextChannel().sendMessage("Sag mir, wenn ich kicken soll.").queue();
                }
            } else {
                event.getTextChannel().sendMessage("Deine Naricht muss etwas länger sein.").queue();
            }
        } else {
            Member victim = event.getGuild().getMember(event.getMessage().getMentionedUsers().get(0));
            event.getTextChannel().sendMessage(new EmbedBuilder().setColor(Color.red).setTitle(Emojis.WRENCH +"" + Emojis2.WHITE_SMALL_SQUARE + "<:X_:504673235212697601> Auch, da ist was schief gelaufen...").setDescription("Ich wollte" + victim.getAsMention() + ", aus dem Channel, kicken, aber da habe ich festgestellt, dass du gar nicht das Recht (`VOICE_MOVE_OTHERS`) hast! \n Ich will nicht gefeuert werden, also melde dich erst wieder wenn du das Recht hast!").build()).queue();

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
