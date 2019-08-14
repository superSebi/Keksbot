package listeners;

import core.XPHandler;
import core.restHandler;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;
import util.Emojis2;

import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;

public class autoXPSetter extends ListenerAdapter {


    public void onMessageReceived(MessageReceivedEvent event) {
        try {

            if (restHandler.XPenable(event.getGuild().getId()) == 1) {
                return;
            }
        } catch (Exception e) {
        }
           if (!event.getAuthor().isBot()) {
                Integer xp = XPHandler.getUsersXP(event.getAuthor(), event.getGuild().getId());
                XPHandler.setUsersXP(event.getAuthor(), xp);

                    try {
                    int vers = 2;
                    String name = "Keksiges-XP-System";
                    if (xp == 20) {
                        setLevel(event, 1, name + " | Version " + vers + ".0");

                    }
                    if (xp == 100) {
                        setLevel(event, 2, name + " | Version " + vers + ".0");

                    }
                    if (xp == 500) {
                        setLevel(event, 3, name + " | Version " + vers + ".0");

                    }
                    if (xp == 700) {
                        setLevel(event, 4, name + " | Version " + vers + ".0");

                    }
                    if (xp == 1000) {
                        setLevel(event, 5, name + " | Version " + vers + ".0");

                    }
                    if (xp == 1500) {
                        setLevel(event, 6, name + " | Version " + vers + ".0");

                    }
                    if (xp == 2000) {
                        setLevel(event, 7, name + " | Version " + vers + ".0");

                    }
                    if (xp == 2800) {
                        setLevel(event, 8, name + " | Version " + vers + ".0");

                    }
                    if (xp == 4200) {
                        setLevel(event, 9, name + " | Version " + vers + ".0");

                    }
                    if (xp == 5400) {
                        setLevel(event, 10, name + " | Version " + vers + ".0");

                    }
                    if (xp == 7000) {
                        setLevel(event, 11, name + " | Version " + vers + ".0");

                    }
                    if (xp == 8900) {
                        setLevel(event, 12, name + " | Version " + vers + ".0");

                    }
                    if (xp == 10000) {
                        setLevel(event, 13, name + " | Version " + vers + ".0");

                    }
                    if (xp == 15000) {
                        setLevel(event, 14, name + " | Version " + vers + ".0");

                    }
                    if (xp == 20000) {
                        setLevel(event, 15, name + " | Version " + vers + ".0");

                    }
                    if (xp == 27000) {
                        setLevel(event, 16, name + " | Version " + vers + ".0");

                    }
                    if (xp == 35000) {
                        setLevel(event, 17, name + " | Version " + vers + ".0");

                    }
                    if (xp == 40000) {
                        setLevel(event, 18, name + " | Version " + vers + ".0");

                    }
                    if (xp == 65000) {
                        setLevel(event, 19, name + " | Version " + vers + ".0");

                    }
                    if (xp == 100000) {
                        setLevel(event, 20, name + " | Version " + vers + ".0");
                        event.getTextChannel().sendMessage(new EmbedBuilder().setColor(Color.GREEN).setTitle("<:XP:564430132878770176> " + Emojis2.WHITE_SMALL_SQUARE + " Keksiges-XP-System | Version 2.0 ").setDescription(event.getAuthor().getName() + "#" + event.getAuthor().getDiscriminator() + " hat das **maximale** Level erreicht!").setFooter("HGW " + event.getAuthor().getName(), event.getAuthor().getAvatarUrl()).build()).queue();
                    }
//                    if (xp == xprole) {
//                        Optional<Role> role = LevelRoleHander.getRole(event.getGuild());
//                        role.ifPresent(member -> member.getJDA().getRoles().add(role.get()));
//                        event.getTextChannel().sendMessage(new EmbedBuilder().setColor(Color.GREEN).setTitle("<:XP:564430132878770176> " + Emojis2.WHITE_SMALL_SQUARE + " Keksiges-XP-System | Version 2.0 ").setDescription(event.getAuthor().getName() + "#" + event.getAuthor().getDiscriminator() + " hat ein neues Level und die Rolle " + LevelRoleHander.getRole(event.getGuild()).toString() + " bekommen").setFooter("HGW " + event.getAuthor().getName(), event.getAuthor().getAvatarUrl()).build()).queue();
//                    }


                } catch (Exception e) {

                }
            }
    }

    private void setLevel(MessageReceivedEvent event, int i, String s) {
        try {
            XPHandler.setUserLevel(event.getAuthor(), i);
           // XPHandler.saveXPs();
            Message answer = event.getTextChannel().sendMessage(new EmbedBuilder().setTitle("<:XP:564430132878770176> " + Emojis2.WHITE_SMALL_SQUARE + " Keksiges-XP-System | Version 2.0 / <a:discordload:525293213318250496>").setDescription("Von " + event.getAuthor().getName() + "#" + event.getAuthor().getDiscriminator() + " hat ein neues Level erreicht! \n Berechne...").setFooter("HGW " + event.getAuthor().getName(), event.getAuthor().getAvatarUrl()).build()).complete();
            new Timer().schedule(new TimerTask() {
                @Override
                public void run() {
                    answer.delete().queue();
                    Integer Level2 = XPHandler.getUserLevel(event.getAuthor(), event.getGuild().getId());
                    Integer xp2 = XPHandler.getUsersXP(event.getAuthor() , event.getGuild().getId());
                    event.getTextChannel().sendMessage(new EmbedBuilder().setTitle("<:XP:564430132878770176> " + Emojis2.WHITE_SMALL_SQUARE + " Keksiges-XP-System | Version 2.0 / " + event.getAuthor().getName() + " hat ein neues Level erreicht!").setDescription(event.getMessage().getAuthor().getName() + "#" + event.getAuthor().getDiscriminator() + " ist nun Level **" + Level2 + "** und hat nun **" + xp2 + "** XP").setFooter("HGW " + event.getAuthor().getName(), event.getAuthor().getAvatarUrl()).build()).queue();
                }
            }, 5000);

        } catch (Exception e) {

        }
    }
}


