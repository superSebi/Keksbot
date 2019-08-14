package listeners;

import core.gChatHandler;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.PrivateChannel;
import net.dv8tion.jda.core.entities.TextChannel;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;
import util.Emojis2;

import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;

;

public class gchatListener extends ListenerAdapter {

    public void onMessageReceived(MessageReceivedEvent event) {
        if (event.getAuthor().isBot()) {
            return;
        }
        if (event.getTextChannel().getName().equals("keksiger-globalchat")) {


            Message msg = event.getMessage();
            String IMAGE = new String();
            if (event.getMessage().getAttachments().size() == 0) {
                IMAGE = null;
            } else {
                IMAGE = event.getMessage().getAttachments().get(0).getUrl();
            }
            if (event.getAuthor().getId().equals(gChatHandler.loadBlacklist(event.getAuthor().getId()))) {
                if (event.getAuthor().getId().equals("349879801064194060")) {
                    msg.delete().queue();
                } else {
                    PrivateChannel pc = msg.getAuthor().openPrivateChannel().complete();
                    pc.sendMessage(new EmbedBuilder().setTitle("Das darfst du nicht!").setDescription("Du bist aus dem Globalchat gebannt und musst warten bis du entbannt wurdest!").build()).queue();
                    msg.delete().queue();
                    return;
                }
            }
            if(msg.mentionsEveryone()) {
                PrivateChannel pc = msg.getAuthor().openPrivateChannel().complete();
                pc.sendMessage(new EmbedBuilder().setTitle("Das darfst du nicht!").setDescription("Leute Pingen im GC ist **deaktviert**!").build()).queue();
                msg.delete().queue();
                return;
            }
            if (msg.getContentDisplay().equals("regeln") || msg.getContentDisplay().equals("Regeln") || msg.getContentDisplay().equals("REGELN")) {
                PrivateChannel pc = msg.getAuthor().openPrivateChannel().complete();
                pc.sendMessage(new EmbedBuilder().setTitle("Hey psssst, du hast die GlobalchatRegeln angefordert, oder?").setDescription("Diese kommen bald...").build()).queue();
                msg.delete().queue();
                return;
            }
            if (msg.getContentDisplay().contains("spast") || msg.getContentDisplay().contains("Spast") || msg.getContentDisplay().contains("SPAST")) {
                PrivateChannel pc = msg.getAuthor().openPrivateChannel().complete();
                pc.sendMessage("Ey " + event.getAuthor().getAsMention() + ", bitte nutze keine bösen wörter im Globalchat qwq").queue();
                msg.delete().queue();
                return;
            }
            if (msg.getContentDisplay().contains("hurensohn") || msg.getContentDisplay().contains("Hurensohn") || msg.getContentDisplay().contains("HURENSOHN")) {
                PrivateChannel pc = msg.getAuthor().openPrivateChannel().complete();
                pc.sendMessage("Ey " + event.getAuthor().getAsMention() + ", bitte nutze keine bösen wörter im Globalchat qwq").queue();
                msg.delete().queue();
                return;
            }
            if (msg.getContentDisplay().contains("Bastard")) {
                PrivateChannel pc = msg.getAuthor().openPrivateChannel().complete();
                pc.sendMessage("Ey " + event.getAuthor().getAsMention() + ", bitte nutze keine bösen wörter im Globalchat qwq").queue();
                msg.delete().queue();
                return;
            }
//            if (msg.getContentDisplay().contains("https://") || msg.getContentDisplay().contains("http://")) {
//                PrivateChannel pc = msg.getAuthor().openPrivateChannel().complete();
//                pc.sendMessage("Ey " + event.getAuthor().getAsMention() + ", bitte nutze den GC nicht für Werbung oder sonstige Links!").queue();
//                msg.delete().queue();
//                return;
//            }
            if(msg.getContentDisplay().contains("/delete") || msg.getContentDisplay().contains("/Delete") || msg.getContentDisplay().contains("/DELETE")) {
                if (!(msg.getAuthor().getId().equals("349879801064194060") || event.getAuthor().getId().equals("526023653348081675") || event.getAuthor().getId().equals("455786480795910165") || event.getAuthor().getId().equals("186055122608979968") || event.getAuthor().getId().equals("477599429486968862") || event.getAuthor().getId().equals("508600586040770570"))) {
                    PrivateChannel pc = msg.getAuthor().openPrivateChannel().complete();
                    pc.sendMessage(new EmbedBuilder().setDescription("Du bist kein Supporter des KeksBots, deshalb darfst du nicht eine Nachricht löschen!").setFooter("Bald kannst du deine eigenen Nachrichten löschen, aber noch nicht jetzt", event.getAuthor().getAvatarUrl()).build()).queue();
                    return;
                }
                Message msgsearch = event.getTextChannel().getMessageById(event.getMessage().getContentDisplay().replace("/delete", "").replace(" ", "")).complete();
                if(msgsearch == null) {
                    PrivateChannel pc = event.getAuthor().openPrivateChannel().complete();
                    pc.sendMessage("Du musst angeben was ich löschen soll! (Die MessageID bekommst du, indem du auf die 3 Punkte neben der Nachricht gehst und auf `ID kopieren` gehst! WARNUNG: Dafür musst du den Entwicklermodus aktiviert haben!").queue();
                } else {
                    try {
                        for (Guild guild : event.getJDA().getGuilds()) {
                            try {
                                TextChannel channel = guild.getTextChannelsByName("keksiger-globalchat", true).get(0);
                                channel.sendMessage(new EmbedBuilder().setColor(Color.RED).setDescription("``" + event.getJDA().getSelfUser().getName() + "`` ➛ **" + event.getJDA().getSelfUser().getName() + "**#" + event.getJDA().getSelfUser().getDiscriminator() + ": \n " + event.getAuthor().getAsMention()+ " hat eine Naricht gelöscht! ( ID: " + msgsearch.getId() + ")").setFooter("ServerID: " + event.getGuild().getId() + ", MessageID: " + event.getMessage().getId() + ", UserID: " + event.getAuthor().getId(), event.getJDA().getSelfUser().getAvatarUrl()).build()).queue();
                            } catch (Exception e) {

                            }
                        }


                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    msgsearch.delete().queue();

                }
                return;
            }
            if (msg.getContentDisplay().contains("/gcban") || msg.getContentDisplay().contains("/GCban") || msg.getContentDisplay().contains("/GCBAN")) {
                SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
                String uhrzeit = sdf.format(new Date());
                String reason = "Unbegründet";
                if (event.getMessage().getMentionedUsers() == null && event.getGuild().getMemberById(event.getGuild().getMemberById(event.getMessage().getContentDisplay().replace("/gcban", "").replace(" ", "")).getUser().getId()) == null) {
                    PrivateChannel pc = msg.getAuthor().openPrivateChannel().complete();
                    pc.sendMessage(new EmbedBuilder().setDescription("Bitte gebe an, wenn ich bannen soll!").setFooter("Ausgeführt um " + uhrzeit + " Uhr", event.getAuthor().getAvatarUrl()).build()).queue();
                    return;
                }
                if (!(msg.getAuthor().getId().equals("349879801064194060") || event.getAuthor().getId().equals("526023653348081675") || event.getAuthor().getId().equals("455786480795910165") || event.getAuthor().getId().equals("186055122608979968") || event.getAuthor().getId().equals("477599429486968862") || event.getAuthor().getId().equals("508600586040770570"))) {
                    PrivateChannel pc = msg.getAuthor().openPrivateChannel().complete();
                    pc.sendMessage(new EmbedBuilder().setDescription("Du bist kein Supporter des KeksBots, deshalb darfst du nicht bannen").setFooter("Ausgeführt um " + uhrzeit + " Uhr", event.getAuthor().getAvatarUrl()).build()).queue();
                } else {
                    if (event.getMessage().getMentionedUsers() == null) {
                        String id = gChatHandler.loadBlacklist(event.getMessage().getMentionedUsers().get(0).getId());
                    if (id == null) {
                        PrivateChannel pc = msg.getMentionedUsers().get(0).openPrivateChannel().complete();
                        pc.sendMessage(new EmbedBuilder().setTitle("Du wurdest aus dem **keksigen Globalchat verbannt**").setDescription("Der Supporter **" + event.getAuthor().getAsMention() + "** hat dich wegen ``" + reason + "``" + ", aus dem Globalchat gebannt.").setFooter("Ausgeführt um " + uhrzeit + " Uhr", event.getAuthor().getAvatarUrl()).build()).queue();
                        gChatHandler.setBlackList(event.getMessage().getMentionedUsers().get(0).getId());
                        try {
                            for (Guild guild : event.getJDA().getGuilds()) {
                                try {
                                    TextChannel channel = guild.getTextChannelsByName("keksiger-globalchat", true).get(0);
                                    channel.sendMessage(new EmbedBuilder().setColor(Color.RED).setDescription("``" + event.getJDA().getSelfUser().getName() + "`` ➛ **" + event.getJDA().getSelfUser().getName() + "**#" + event.getJDA().getSelfUser().getDiscriminator() + ": \n " + event.getMessage().getMentionedUsers().get(0).getAsMention() + " wurde von " + event.getAuthor().getAsMention() + ", aus dem Globalchat gebannt!").setFooter("ServerID: " + event.getGuild().getId() + ", MessageID: " + event.getMessage().getId() + ", UserID: " + id, event.getJDA().getSelfUser().getAvatarUrl()).build()).queue();
                                } catch (Exception e) {

                                }
                            }


                        } catch (Exception e) {
                            e.printStackTrace();
                        }


                    } else {
                        PrivateChannel pc = msg.getMentionedUsers().get(0).openPrivateChannel().complete();
                        pc.sendMessage(new EmbedBuilder().setTitle("Du wurdest aus dem **keksigen Globalchat entbannt**").setDescription("Der Supporter **" + event.getAuthor().getAsMention() + "** hat dich aus dem Globalchat entbannt.").setFooter("Ausgeführt um " + uhrzeit + " Uhr", event.getAuthor().getAvatarUrl()).build()).queue();
                        gChatHandler.removeBlacklist(event.getMessage().getMentionedUsers().get(0).getId());
                        try {
                            for (Guild guild : event.getJDA().getGuilds()) {
                                try {
                                    TextChannel channel = guild.getTextChannelsByName("keksiger-globalchat", true).get(0);
                                    channel.sendMessage(new EmbedBuilder().setColor(Color.RED).setDescription("``" + event.getJDA().getSelfUser().getName() + "`` ➛ **" + event.getJDA().getSelfUser().getName() + "**#" + event.getJDA().getSelfUser().getDiscriminator() + ": \n " + event.getMessage().getMentionedUsers().get(0).getAsMention() + " wurde von " + event.getAuthor().getAsMention() + ", aus dem Globalchat entbannt!").setFooter("ServerID: " + event.getGuild().getId() + ", MessageID: " + event.getMessage().getId() + ", UserID: " + id, event.getJDA().getSelfUser().getAvatarUrl()).build()).queue();
                                } catch (Exception e) {

                                }
                            }


                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                    } else {
                        String id = gChatHandler.loadBlacklist(event.getGuild().getMemberById(event.getMessage().getContentDisplay().replace("/gcban", "").replace(" ", "")).getUser().getId());
                        if (id == null) {
                            PrivateChannel pc = event.getGuild().getMemberById(event.getMessage().getContentDisplay().replace("/gcban", "").replace(" ", "")).getUser().openPrivateChannel().complete();
                            pc.sendMessage(new EmbedBuilder().setTitle("Du wurdest aus dem **keksigen Globalchat verbannt**").setDescription("Der Supporter **" + event.getAuthor().getAsMention() + "** hat dich wegen ``" + reason + "``" + ", aus dem Globalchat gebannt.").setFooter("Ausgeführt um " + uhrzeit + " Uhr", event.getAuthor().getAvatarUrl()).build()).queue();
                            gChatHandler.setBlackList(event.getGuild().getMemberById(event.getMessage().getContentDisplay().replace("/gcban", "").replace(" ", "")).getUser().getId());
                            try {
                                for (Guild guild : event.getJDA().getGuilds()) {
                                    try {
                                        TextChannel channel = guild.getTextChannelsByName("keksiger-globalchat", true).get(0);
                                        channel.sendMessage(new EmbedBuilder().setColor(Color.RED).setDescription("``" + event.getJDA().getSelfUser().getName() + "`` ➛ **" + event.getJDA().getSelfUser().getName() + "**#" + event.getJDA().getSelfUser().getDiscriminator() + ": \n " + event.getGuild().getMemberById(event.getMessage().getContentDisplay().replace("/gcban", "").replace(" ", "")).getUser().getAsMention() + " wurde von " + event.getAuthor().getAsMention() + ", aus dem Globalchat gebannt!").setFooter("ServerID: " + event.getGuild().getId() + ", MessageID: " + event.getMessage().getId() + ", UserID: " + id, event.getJDA().getSelfUser().getAvatarUrl()).build()).queue();
                                    } catch (Exception e) {

                                    }
                                }


                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                                    try {
                                        TextChannel channel = event.getJDA().getGuildById("504609411243704365").getTextChannelsByName("log", true).get(0);
                                        channel.sendMessage(
                                                new EmbedBuilder().setColor(Color.RED) // Color.FARBE
                                                        .setTitle("<:confirm:509070945948925962>" + Emojis2.WHITE_SMALL_SQUARE + " GCBAN")
                                                        .addField("Spieler", event.getGuild().getMemberById(event.getMessage().getContentDisplay().replace("/gcban", "").replace(" ", "")).getUser().getName(), true)
                                                        .addField("Gebannt von", event.getAuthor().getName(), true)
                                                        .addField("Gebannt um", uhrzeit, true)
                                                        .addField("Grund", reason + "", false)
                                                        .build()
                                        ).queue();
                                    } catch (Exception e) {

                                    }



                        } else {
                            PrivateChannel pc = event.getGuild().getMemberById(event.getMessage().getContentDisplay().replace("/gcban", "").replace(" ", "")).getUser().openPrivateChannel().complete();
                            pc.sendMessage(new EmbedBuilder().setTitle("Du wurdest aus dem **keksigen Globalchat entbannt**").setDescription("Der Supporter **" + event.getAuthor().getAsMention() + "** hat dich aus dem Globalchat entbannt.").setFooter("Ausgeführt um " + uhrzeit + " Uhr", event.getAuthor().getAvatarUrl()).build()).queue();
                            gChatHandler.removeBlacklist(event.getGuild().getMemberById(event.getMessage().getContentDisplay().replace("/gcban", "").replace(" ", "")).getUser().getId());
                            try {
                                for (Guild guild : event.getJDA().getGuilds()) {
                                    try {
                                        TextChannel channel = guild.getTextChannelsByName("keksiger-globalchat", true).get(0);
                                        channel.sendMessage(new EmbedBuilder().setColor(Color.RED).setDescription("``" + event.getJDA().getSelfUser().getName() + "`` ➛ **" + event.getJDA().getSelfUser().getName() + "**#" + event.getJDA().getSelfUser().getDiscriminator() + ": \n " + event.getGuild().getMemberById(event.getMessage().getContentDisplay().replace("/gcban", "").replace(" ", "")).getUser().getAsMention() + " wurde von " + event.getAuthor().getAsMention() + ", aus dem Globalchat entbannt!").setFooter("ServerID: " + event.getGuild().getId() + ", MessageID: " + event.getMessage().getId() + ", UserID: " + id, event.getJDA().getSelfUser().getAvatarUrl()).build()).queue();
                                    } catch (Exception e) {

                                    }
                                }
                                try {
                                    TextChannel channel = event.getJDA().getGuildById("504609411243704365").getTextChannelsByName("log", true).get(0);
                                    channel.sendMessage(
                                            new EmbedBuilder().setColor(Color.RED) // Color.FARBE
                                                    .setTitle("<:confirm:509070945948925962>" + Emojis2.WHITE_SMALL_SQUARE + " GCENTBAN")
                                                    .addField("Spieler", event.getGuild().getMemberById(event.getMessage().getContentDisplay().replace("/gcban", "").replace(" ", "")).getUser().getName(), true)
                                                    .addField("Entbannt von", event.getAuthor().getName(), true)
                                                    .addField("Entbannt um", uhrzeit, true)
                                                    .build()
                                    ).queue();
                                } catch (Exception e) {

                                }


                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }
                    msg.delete().queue();
                    return;
                }
            }
                if(event.getAuthor().getId().equals("349879801064194060") || event.getAuthor().getId().equals("526023653348081675") || event.getAuthor().getId().equals("455786480795910165") || event.getAuthor().getId().equals("186055122608979968") || event.getAuthor().getId().equals("477599429486968862") || event.getAuthor().getId().equals("508600586040770570")) {
                try {
                    for (Guild guild : event.getJDA().getGuilds()) {
                        try {
                            TextChannel channel = guild.getTextChannelsByName("keksiger-globalchat", true).get(0);
                            channel.sendMessage(new EmbedBuilder().setColor(Color.GREEN).setDescription("``" + event.getGuild().getName() + "`` ➛ **[Team] " + event.getAuthor().getName() + "**#" + event.getAuthor().getDiscriminator() + ": \n " + event.getMessage().getContentRaw().replace("discord.gg/", "")).setImage(IMAGE).setFooter("ServerID: " + event.getGuild().getId() + ", MessageID: " + event.getMessage().getId() + ", UserID: " + event.getAuthor().getId(), event.getAuthor().getAvatarUrl()).build()).queue();
                        } catch (Exception e) {

                        }
                    }
                    msg.delete().queue();


                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                    try {
                        for (Guild guild : event.getJDA().getGuilds()) {
                            try {
                                TextChannel channel = guild.getTextChannelsByName("keksiger-globalchat", true).get(0);
                                channel.sendMessage(new EmbedBuilder().setColor(Color.ORANGE).setDescription("``" + event.getGuild().getName() + "`` ➛ **" + event.getAuthor().getName() + "**#" + event.getAuthor().getDiscriminator() + ": \n " + event.getMessage().getContentRaw().replace("discord.gg/", "")).setImage(IMAGE).setFooter("ServerID: " + event.getGuild().getId() + ", MessageID: " + event.getMessage().getId() + ", UserID: " + event.getAuthor().getId(), event.getAuthor().getAvatarUrl()).build()).queue();
                            } catch (Exception e) {

                            }
                        }
                        msg.delete().queue();


                    } catch (Exception e) {
                        e.printStackTrace();
                    }
            }

            }
        }

    }
