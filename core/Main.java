package core;

import commands.*;
import listeners.JdaEventListener;
import listeners.RegisterListener;
import listeners.commandListener;
import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;
import org.discordbots.api.client.DiscordBotListAPI;
import util.SECRETS;

import javax.security.auth.login.LoginException;

public class Main {

    public static  void  main(String[] Args) {

        try {


            JDA jda = new JDABuilder("Your-Token-Goes-Here").setToken(SECRETS.TOKEN).setAutoReconnect(true)// The token of the account that is logging in.

                    //Listeners
                    .addEventListener(new RegisterListener())
                    .addEventListener(new JdaEventListener())
                    // An instance of a class that will handle events.

                    //.setGame(Game.listening("/help " + Emojis2.WHITE_SMALL_SQUARE + " by Sebl"))
                    .build();


            jda.awaitReady(); // Blocking guarantees that JDA will be completely loaded.

            DiscordBotListAPI api = new DiscordBotListAPI.Builder()
                    .token("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6IjQ5MzA2NjM4NzE4MzYzMjM4NyIsImJvdCI6dHJ1ZSwiaWF0IjoxNTY1NTU1MjY5fQ.zsDZKb2eCyu48GeAr32dBAyGCWz9hHSiBFeKv9Pk8j8")
                    .botId("493066387183632387")
                    .build();
            int serverCount = jda.getGuilds().size(); // the total amount of servers across all shards

            api.setStats(serverCount);

            cmdAbout.load();


            //Handlers
            SetJoinChannel.loadJoinChannels();
            PrefixHandler.loadPrefixes();
            ProfileHandler.loadProfile();
            ChannelHandler.loadChannels();
            JoinMessageHandler.loadJoinMessages();
            LeaveMessageHandler.loadLeaveMessages();
            JoinMessageHandler.loadJoinMessages();
            JoinRoleHandler.loadRoles();
            //   XPHandler.loadXP();

            //Commands
            CommandHandler.commands.put("keks", new cmdKeks());
            CommandHandler.commands.put("help", new cmdHelp());
            CommandHandler.commands.put("deathbattle", new cmdDeathbattle());
            CommandHandler.commands.put("answerdeathbattle", new cmdAnswerDeathBattle());
            CommandHandler.commands.put("friendscore", new cmdFriendscore());
            CommandHandler.commands.put("answerfriendscore", new cmdAnswerfriendscore());
            CommandHandler.commands.put("serverinfo", new cmdServerinfo());
            CommandHandler.commands.put("userinfo", new cmdUserinfo());
            CommandHandler.commands.put("prefix", new cmdPrefix());
            CommandHandler.commands.put("about", new cmdAbout());
            CommandHandler.commands.put("würfel", new cmdWürfel());
            CommandHandler.commands.put("setserverprefix", new cmdSetServerPrefix());
            CommandHandler.commands.put("setmyprefix", new cmdSetMyPrefix());
            CommandHandler.commands.put("randomkeks", new cmdRandomKeks());
            CommandHandler.commands.put("answerkeks", new cmdAnswerKeks());
            CommandHandler.commands.put("bugreport", new cmdBugReport());
            CommandHandler.commands.put("rip", new cmdRip());
            CommandHandler.commands.put("say", new cmdSay());
            CommandHandler.commands.put("register", new cmdRegister());
            CommandHandler.commands.put("kick", new cmdKick());
            CommandHandler.commands.put("warn", new cmdWarn());
            CommandHandler.commands.put("ban", new cmdBan());
            CommandHandler.commands.put("setdesc", new cmdSetDesc());
            CommandHandler.commands.put("setfriendcode", new cmdSetFriendCode());
            CommandHandler.commands.put("profile", new cmdProfile());
            CommandHandler.commands.put("setannouncementchannel", new cmdSetAnnouncementChannel());
            CommandHandler.commands.put("announcementchannel", new cmdAnnouncementChannel());
            CommandHandler.commands.put("announcement", new cmdAnnouncement());
            CommandHandler.commands.put("event", new cmdEvent());
            CommandHandler.commands.put("setjoinchannel", new cmdSetJoinChannel());
            CommandHandler.commands.put("setjoinmessage", new cmdSetJoinMessage());
            CommandHandler.commands.put("log", new cmdLog());
            CommandHandler.commands.put("answergift", new cmdAnswerGift());
            CommandHandler.commands.put("invite", new cmdInvite());
            CommandHandler.commands.put("vote", new cmdVote());
            CommandHandler.commands.put("support", new cmdSupport());
            CommandHandler.commands.put("joinchannel", new cmdJoinChannel());
            CommandHandler.commands.put("setleavemessage", new cmdSetLeaveMessage());
            CommandHandler.commands.put("roykoopa", new cmdRayKoopa());
            CommandHandler.commands.put("shutdown", new cmdShutdown());
            CommandHandler.commands.put("ki", new cmdKI());
            CommandHandler.commands.put("autowarn", new cmdAutoWarn());
            CommandHandler.commands.put("musik", new cmdMusic());
            CommandHandler.commands.put("cc", new cmdClearChat());
            CommandHandler.commands.put("clearchat", new cmdClearChat());
            CommandHandler.commands.put("status", new cmdStatus());
            CommandHandler.commands.put("working", new cmdWorking());
            CommandHandler.commands.put("setjoinrole", new cmdSetJoinRole());
            CommandHandler.commands.put("roundmail", new cmdRoundMail());
            CommandHandler.commands.put("setlog", new cmdSetLog());
            CommandHandler.commands.put("ping", new cmdPing());
            CommandHandler.commands.put("avatar", new cmdAvatar());
            CommandHandler.commands.put("id", new cmdTest());
            CommandHandler.commands.put("vkick", new cmdVKick());
            CommandHandler.commands.put("speedtest", new cmdSpeedtest());
            CommandHandler.commands.put("uptime", new cmdUptime());
            CommandHandler.commands.put("mute", new cmdMute());
            CommandHandler.commands.put("snick", new cmdNick());
            CommandHandler.commands.put("xp", new cmdXP());
            CommandHandler.commands.put("level", new cmdLevel());
            CommandHandler.commands.put("stats", new cmdStats());
            CommandHandler.commands.put("roleplay", new cmdRolePlay());
            CommandHandler.commands.put("rp", new cmdRolePlay());
            CommandHandler.commands.put("umfrage", new cmdUmfrage());
            CommandHandler.commands.put("ccmd", new cmdCustomCommand());
            CommandHandler.commands.put("customcmd", new cmdCustomCommand());
            CommandHandler.commands.put("customcommand", new cmdCustomCommand());
            //CommandHandler.commands.put("geburtstag", new cmdWeihnachtsEvent());
            CommandHandler.commands.put("globalchat", new cmdGlobalchat());
            CommandHandler.commands.put("gc", new cmdGlobalchat());
            CommandHandler.commands.put("timer", new cmdTimer());
            CommandHandler.commands.put("setserverlimit", new cmdSetLimit());
            CommandHandler.commands.put("idban", new cmdIDBan());
            CommandHandler.commands.put("whitelist", new cmdWhitelist());
            CommandHandler.commands.put("swe", new cmdWithoutEmbed());
            CommandHandler.commands.put("test", new cmdBild());
            CommandHandler.commands.put("setgb", new cmdSetGB());

            System.out.println("JDA wurde gebaut! \n " + CommandHandler.commands.size() + " Befehle geladen!");
        } catch (LoginException e) {
            //If anything goes wrong in terms of authentication, this is the exception that will represent it
            e.printStackTrace();
        } catch (InterruptedException e) {
            //Due to the fact that awaitReady is a blocking method, one which waits until JDA is fully loaded,
            // the waiting can be interrupted. This is the exception that would fire in that situation.
            //As a note: in this extremely simplified example this will never occur. In fact, this will never occur unless
            // you use awaitReady in a thread that has the possibility of being interrupted (async thread usage and interrupts)
            e.printStackTrace();


  /*      JDABuilder builder = new JDABuilder (AccountType.BOT);

        builder.setToken(SECRETS.TOKEN);
        builder.setAutoReconnect(true);

        builder.setStatus(OnlineStatus.DO_NOT_DISTURB);

        addListeners();
        addCommands();

        try {
            JDA jda = builder.buildBlocking();
        }catch (LoginException e) {
            e.printStackTrace();

        }catch (InterruptedException e) {
            e.printStackTrace();

        }*/

        }
    }

    public static void addCommands() {
        CommandHandler.commands.put("keks", new cmdKeks());
    }


    public static void addListeners() {

        JDABuilder builder = new JDABuilder (AccountType.BOT);

        builder.addEventListener(new commandListener());
        builder.addEventListener(new JdaEventListener());
    }

}
