package commands;

import core.CommandHandler;
import core.restHandler;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.EmbedBuilder;
import util.Emojis;
import util.Emojis2;
import core.PrefixHandler;
import util.STATIC;

import java.awt.*;

public class cmdHelp implements Command {
    private void sendMessage(MessageReceivedEvent event,String title, String content) {
        event.getTextChannel().sendMessage(new EmbedBuilder().setColor(Color.GREEN)
                .setTitle(title)
                .setDescription(content)
        .build()).queue();
    }

    @Override
    public boolean called (String[] args, MessageReceivedEvent event) {
        return false;
    }

    @Override
    public void action (String[]  args, MessageReceivedEvent event) {
        restHandler.setCommands(event.getGuild(), restHandler.getCommands(event.getGuild()));

        if (args.length == 0)

     //   event.getTextChannel().sendMessage ("Dieser Bot wurde von KeksOf (Sebl) geschrieben! Derzeit gibt es folgende Commands: -keks = Du kannst einen Keks essen. -deathbattle = Starte ein super krasses Battle gegen einen Freund etc -friendscore = Gebe einen weiteren User an und schaue wir sehr ihr Zusammen passt!  -- Version: 0.1").queue();
        event.getTextChannel().sendMessage(
                    new EmbedBuilder().setColor(Color.GREEN)
                           .setTitle("KeksBot by Sebl v" + STATIC.VERS + "| Hilfe")
                          .setDescription("Server-Prefix: `" + PrefixHandler.getServerPrefix(event.getGuild().getId()) + "` \n Dein Prefix: `" + PrefixHandler.getUserPrefix(event.getAuthor().getId(),event.getGuild().getId()) + "`" )
                            .addField(Emojis2.GEAR +  "" + Emojis2.WHITE_SMALL_SQUARE +  "Nützliches", "setserverprefix, setmyprefix, bugreport, umfrage, timer",  true)
                            .addField(":tada:" + Emojis2.WHITE_SMALL_SQUARE + "**GBManager [NEU]**", "**setgb**, **gb**", false)
                            .addField(Emojis.WRENCH + "" + Emojis2.WHITE_SMALL_SQUARE + "**Moderator-Tools**","kick, warn, ban, ~~mute~~, log, setjoinmessage, setjoinchannel, joinchannel, setleavemessage, setjoinrole, autowarn, cc/clearchat, vkick, ipban, setserverlimit, whitelist", false)
                            .addField(Emojis2.CLIP_BOARD + "" + Emojis2.WHITE_SMALL_SQUARE + "**Profil**", "setdesc, setfriendcode, profile", false)
                            .addField("<:XP:590126610594922516>" + Emojis2.WHITE_SMALL_SQUARE + "**Keksiges-XP-System**", "xp, level", false)
                            .addField(Emojis2.INFORMATION_SOURCE + "" +  Emojis2.WHITE_SMALL_SQUARE +  " **Informationen**", "serverinfo, prefix, avatar, (ohne Prefix!)@KeksBot", false)
                            .addField(":musical_note: " + Emojis2.WHITE_SMALL_SQUARE + "**Keksiger MusikBot [BUG FIX]**", "**musik[BUG FIX]**", false)
                            .addField("<:ki:536488024431656980>" + Emojis2.WHITE_SMALL_SQUARE + "**KI [UPDATED]**", "ki",false)
                            .addField("<:customcmd:576127906959327242>" + Emojis2.WHITE_SMALL_SQUARE + "**Customcommands**", "customcmd/ccmd/customcmd",false)
                            .addField("<:globalchat:580795455013781505>" + Emojis2.WHITE_SMALL_SQUARE + "**Globalchat**", "gc/globalchat", false)
                            .addField("<:event:522761329853988864>" + Emojis2.WHITE_SMALL_SQUARE + "**Events [EVENT ENDET BALD]**", "event", false)
                            .addField(Emojis2.SMILING_FACE_WITH_OPEN_MOUTH_HAND_TIGHT + "" +  Emojis2.WHITE_SMALL_SQUARE + "**Spaß**", "deathbattle, keks, friendscore, würfel, randomkeks, rip, say, roleplay", false)
                            .addField(Emojis2.COOKIE + "" +  Emojis2.WHITE_SMALL_SQUARE + " **KeksBot [UPDATED}**", "**about[UPDATED}**, invite, vote, support, status, working, ping, speedtest", false)
                          //  .addField("Text-/AfKchannels:", event.getGuild().getTextChannels().size() + "/" + event.getGuild().getAfkChannel().getName(), true)
                            .setFooter("Insgesammt registrierte Befehle: " + CommandHandler.commands.size() + " \n Mehr Hilfe zu den einzeln Commands -> /help <command>", event.getJDA().getSelfUser().getAvatarUrl())
                            .build()).queue();
        else {

            switch (args[0].toLowerCase()) {
                case "setserverprefix":
                    sendMessage(event, Emojis2.GEAR + "" + Emojis2.WHITE_SMALL_SQUARE + " Hilfe **/setserverprefix**", "Mit `/setserverprefix` kannst du, als Server-Inhaber, den Prefix des Botes setzen, denn jeder nutzt, damit der Bot reagiert.");
                    break;
                case "setmyprefix":
                    sendMessage(event, Emojis2.GEAR + "" + Emojis2.WHITE_SMALL_SQUARE + " Hilfe **/setmyprefix**", "Mit `/setmyprefix` kannst du, den Prefix des Bots setzen, denn du nutzen kannst, damit der Bot reagiert.");
                    break;
                case "bugreport":
                    sendMessage(event, Emojis2.GEAR + "" + Emojis2.WHITE_SMALL_SQUARE + " Hilfe **/bugreport**", "Mit `/bugreport` kannst du, wenn du einen Bug gefunden hast, uns einen Bug senden, damit wir ihn fixen können.");
                    break;
                case "announcement":
                    sendMessage(event, Emojis2.PUBLIC_ADDRESS_LOUDSPEAKER + "" + Emojis2.WHITE_SMALL_SQUARE + " Hilfe **/announcement**", "Mit `/announcement` kannst du, als Team-Mitlgied, eine Mitteilung in den gesetzen `Announcement-Channel` senden.");
                    break;
                case "setannouncementchannel":
                    sendMessage(event, Emojis2.GEAR + "" + Emojis2.WHITE_SMALL_SQUARE + " Hilfe **/setannouncementchannel**", "Mit `/setannouncementchannel` kannst du, als Server-Inhaber, den Prefix des Bots setzen, denn jeder nutzt, damit der Bot reagiert.");
                    break;
                case "announcementchannel":
                    sendMessage(event, Emojis2.GEAR + "" + Emojis2.WHITE_SMALL_SQUARE + " Hilfe **/announcementchannel**", "Mit `/announcementchannel` kannst du, sehen, was der `Announcement-Channel` auf diesem Server ist.");
                    break;
                case "kick":
                    sendMessage(event, Emojis.WRENCH + "" + Emojis2.WHITE_SMALL_SQUARE + " Hilfe **/kick**", "Mit `/kick` kannst du, als Team-Mitlgied, ein User, mit einem Grund und einer automatischen PN an den gekickten, kicken.");
                    break;
                case "ban":
                    sendMessage(event, Emojis.WRENCH + "" + Emojis2.WHITE_SMALL_SQUARE + " Hilfe **/ban**", "Mit `/ban` kannst du, als Team-Mitlgied, ein User, mit einem Grund und einer automatischen PN an den gebannten, bannen.");
                    break;
                case "warn":
                    sendMessage(event, Emojis.WRENCH + "" + Emojis2.WHITE_SMALL_SQUARE + " Hilfe **/warn**", "Mit `/warn` kannst du, als Team-Mitlgied, ein User, mit einem Grund und einer automatischen Naricht in den `Announcement-Channel`, verwarnen.");
                    break;
                case "log":
                    sendMessage(event, Emojis.WRENCH + "" + Emojis2.WHITE_SMALL_SQUARE + " Hilfe **/log**", "Mit `/log` kannst du, sehen was der Log ist und wie du ihn aktivierts.");
                    break;
                case "setjoinmessage":
                    sendMessage(event, Emojis.WRENCH + "" + Emojis2.WHITE_SMALL_SQUARE + " Hilfe **/setjoinmessage**", "Mit `/setjoinmessage` kannst du, als Team-Mitglied, eine Naricht setzen, die in dem `Join-Channel` gesendet wird, wenn ein neuer User auf den Server kommt.");
                    break;
                case "setjoinchannel":
                    sendMessage(event, Emojis.WRENCH + "" + Emojis2.WHITE_SMALL_SQUARE + " Hilfe **/setjoinchannel**", "Mit `/setjoinchannel` kannst du, als Team-Mitglied, den Channel setzen,  in dem die `Join/Leavemessage` gesendet wird, wenn ein neuer User auf den Server kommt.");
                    break;
                case "setleavemessage":
                    sendMessage(event, Emojis.WRENCH + "" + Emojis2.WHITE_SMALL_SQUARE + " Hilfe **/setleavemessage**", "Mit `/setleavemessage` kannst du, als Team-Mitglied, eine Naricht setzen, die in dem `Join-Channel` gesendet wird, wenn ein neuer User den Server verlässt.");
                    break;
                case "joinchannel":
                    sendMessage(event, Emojis.WRENCH + "" + Emojis2.WHITE_SMALL_SQUARE + " Hilfe **/joinchannel**", "Mit `/joinchannel` kannst du sehen, wohin die Naricht gesendet wird, wenn ein neuer User auf den Server kommt.");
                    break;
                case "setjoinrole":
                    sendMessage(event, Emojis.WRENCH + "" + Emojis2.WHITE_SMALL_SQUARE + " Hilfe **/setjoinrole**", "Mit `/setjoinrole` kannst du, als Team-Mitglied, eine Rolle setzen, die ein User bekommt, wenn er auf den Server kommt.");
                    break;
                case "autowarn":
                    sendMessage(event, Emojis.WRENCH + "" + Emojis2.WHITE_SMALL_SQUARE + " Hilfe **/autowarn**", "Mit `/autowarn` kannst du sehen, wie du das automatische Warn System einstellst, und was es ist.");
                    break;
                case "cc":
                    sendMessage(event, Emojis.WRENCH + "" + Emojis2.WHITE_SMALL_SQUARE + " Hilfe **/cc**", "Mit `/cc` kannst du als, Team-Mitglied, den Chat clearen. \n Mit `/cc all` löschst du **ALLE** Narichten in dem aktiven Channel - **ACHTUNG: Dieser Vorgang kann nicht unterbrochen werden, und kann jenach größe des Channels, lange dauern und zu Bug führen!** \n Mit `/cc <Zahl>` löschst du so viele Narichten, wie du als Zahl vorgeben hast. \n Mit `/cc <Datum>` löscht du alle Narichten, von dem angebenen Datum. \n Mit `/cc` löschst du, die letzte Naricht.");
                    break;
                case "profile":
                    sendMessage(event, Emojis2.CLIP_BOARD + "" + Emojis2.WHITE_SMALL_SQUARE + " Hilfe **/profile**", "Mit `/profile` kannst du, von dir oder dem gepingten User, Userinfos sehen, und, falls der User, oder du, es mit `/setdesc` oder `/setfriendcode` eingestellt hast, den Freundescode und die Beschrebung von dir, oder dem gepingten User, sehen.");
                    break;
                case "setdesc":
                    sendMessage(event, Emojis2.CLIP_BOARD + "" + Emojis2.WHITE_SMALL_SQUARE + " Hilfe **/setdesc**", "Mit `/setdesc` kannst du die Beschrebung von dir setzen, die ihm `/profile` von dir angezeigt wird.");
                    break;
                case "setfriendcode":
                    sendMessage(event, Emojis2.CLIP_BOARD + "" + Emojis2.WHITE_SMALL_SQUARE + " Hilfe **/setfriendcode**", "Mit `/setfriendcode` kannst du deinen (Switch-)Freundescode setzen, der ihm `/profile` von dir angezeigt wird.");
                    break;
                case "serverinfo":
                    sendMessage(event, Emojis2.INFORMATION_SOURCE + "" + Emojis2.WHITE_SMALL_SQUARE + " Hilfe **/serverinfo**", "Mit `/serverinfo` kannst du, wichtige Infos des Servers sehen. **Achtung:** Dafür musst du alles eingestellt haben");
                    break;
                case "prefix":
                    sendMessage(event, Emojis2.INFORMATION_SOURCE + "" + Emojis2.WHITE_SMALL_SQUARE + " Hilfe **/prefix**", "Mit `/prefix` deinen Prefix sehen, oder den des Servers.");
                    break;
                case "avatar":
                    sendMessage(event, Emojis2.INFORMATION_SOURCE + "" + Emojis2.WHITE_SMALL_SQUARE + " Hilfe **/avatar**", "Mit `/avatar` kannst du dein Avatar sehen, oder des gepingten Servers.");
                    break;
                case "musik":
                    sendMessage(event, ":musical_note:" + Emojis2.WHITE_SMALL_SQUARE + " Hilfe **/musik**", "Um alle Commands, und deren Erklärung, des MusikBots zu erhalten, gebe `/musik` ein.");
                    break;
                case "ki":
                    sendMessage(event, "<:ki:536488024431656980>" + Emojis2.WHITE_SMALL_SQUARE + " Hilfe **/ki**", "Mit `/ki` gebekommst du eine Erklärung was das ist und wie es Funktioniert.");
                    break;
                case "event":
                    sendMessage(event, "<:event:522761329853988864>" + Emojis2.WHITE_SMALL_SQUARE + "<:X_:504673235212697601> | Fehler ", "Dieser Command ist derzeit deaktiviert.");
                    break;
                case "deathbattle":
                    sendMessage(event, Emojis2.SMILING_FACE_WITH_OPEN_MOUTH_HAND_TIGHT + "" + Emojis2.WHITE_SMALL_SQUARE + " Hilfe **/deathbattle**", "Mit `/deathbattle` kannst du, ein Spannendes Battle gegen einen Freund, oder Feind, starten.");
                    break;
                case "keks":
                    sendMessage(event, Emojis2.SMILING_FACE_WITH_OPEN_MOUTH_HAND_TIGHT + "" + Emojis2.WHITE_SMALL_SQUARE + " Hilfe **/keks**", "Mit `/keks` kannst du, dir ein leckeren Keks backen lassen :D");
                    break;
                case "würfel":
                    sendMessage(event, Emojis2.SMILING_FACE_WITH_OPEN_MOUTH_HAND_TIGHT + "" + Emojis2.WHITE_SMALL_SQUARE + " Hilfe **/würfel**", "Mit `/würfel` kannst du, einen Würfel würfel. Wow, erklärt sich doch von selbst oder?");
                    break;
                case "friendscore":
                    sendMessage(event, Emojis2.SMILING_FACE_WITH_OPEN_MOUTH_HAND_TIGHT + "" + Emojis2.WHITE_SMALL_SQUARE + " Hilfe **/friendscore**", "Mit `/friendscore` kannst du herausfinden, wie sehr du mit einem User zusammenpasst.");
                    break;
                case "about":
                    sendMessage(event, Emojis2.COOKIE + "" + Emojis2.WHITE_SMALL_SQUARE + " Hilfe **/about**", "Mit `/about` kannst du alles über mich herausfinden.");
                    break;
                case "invite":
                    sendMessage(event, Emojis2.COOKIE + "" + Emojis2.WHITE_SMALL_SQUARE + " Hilfe **/invite**", "Mit `/invite` kannst du mich zu deiner Party einladen. Bitte ich will ein Teil deiner Party sein uwu");
                    break;
                case "support":
                    sendMessage(event, Emojis2.COOKIE + "" + Emojis2.WHITE_SMALL_SQUARE + " Hilfe **/support**", "Oh, ich gehe bei dir nicht richtig :/ Dann gebe `/support` ein und finde heraus, wie meine Devs dir helfen können.");
                    break;
                case "status":
                    sendMessage(event, Emojis2.COOKIE + "" + Emojis2.WHITE_SMALL_SQUARE + " Hilfe **/status**", "Sehe was bei mir so ab geht und checke ob ich richtig Funktioniere.");
                    break;
                case "working":
                    sendMessage(event, Emojis2.COOKIE + "" + Emojis2.WHITE_SMALL_SQUARE + " Hilfe **/working**", "Keiner ist Perfekt, auch nicht ich. Sehe auf was du dich in Zukunft freuen kannst!");
                    break;
                case "ping":
                    sendMessage(event, Emojis2.COOKIE + "" + Emojis2.WHITE_SMALL_SQUARE + " Hilfe **/ping**", "Finde meine Reaktions Zeit heraus.");
                    break;
                case "vote":
                    sendMessage(event, Emojis2.COOKIE + "" + Emojis2.WHITE_SMALL_SQUARE + " Hilfe **/vote**", "Unterstütze mich, kostenlos :D");
                    break;
                case "speedtest":
                    sendMessage(event, Emojis2.COOKIE + "" + Emojis2.WHITE_SMALL_SQUARE + " Hilfe **/speedtest**", "Wie gut ist das Internet, des Bots?");
                    break;
                case "vkick":
                    sendMessage(event, Emojis.WRENCH + "" + Emojis2.WHITE_SMALL_SQUARE + " Hilfe **/vkick <Zeit des Timebans>**", "Kicke einen User aus einem Channel! \n Oder Timebanne ihn sogar!");
                    break;
                case "xp":
                    sendMessage(event, Emojis.WRENCH + "" + Emojis2.WHITE_SMALL_SQUARE + " Hilfe **/xp <@User>**", "XP System deaktivieren: `/xp enable` \n XP-Liste: \n **20 XP** = Level **1** \n **100 XP** = Level **2** \n **500 XP** = Level **3** \n **700 XP** = Level **4** \n **1000 XP** = Level **5** \n **1500 XP** = Level **6** \n **2000 XP** = Level **7** \n **2800 XP** = Level **8** \n **4200 XP** = Level **9** \n **5400 XP** = Level **10** \n **8900 XP** = Level **11** \n **10000 XP** = Level **13** \n **15000 XP** = Level **14** \n **20000 XP** = Level **15/max**");
                    break;
                case "roleplay":
                    sendMessage(event, Emojis.WRENCH + "" + Emojis2.WHITE_SMALL_SQUARE + " Hilfe **/roleplay @User**", "Verschiedene Gifs, die zum roleplay verwendet werden können \n SubcmdList: **/roleplay wink @User** - winke jemanden zu \n **/roleplay hug @User** - Umarme jemanden \n **/roleplay kiss @User** - Küsse jemanden \n **/roleplay kill @User** - Töte jemanden");
                    break;
                case "umfrage":
                    sendMessage(event, Emojis.WRENCH + "" + Emojis2.WHITE_SMALL_SQUARE + " Hilfe **/umfrage <subcmd>**", "Erstelle eine Umfrage und lasse die User abstimmen! \n SubcmdList: **/umfrage create <Titel>|<Antwort1>|<Antwort2>|...** - erstelle eine Umfrage \n **/umfrage secret <Titel>|<Antwort1>|<Antwort2>|...** - Erstelle eine geheime Umfrage, die man nur in deinem Channel sieht \n **/umfrage stats** - Die aktuellen Stats \n **/roleplay close** - Beende deine Umfrage");
                    break;
                case "customcmd":
                    sendMessage(event, Emojis.WRENCH + "" + Emojis2.WHITE_SMALL_SQUARE + " Hilfe **/customcmd <subcmd>**", "Setze einen Customcmd! \n SubcmdList: **/umfrage add <Command> <Antwort>** - füge einen hinzu! \n **/umfrage list** - Sehe alle CustomCommands auf diesem Server");
                    break;
                case "globalchat":
                    sendMessage(event, "<:globalchat:580795455013781505>" + Emojis2.WHITE_SMALL_SQUARE + " Hilfe **/globalchat**", "Erstelle einen Channel mit dem Namen `keksiger-globalchat`, um mit allen Usern des KeksBottes zu chatten");
                    break;
                case "whitelist":
                    sendMessage(event, Emojis.WRENCH + "" + Emojis2.WHITE_SMALL_SQUARE + " Hilfe **/whitelist**", "Um die Whitelist zu aktivieren gebe ``/whitelist enable`` ein, damit kannst du sie auch wieder deaktivieren. \n Um einen User auf die Whitelist hinzuzufügen gebe `/whitelist <UserId>` ein. \n Wenn die Whitelist aktiviert ist, können nur noch die Leute auf den Server, die auf der Whitelist sind.");
                    break;
                case "ipban":
                    sendMessage(event, Emojis.WRENCH + "" + Emojis2.WHITE_SMALL_SQUARE + " Hilfe *ipban**", " Um einen User IP zu bannen, auch bevor er auf dem Server ist, gebe `/ipban <UserId>` ein. \n Der User kommt dann nicht mehr auf den Server bis du ``/ipban remove <UserId>`` eingibst.");
                    break;
                default:
                    sendMessage(event, "<:X_:504673235212697601> | Fehler ", "Dieser **Command** wurde **nicht gefunden**, oder **es gibt** derzeit noch **keine Hilfe dafür**. \n Falls dies der **Fall** sein sollte, gebe bitte auf unserem **Support Server `/support`** ein.");


            }
        }
    }


    @Override
    public void executed(boolean sucess, MessageReceivedEvent event) {
        System.out.println("[Info] Der Command help wurde ausgeführt!");

    }

    @Override
    public  String  help() {

        return null;
    }



}

