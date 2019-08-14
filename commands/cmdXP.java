package commands;

import core.LevelRoleHander;
import core.XPHandler;
import core.restHandler;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.Permission;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import util.Emojis;
import util.Emojis2;
import util.STATIC;

import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;
import java.util.stream.Collectors;

public class cmdXP implements Command {
    @Override
    public boolean called(String[] args, MessageReceivedEvent event) {
        return false;
    }

    @Override
    public void action(String[] args, MessageReceivedEvent event) {
        restHandler.setCommands(event.getGuild(), restHandler.getCommands(event.getGuild()));
        if (args[0].contains("enable")) {
            if (!event.getMember().hasPermission(Permission.MANAGE_SERVER)) {
                event.getTextChannel().sendMessage(new EmbedBuilder().setColor(Color.red).setTitle(Emojis.WRENCH +"" + Emojis2.WHITE_SMALL_SQUARE + "<:X_:504673235212697601> Auch, da ist was schief gelaufen...").setDescription("Ich wollte " + args[0] + " tun, aber da habe ich festgestellt, das du gar nicht das Recht (`MANAGE_SERVER`) hast! \n Ich will nicht gefeuert werden, also melde dich erst wieder wenn du das Recht hast!").build()).queue();
                return;
            }
            if (restHandler.XPenable(event.getGuild().getId()) == 0) {
                restHandler.setenable(event.getGuild().getId(), 1);
                event.getTextChannel().sendMessage("Das XP System wurde auf diesem Server deaktivert! \n Warnung: Hier kann nun niemand mehr Leveln!").queue();
            } else {
                restHandler.setenable(event.getGuild().getId(), 0);
                event.getTextChannel().sendMessage("Das XP System wurde auf diesem Server wieder aktiviert!").queue();
            }
        } else {
            if (restHandler.XPenable(event.getGuild().getId()) == 1) {
                event.getTextChannel().sendMessage(new EmbedBuilder().setTitle("<:XP:590126610594922516> " + Emojis2.WHITE_SMALL_SQUARE + " Keksiges-XP-System | Version 2.0 / <:X_:504673235212697601> Fehler").setDescription("Das XP System auf ist auf diesem Server **deaktiviert**").build()).queue();
                return;
            }
            if(args.length == 0) {
                Integer xp = (XPHandler.getUsersXP(event.getAuthor(), event.getGuild().getId()));
                event.getTextChannel().sendMessage(new EmbedBuilder().setTitle("<:XP:590126610594922516> " + Emojis2.WHITE_SMALL_SQUARE + " Keksiges-XP-System | Version 2.0 / Level-Details von " + event.getAuthor().getName() + "#" + event.getAuthor().getDiscriminator()).setDescription(" **" + xp + "** XP").setImage(event.getAuthor().getAvatarUrl()).build()).queue();
                return;
            }

            Integer xp2 = LevelRoleHander.getXP(event.getGuild().getId());
            try {
                Integer xp3 = (XPHandler.getUsersXP(event.getMessage().getMentionedUsers().get(0), event.getGuild().getId()));
                event.getTextChannel().sendMessage(new EmbedBuilder().setTitle("<:XP:590126610594922516> " + Emojis2.WHITE_SMALL_SQUARE + " Keksiges-XP-System | Version 2.0 / Level-Details von " + event.getMessage().getMentionedUsers().get(0).getName() + "#" + event.getMessage().getMentionedUsers().get(0).getDiscriminator()).setDescription(" **" + xp3 + "** XP").setImage(event.getMessage().getMentionedUsers().get(0).getAvatarUrl()).build()).queue();
                return;
            } catch (Exception e) {
            }
            switch (args[0].toLowerCase()) {

                case "clear":

                    if (event.getMessage().getMentionedUsers() == null) {
                        try {
                            XPHandler.setUserLevel(event.getAuthor(), 1);

                            XPHandler.setUsersXP(event.getAuthor(), 1);
                            Message answer = event.getTextChannel().sendMessage(new EmbedBuilder().setTitle("<:XP:590126610594922516> " + Emojis2.WHITE_SMALL_SQUARE + " Bestes-XP-System | Version 2.0 / <a:discordload:525293213318250496>").setDescription("Von " + event.getAuthor().getName() + "#" + event.getAuthor().getDiscriminator() + " werden die XPs und die Levels zurückgesetzt... \n Dies dauert einen Moment...").setFooter("Ausgeführt von " + event.getAuthor().getName(), event.getAuthor().getAvatarUrl()).build()).complete();
                            new Timer().schedule(new TimerTask() {
                                @Override
                                public void run() {
                                    answer.delete().queue();
                                    Integer Level2 = XPHandler.getUserLevel(event.getAuthor(), event.getGuild().getId());
                                    Integer xp2 = (XPHandler.getUsersXP(event.getAuthor(), event.getGuild().getId()));
                                    event.getTextChannel().sendMessage(new EmbedBuilder().setTitle("<:XP:590126610594922516> " + Emojis2.WHITE_SMALL_SQUARE + " Bestes-XP-System | Version 2.0 / XP erfolgreich zurückgesetzt!").setDescription(event.getAuthor().getName() + "#" + event.getAuthor().getDiscriminator() + " ist nun Level **" + Level2 + "** und hat nun **" + xp2 + "** XP").setFooter("Ausgeführt von " + event.getAuthor().getName(), event.getAuthor().getAvatarUrl()).build()).queue();
                                }
                            }, 5000);


                        } catch (Exception e) {

                        }
                    } else {
                        if (event.getAuthor().getId().equals("349879801064194060")) {
                            try {
                                XPHandler.setUserLevel(event.getMessage().getMentionedUsers().get(0), 1);

                                XPHandler.setUsersXP(event.getMessage().getMentionedUsers().get(0), 1);
                                Message answer = event.getTextChannel().sendMessage(new EmbedBuilder().setTitle("<:XP:590126610594922516> " + Emojis2.WHITE_SMALL_SQUARE + " Keksiges-XP-System | Version 2.0 / <a:discordload:525293213318250496>").setDescription("Von " + event.getAuthor().getName() + "#" + event.getAuthor().getDiscriminator() + " werden die XPs und die Levels zurückgesetzt... \n Dies dauert einen Moment...").setFooter("Ausgeführt von " + event.getAuthor().getName(), event.getAuthor().getAvatarUrl()).build()).complete();
                                new Timer().schedule(new TimerTask() {
                                    @Override
                                    public void run() {
                                        answer.delete().queue();
                                        Integer Level2 = XPHandler.getUserLevel(event.getMessage().getMentionedUsers().get(0), event.getGuild().getId());
                                        Integer xp2 = (XPHandler.getUsersXP(event.getMessage().getMentionedUsers().get(0), event.getGuild().getId()));
                                        event.getTextChannel().sendMessage(new EmbedBuilder().setTitle("<:XP:590126610594922516> " + Emojis2.WHITE_SMALL_SQUARE + " Keksiges-XP-System | Version 2.0 / XP erfolgreich zurückgesetzt!").setDescription(event.getMessage().getMentionedUsers().get(0).getName() + "#" + event.getMessage().getMentionedUsers().get(0).getDiscriminator() + " ist nun Level **" + Level2 + "** und hat nun **" + xp2 + "** XP").setFooter("Ausgeführt von " + event.getAuthor().getName(), event.getAuthor().getAvatarUrl()).build()).queue();
                                    }
                                }, 5000);


                            } catch (Exception e) {

                            }
                        } else {
                            event.getTextChannel().sendMessage(new EmbedBuilder().setTitle("<:XP:590126610594922516> " + Emojis2.WHITE_SMALL_SQUARE + " Keksiges-XP-System | Version 2.0 / <:X_:504673235212697601> XP nicht zurückgesetzt!").setDescription(event.getMessage().getAuthor().getName() + "#" + event.getMessage().getAuthor().getDiscriminator() + " sieht nicht so aus, als wärst du mein Master... \n Deine eigenen XP darfst du reseten, aber nicht die von anderen.").setFooter("Ausgeführt von " + event.getAuthor().getName(), event.getAuthor().getAvatarUrl()).build()).queue();
                        }
                    }
                    break;

                case "toplist":

//                    new Timer().schedule(new TimerTask() {
//////                        @Override
//////                        public void run() {
//////                            event.getTextChannel().deleteMessageById(event.getMessage().getId()).queue();
//////                            event.getTextChannel().sendMessage(new EmbedBuilder().setTitle("<:XP:564430132878770176> " + Emojis2.WHITE_SMALL_SQUARE + " Bestes-XP-System | Version 1.0 / <:X_:504673235212697601> Ouch...").setDescription("Es sieht so aus, als währe deine Anfrage im WLAN-Kabel des Botes stecken geblieben... \n Bitte versuche es später erneut oder schaue auf unserer [Statuspage](https://beste_bot.statuskit.com/)").setFooter("Ausgeführt von " + event.getAuthor().getName(), event.getAuthor().getAvatarUrl()).build()).queue();
//////                        }
//////                    }, 10 * 1000);
                    try {
                        event.getTextChannel().sendMessage(new EmbedBuilder().setTitle("<:XP:590126610594922516> " + Emojis2.WHITE_SMALL_SQUARE + " Keksiges-XP-System | Version 2.0" + "/ XP Toplist von **allen** Server(n)").setDescription(XPHandler.getToplist().stream().map(e -> String.format("**%s** mit **%dXP**", e.getKey().getName(), e.getValue())).collect(Collectors.joining("\n"))).setFooter("Pro Naricht erhälst du 2XP", event.getAuthor().getAvatarUrl()).build()).queue();
                    } catch (Exception e) {
                        event.getTextChannel().sendMessage(new EmbedBuilder().setTitle("<:XP:590126610594922516> " + Emojis2.WHITE_SMALL_SQUARE + " Keksiges-XP-System | Version 2.0 / <:X_:504673235212697601> Hmm, irgendein Keks hat Mist gebaut").setDescription("Es scheint so, als ob ein Keks, der den Bot steuert, ein Fehler gemacht hat... \n Bitte verzeihe ihm und versuche es erneut, ich bin mir sicher, er schafft es dieses Mal :D").build()).queue();
                    }
                    break;
                case "setrole":

//                    new Timer().schedule(new TimerTask() {
//////                        @Override
//////                        public void run() {
//////                            event.getTextChannel().deleteMessageById(event.getMessage().getId()).queue();
//////                            event.getTextChannel().sendMessage(new EmbedBuilder().setTitle("<:XP:564430132878770176> " + Emojis2.WHITE_SMALL_SQUARE + " Bestes-XP-System | Version 1.0 / <:X_:504673235212697601> Ouch...").setDescription("Es sieht so aus, als währe deine Anfrage im WLAN-Kabel des Botes stecken geblieben... \n Bitte versuche es später erneut oder schaue auf unserer [Statuspage](https://beste_bot.statuskit.com/)").setFooter("Ausgeführt von " + event.getAuthor().getName(), event.getAuthor().getAvatarUrl()).build()).queue();
//////                        }
                    if (!(event.getMessage().getMentionedRoles() == null)) {
                        if (!(STATIC.xprole == 0)) {
                            LevelRoleHander.setRole(event.getGuild().getId(), event.getMessage().getMentionedRoles().get(0).getId());
                            event.getTextChannel().sendMessage(new EmbedBuilder().setTitle("<:XP:590126610594922516> " + Emojis2.WHITE_SMALL_SQUARE + " Keksiges-XP-System | Version 2.0 / XPRole gesetzt!").setDescription("Die neue LevelRole, die man bei " + STATIC.xprole + "XP bekommt, ist " + LevelRoleHander.getRole(event.getGuild())).setFooter("Ausgeführt von " + event.getAuthor().getName(), event.getAuthor().getAvatarUrl()).build()).queue();
                        } else {
                            event.getTextChannel().sendMessage(new EmbedBuilder().setTitle("<:XP:590126610594922516> " + Emojis2.WHITE_SMALL_SQUARE + " Keksiges-XP-System | Version 2.0 / <:X_:504673235212697601> XPRole nicht gesetzt!").setDescription("Bitte setze zuerst, mit welchem XP man die Rolle bekommen soll! \n `/xp setrolexp <XP>`").setFooter("Ausgeführt von " + event.getAuthor().getName(), event.getAuthor().getAvatarUrl()).build()).queue();
                        }
                    } else {
                        event.getTextChannel().sendMessage(new EmbedBuilder().setTitle("<:XP:590126610594922516> " + Emojis2.WHITE_SMALL_SQUARE + " Keksiges-XP-System | Version 2.0 / <:X_:504673235212697601> XPRole nicht gesetzt!").setDescription("Bitte pinge die Rolle, die du als LevelRole haben willst!").setFooter("Ausgeführt von " + event.getAuthor().getName(), event.getAuthor().getAvatarUrl()).build()).queue();
                    }
                    break;
                case "setrolexp":

//                    new Timer().schedule(new TimerTask() {
//////                    }, 10 * 1000);
//////                        @Override
//////                        public void run() {
//////                            event.getTextChannel().deleteMessageById(event.getMessage().getId()).queue();
//////                            event.getTextChannel().sendMessage(new EmbedBuilder().setTitle("<:XP:564430132878770176> " + Emojis2.WHITE_SMALL_SQUARE + " Bestes-XP-System | Version 1.0 / <:X_:504673235212697601> Ouch...").setDescription("Es sieht so aus, als währe deine Anfrage im WLAN-Kabel des Botes stecken geblieben... \n Bitte versuche es später erneut oder schaue auf unserer [Statuspage](https://beste_bot.statuskit.com/)").setFooter("Ausgeführt von " + event.getAuthor().getName(), event.getAuthor().getAvatarUrl()).build()).queue();
//////                        }
//////                    }, 10 * 1000);
                    if (event.getMember().hasPermission(Permission.MANAGE_SERVER)) {
                        if (xp2 == null) {
                            int newXp = event.getMessage().getContentDisplay().indexOf(args[2]);
                            LevelRoleHander.setXP(event.getGuild().getId(), newXp);
                            event.getTextChannel().sendMessage(new EmbedBuilder().setTitle("<:XP:590126610594922516> " + Emojis2.WHITE_SMALL_SQUARE + " Keksiges-XP-System | Version 2.0 / XPRole gesetzt!").setDescription(newXp + " ist die neue Anzahl um eine Rolle zu bekommen! \n Bitte setzte diese nun mit /xp setrole @Role").setFooter("Ausgeführt von " + event.getAuthor().getName(), event.getAuthor().getAvatarUrl()).build()).queue();
                        } else {
                            event.getTextChannel().sendMessage(new EmbedBuilder().setTitle("<:XP:590126610594922516> " + Emojis2.WHITE_SMALL_SQUARE + " Keksiges-XP-System | Version 2.0 / <:X_:504673235212697601> XPRole nicht gesetzt!").setDescription("Bitte setze zuerst, mit welchem XP man die Rolle bekommen soll! \n `/xp setrolexp <XP>`").setFooter("Ausgeführt von " + event.getAuthor().getName(), event.getAuthor().getAvatarUrl()).build()).queue();
                        }
                    } else {
                        event.getTextChannel().sendMessage(new EmbedBuilder().setTitle("<:XP:590126610594922516> " + Emojis2.WHITE_SMALL_SQUARE + " Keksiges-XP-System | Version 2.0 / <:X_:504673235212697601> XPRole nicht gesetzt!").setDescription("Dir fehlen mindestens 1 Permissions \n `MANAGE_SERVER`").setFooter("Ausgeführt von " + event.getAuthor().getName(), event.getAuthor().getAvatarUrl()).build()).queue();
                    }
                    break;

                case "list":
                    event.getTextChannel().sendMessage(new EmbedBuilder().setTitle("<:XP:590126610594922516> " + Emojis2.WHITE_SMALL_SQUARE + " Keksiges-XP-System | Version 2.0 / XP Liste").setDescription("XP-Liste: \n **20 XP** = Level **1** \n **100 XP** = Level **2** \n **500 XP** = Level **3** \n **700 XP** = Level **4** \n **1000 XP** = Level **5** \n **1500 XP** = Level **6** \n **2000 XP** = Level **7** \n **2800 XP** = Level **8** \n **4200 XP** = Level **9** \n **5400 XP** = Level **10** \n **7000 XP** = Level **11** \n **8900 XP** = Level **12** \n **10000 XP** = Level **13** \n **15000 XP** = Level **14** \n **20000 XP** = Level **15** \n **27000 XP** = Level **16** \n **35000 XP** = Level **17** \n **40000 XP** = Level **18** \n **65000 XP** = Level **19** \n **100000 XP** = Level **20** ").setFooter("Ausgeführt von " + event.getAuthor().getName(), event.getAuthor().getAvatarUrl()).build()).queue();
                    break;

                case "set":
                    if (event.getAuthor().getId().equals("349879801064194060")) {
                        if (args.length == 3) {
                            try {
                                int xp3 = Integer.parseInt(args[1]);
                                int Level2 = Integer.parseInt(args[2]);
                                XPHandler.setUserLevel(event.getAuthor(), Level2);

                                XPHandler.setUsersXP(event.getAuthor(), xp2);
                                Message answer = event.getTextChannel().sendMessage(new EmbedBuilder().setTitle("<:XP:590126610594922516> " + Emojis2.WHITE_SMALL_SQUARE + " Keksiges-XP-System | Version 2.0 / <a:discordload:525293213318250496>").setDescription("Von " + event.getAuthor().getName() + "#" + event.getAuthor().getDiscriminator() + " werden die XPs und die Levels gesetzt... \n Dies dauert einen Moment...").setFooter("Ausgeführt von " + event.getAuthor().getName(), event.getAuthor().getAvatarUrl()).build()).complete();
                                new Timer().schedule(new TimerTask() {
                                    @Override
                                    public void run() {
                                        answer.delete().queue();
                                        Integer Level2 = XPHandler.getUserLevel(event.getAuthor(), event.getGuild().getId());
                                        Integer xp2 = (XPHandler.getUsersXP(event.getAuthor(), event.getGuild().getId()));
                                        event.getTextChannel().sendMessage(new EmbedBuilder().setTitle("<:XP:590126610594922516> " + Emojis2.WHITE_SMALL_SQUARE + " Keksiges-XP-System | Version 2.0 / XP erfolgreich gesetzt!").setDescription(event.getAuthor().getName() + "#" + event.getAuthor().getDiscriminator() + " ist nun Level **" + Level2 + "** und hat nun **" + xp3 + "** XP").setFooter("Ausgeführt von " + event.getAuthor().getName(), event.getAuthor().getAvatarUrl()).build()).queue();
                                    }
                                }, 5000);

                            } catch (Exception e) {
                                //event.getTextChannel().sendMessage(new EmbedBuilder().setColor(Color.RED).setTitle("<:XP:564430132878770176> " + Emojis2.WHITE_SMALL_SQUARE + " <:X_:504673235212697601> Mensch du Keks, machs richtig, als Dev des Bots ts ts >> **Fehler**").setDescription("Du musst eine **Zahl**, keinen **Buchstaben**, angeben!").build()).queue();
                                event.getTextChannel().sendMessage(e.toString()).queue();
                            }
                        }
                    } else {
                        if (event.getAuthor().getId().equals("349879801064194060")) {
                            try {
                                int xp3 = Integer.parseInt(args[2]);
                                int Level2 = Integer.parseInt(args[3]);
                                XPHandler.setUserLevel(event.getMessage().getMentionedUsers().get(0), Level2);

                                XPHandler.setUsersXP(event.getMessage().getMentionedUsers().get(0), xp3);
                                Message answer = event.getTextChannel().sendMessage(new EmbedBuilder().setTitle("<:XP:590126610594922516> " + Emojis2.WHITE_SMALL_SQUARE + " Keksiges-XP-System | Version 2.0 / <a:discordload:525293213318250496>").setDescription("Von " + event.getAuthor().getName() + "#" + event.getAuthor().getDiscriminator() + " werden die XPs und die Levels gesetzt... \n Dies dauert einen Moment...").setFooter("Ausgeführt von " + event.getAuthor().getName(), event.getAuthor().getAvatarUrl()).build()).complete();
                                new Timer().schedule(new TimerTask() {
                                    @Override
                                    public void run() {
                                        answer.delete().queue();
                                        Integer Level2 = XPHandler.getUserLevel(event.getMessage().getMentionedUsers().get(0), event.getGuild().getId());
                                        Integer xp2 = (XPHandler.getUsersXP(event.getMessage().getMentionedUsers().get(0), event.getGuild().getId()));
                                        event.getTextChannel().sendMessage(new EmbedBuilder().setTitle("<:XP:590126610594922516> " + Emojis2.WHITE_SMALL_SQUARE + " Keksiges-XP-System | Version 2.0 / XP erfolgreich gesetzt!").setDescription(event.getMessage().getMentionedUsers().get(0).getName() + "#" + event.getMessage().getMentionedUsers().get(0).getDiscriminator() + " ist nun Level **" + Level2 + "** und hat nun **" + xp3 + "** XP").setFooter("Ausgeführt von " + event.getAuthor().getName(), event.getAuthor().getAvatarUrl()).build()).queue();
                                    }
                                }, 5000);

                            } catch (Exception e) {
                                event.getTextChannel().sendMessage(new EmbedBuilder().setColor(Color.RED).setTitle("<:XP:590126610594922516> " + Emojis2.WHITE_SMALL_SQUARE + " <:X_:504673235212697601> Mensch du Keks, machs richtig, als Dev des Bots ts ts >> **Fehler**").setDescription("Du musst eine **Zahl**, keinen **Buchstaben**, angeben!").build()).queue();
                                event.getTextChannel().sendMessage(e.toString()).queue();
                            }
                        } else {
                            event.getTextChannel().sendMessage(new EmbedBuilder().setTitle("<:XP:590126610594922516> " + Emojis2.WHITE_SMALL_SQUARE + " Keksiges-XP-System | Version 2.0 / <:X_:504673235212697601> XP nicht gesetzt!").setDescription(event.getMessage().getAuthor().getName() + "#" + event.getMessage().getAuthor().getDiscriminator() + " sieht nicht so aus, als wärst du mein Master... \n Die XPs darf nur mein Master (`KeksOfs | Sebl #6013`) setzen!").setFooter("Ausgeführt von " + event.getAuthor().getName(), event.getAuthor().getAvatarUrl()).build()).queue();
                        }
                    }
                    break;
                case "help":
                    event.getTextChannel().sendMessage(new EmbedBuilder().setTitle("<:XP:590126610594922516> " + "Keksiges XP System Hilfe").setDescription("Alle unter Befehle: \n **/xp list** - Wie viel brauchst du noch bis zum nächsten Level? \n **/xp @Name** - Wie gut ist wohl dieser Kek(s)? \n **/xp clear** - Cleare deine XP und dein Level \n **/xp toplist** - Wer sind die besten Kekse?").setFooter("Ausgeführt von " + event.getAuthor().getName(), event.getAuthor().getAvatarUrl()).build()).queue();
            }
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
