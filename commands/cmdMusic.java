package commands;

import audioCore.AudioInfo;
import audioCore.AudioPlayerSendHandler;
import audioCore.TrackManager;
import com.sedmelluq.discord.lavaplayer.player.AudioLoadResultHandler;
import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import com.sedmelluq.discord.lavaplayer.player.AudioPlayerManager;
import com.sedmelluq.discord.lavaplayer.player.DefaultAudioPlayerManager;
import com.sedmelluq.discord.lavaplayer.source.AudioSourceManagers;
import com.sedmelluq.discord.lavaplayer.tools.FriendlyException;
import com.sedmelluq.discord.lavaplayer.track.AudioPlaylist;
import com.sedmelluq.discord.lavaplayer.track.AudioTrack;
import com.sedmelluq.discord.lavaplayer.track.AudioTrackInfo;
import core.restHandler;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.Permission;
import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.Member;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import util.STATIC;

import java.awt.*;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

public class cmdMusic implements Command {

    private List<AudioTrack> endlessList = new ArrayList<>();

    private static final int PLAYLIST_LIMIT = 1000;
    private static Guild guild;
    private static final AudioPlayerManager MANAGER = new DefaultAudioPlayerManager();
    private static final Map<Guild, Map.Entry<AudioPlayer, TrackManager>> PLAYERS = new HashMap<>();

    private AudioPlayer createPlayer(Guild g) {
        AudioPlayer p = MANAGER.createPlayer();
        TrackManager m = new TrackManager(p);
        p.addListener(m);

        guild.getAudioManager().setSendingHandler(new AudioPlayerSendHandler(p));

        PLAYERS.put(g, new AbstractMap.SimpleEntry<>(p, m));

        return p;

    }

    private boolean hasPlayer(Guild g) {
        return PLAYERS.containsKey(g);
    }

    private AudioPlayer getPlayer(Guild g) {
        if (hasPlayer(g))
            return PLAYERS.get(g).getKey();
        else
            return createPlayer(g);
    }

    private TrackManager getManager(Guild g) {
        return PLAYERS.get(g).getValue();
    }

    private boolean isIdle(Guild g) {
        return !hasPlayer(g) || getPlayer(g).getPlayingTrack() == null;
    }

    private void loadTrack(String identifier, Member author, Message msg) {


        Guild guild = author.getGuild();
        getPlayer(guild);

        msg.getTextChannel().sendTyping().queue();
        MANAGER.setFrameBufferDuration(1000);
        MANAGER.loadItemOrdered(guild, identifier, new AudioLoadResultHandler() {
            @Override
            public void trackLoaded(AudioTrack track) {

                getManager(guild).queue(track, author);
            }

            @Override
            public void playlistLoaded(AudioPlaylist playlist) {
                if (playlist.getSelectedTrack() != null) {
                    trackLoaded(playlist.getSelectedTrack());
                } else if (playlist.isSearchResult()) {
                    trackLoaded(playlist.getTracks().get(0));
                } else {

                    for (int i = 0; i < Math.min(playlist.getTracks().size(), PLAYLIST_LIMIT); i++) {
                        getManager(guild).queue(playlist.getTracks().get(i), author);
                    }
                }
            }

           @Override
            public void noMatches() {
            }

            @Override
            public void loadFailed(FriendlyException exception) {
                exception.printStackTrace();
           }
        });
    }

    private void skip(Guild g) {
        getPlayer(g).stopTrack();
    }

    private static String formatTiming(long timing, long maximum) {
        timing = Math.min(timing, maximum) / 1000;

        long seconds = timing % 60;
        timing /= 60;
        long minutes = timing % 60;
        timing /= 60;
        long hours = timing;

        if (maximum >= 3600000L) {
            return String.format("%d:%02d:%02d", hours, minutes, seconds);
        } else {
            return String.format("%d:%02d", minutes, seconds);
        }
    }

    private String buildQueueMessage(AudioInfo info) {
        AudioTrackInfo trackInfo = info.getTrack().getInfo();
        AudioTrack track = info.getTrack();
        String title = trackInfo.title;
        String author = trackInfo.author;
        return   "**" + title + "** von **" + author + " ** `[" + formatTiming(track.getPosition(), track.getDuration()) + " - " + formatTiming(track.getDuration(), track.getDuration()) + "]`\n";
    }

    private void sendErrorMsg(MessageReceivedEvent event, String content) {
        event.getTextChannel().sendMessage( new EmbedBuilder().setColor(Color.RED)
                .setDescription("<:X_:504673235212697601> | " + content)
                .build()
        ).queue();
    }
    private void sendSuccsesMsg(MessageReceivedEvent event, String content) {
        event.getTextChannel().sendMessage(
                new EmbedBuilder().setDescription(":musical_note:  | " + content).setColor(new Color(0, 255, 151)).build()
        ).queue();
    }
    private void loadTrackNext(String identifier, Member author, Message msg) {


        Guild guild = author.getGuild();
        getPlayer(guild);

        msg.getTextChannel().sendTyping().queue();
        MANAGER.setFrameBufferDuration(100);
        MANAGER.loadItemOrdered(guild, identifier, new AudioLoadResultHandler() {
            @Override
            public void trackLoaded(AudioTrack track) {

                AudioInfo currentTrack = getManager(guild).getQueuedTracks().iterator().next();
                Set<AudioInfo> queuedTracks = getManager(guild).getQueuedTracks();
                queuedTracks.remove(currentTrack);
                getManager(guild).purgeQueue();
                getManager(guild).queue(currentTrack.getTrack(), author);
                getManager(guild).queue(track, author);
                queuedTracks.forEach(audioInfo -> getManager(guild).queue(audioInfo.getTrack(), author));
            }

            @Override
            public void playlistLoaded(AudioPlaylist playlist) {
                if (playlist.getSelectedTrack() != null) {
                    trackLoaded(playlist.getSelectedTrack());
                } else if (playlist.isSearchResult()) {
                    trackLoaded(playlist.getTracks().get(0));
                } else {
                    AudioInfo currentTrack = getManager(guild).getQueuedTracks().iterator().next();
                    Set<AudioInfo> queuedTracks = getManager(guild).getQueuedTracks();
                    queuedTracks.remove(currentTrack);
                    getManager(guild).purgeQueue();
                    getManager(guild).queue(currentTrack.getTrack(), author);
                    for (int i = 0; i < Math.min(playlist.getTracks().size(), PLAYLIST_LIMIT); i++) {
                        getManager(guild).queue(playlist.getTracks().get(i), author);
                    }
                    queuedTracks.forEach(audioInfo -> getManager(guild).queue(audioInfo.getTrack(), author));
                }
            }
            @Override
            public void noMatches() {
            }

            @Override
            public void loadFailed(FriendlyException exception) {
            }
        });

    }
    public cmdMusic() {
        AudioSourceManagers.registerRemoteSources(MANAGER);
    }
            @Override
    public boolean called(String[] args, MessageReceivedEvent event) {
        return false;
    }

    @Override
    public void action(String[] args, MessageReceivedEvent event) {
        restHandler.setCommands(event.getGuild(), restHandler.getCommands(event.getGuild()));



        guild = event.getGuild();

        if (args.length  < 1) {
            event.getTextChannel().sendMessage(new EmbedBuilder().setColor(Color.cyan)
                    .setTitle("Keksiger_MusikBot | Version 3.0.0 - Hilfe").setDescription("**Prefix** für alle MusikBot Befehle: **`/musik`** \n\n\n » **play (Link/Name)** \n ➥ Der **Bot joint deinem Channel** und **spielt** deine **Musik** \n ➜ Unterstützte Formate: \n ➥ `/musik supported-formats` \n\n » **skip <Nummer>** \n➥ Überspringt einen oder mehrere Tracks \n\n » **queue** \n➥ Zeigt die aktuelle Warteschleife \n\n » **stop** \n➥ Der Bot verlässt den Voice-Channel und löscht die Warteschleife \n\n » **vol** <Nummer> \n➥ Zeigt die aktuelle Lautstärke an oder stellt die Lautstärke des Bots ein \n\n » **shuffle** \n➥ Die Playlist wird wiederholt. \n\n » **playing/now/info** \n➥ Zeigt die aktuellen Track-Infos an \n\n » **pause/resume** \n➥ Pausiert / Startet den aktuellen Track \n\n » **mute/unmute** \n➥ Muted den Bot / Entmuted den Bot \n\n » **status** \n➥ Zeige dir an, ob der MusikBot funktioniert")
                    .build()).queue();
            return;
        }
        switch (args[0].toLowerCase()) {


            case "play":
            case "p":

                String input = String.join(" ", Arrays.copyOfRange(args, 1, args.length));
                if (input != null && input.startsWith("https://"))
                STATIC.input = input;
                else
                   input = "ytsearch: " + input;

                if (args.length  <= 1) {
                    sendErrorMsg(event, "Bitte gebe an, was gespielt werden soll!");

                } else {
                    Message loading = event.getTextChannel().sendMessage(new EmbedBuilder().setColor(Color.ORANGE).setDescription(":mag_right: | Suche nach **``" + input + "``** auf **YouTube**...").build()).complete();
                    loadTrack(input, event.getMember(), event.getMessage());

                    if (getPlayer(guild).isPaused())
                        getPlayer(guild).setPaused(false);

                    new Timer().schedule(
                            new java.util.TimerTask() {
                                @Override
                                public void run() {
                                        AudioTrack track = getPlayer(guild).getPlayingTrack();
                                    String duration = formatTiming(track.getDuration(), track.getDuration());
                                    String position = formatTiming(track.getPosition(), track.getDuration());
                                    if (track == null){
                                        sendErrorMsg(event, "Ich habe keine Lust mir selber deinen Track vorzuspielen, bitte komm doch dazu und geben den Command erneut ein. ");

                                    } else {
                                        loading.delete().queue();
                                        int tracks = getManager(guild).getQueuedTracks().size();
                                        AudioTrackInfo info = track.getInfo();
                                        sendSuccsesMsg(event, "Ich spiele nun das hier für dich: \n\n » **Titel** \n ➥ ``" + info.title + "`` \n\n » **YTber** \n ➥ ``" + info.author + "`` \n\n » **Lautstärke** \n ➥ `" + getPlayer(guild).getVolume() + "%` \n\n » **Länge** \n ➥ `" + duration + "` \n\n » **Link** \n  ➥ [Klick mich](" + info.uri + ") \n\n\n » Nun sind/ist `" + tracks + "` Track(s) in der Warteschleife.");
                                        //}
                                    }

                                }
                            },
                            10000
                    );
                }
                break;




            case "skip":
            case "s":

                if (isIdle(guild)){
                    sendErrorMsg(event, "Derzeit läuft kein MusikTrack!");
                    return;
                }
                for (int i = (args.length > 1 ? Integer.parseInt(args[1]) : 1); i == 1; i--) {
                    if (event.getMember().hasPermission(Permission.VOICE_MUTE_OTHERS)) {
                        skip(guild);
                        event.getTextChannel().sendMessage(
                                new EmbedBuilder().setDescription(":fast_forward:  | `1` Track wurde übersprungen!").setColor(new Color(0, 255, 151)).build()
                        ).queue();
                    } else {
                        sendErrorMsg(event, "Du hast nicht die benötigten Rechte (`VOICE_MUTE_OTHERS`)");
                    }

                }

            break;
            case "stop":

                if(isIdle(guild)) {
                    sendErrorMsg(event, "Derzeit läuft kein MusikTrack!");
                    return;
                }

                getManager(guild).purgeQueue();
                skip(guild);
                guild.getAudioManager().closeAudioConnection();
                sendSuccsesMsg(event, "Ich habe den Sprachkanal verlassen.");

            break;
            case "shuffle":

                if(isIdle(guild)) {
                    sendErrorMsg(event, "Derzeit läuft kein MusikTrack!");
                    return;
                }
                getManager(guild).shuffleQueue();
                sendSuccsesMsg(event, "Die Playlist/Der Song wird nun wiederholt.");
            break;
            case "now":
            case "info":
            case "playing":

                if(isIdle(guild)) {
                    sendErrorMsg(event, "Derzeit läuft kein MusikTrack!");
                    return;
                }

                AudioTrack track = getPlayer(guild).getPlayingTrack();
                AudioTrackInfo info = track.getInfo();
                String duration = formatTiming(track.getDuration(), track.getDuration());
                String position = formatTiming(track.getPosition(), track.getDuration());

                sendSuccsesMsg(event, "Ich spiele derzeit das hier für dich: \n\n » **Titel** \n ➥ ``" + info.title + "`` \n\n » **YTber** \n ➥ ``" + info.author + "`` \n\n » **Lautstärke** \n ➥ `" + getPlayer(guild).getVolume() + "%` \n\n » **Position** \n ➥ `" + position + "` von `" + duration + "`\n\n » **Link** \n  ➥ [Klick mich](" + info.uri + ")");
            break;
            case "queue":
                if(isIdle(guild)) {
                    sendErrorMsg(event, "Derzeit läuft kein MusikTrack!");
                    return;
                }
                try {
                    int sideNumb = args.length > 1 ? Integer.parseInt(args[1]) : 1;


                    List<String> tracks = new ArrayList<>();
                    List<String> trackSublist;

                    getManager(guild).getQueuedTracks().forEach(audioInfo -> tracks.add(buildQueueMessage(audioInfo)));

                    if (tracks.size() > 20)
                        trackSublist = tracks.subList((sideNumb - 1) * 20, (sideNumb - 1) * 20 + 20);

                    else
                        trackSublist = tracks;


                    String out = trackSublist.stream().collect(Collectors.joining("\n"));
                    int sideNumbAll = tracks.size() >= 20 ? tracks.size() / 20 : 1;
                    AudioTrack track2 = getPlayer(guild).getPlayingTrack();
                    AudioTrackInfo info2 = track2.getInfo();
                    String duration2 = formatTiming(track2.getDuration(), track2.getDuration());
                    String position2 = formatTiming(track2.getPosition(), track2.getDuration());
                    if (tracks.size() == 1) {
                        sendSuccsesMsg(event, "Aktuelle Warteschleife auf " + event.getGuild().getName()
                                + "\n **" + getManager(guild).getQueuedTracks().size() + " Track(s)** | **Seite " + sideNumb + " / " + sideNumbAll + "** \n \n \n » Derzeit spielt \n ➥ **" + info2.title + "** von **" + info2.author + "** " +
                                "`[" + position2 + " - " + duration2 + "]` \n\n\n » Es gibt keine weiteren Tracks in der Warteschleife.");


                    } else {

                        sendSuccsesMsg(event, "Aktuelle Warteschleife auf " + event.getGuild().getName()
                                + " \n **" + getManager(guild).getQueuedTracks().size() + " Track(s)** | **Seite " + sideNumb + " / " + sideNumbAll + "** \n \n \n » Derzeit spielt \n ➥ **" + info2.title + "** von **" + info2.author + "** " +
                                "`[" + position2 + " - " + duration2 + "]` " +
                                "\n \n \n » Die nächsten Lieder sind \n ➥ " + out);
                    }

                    } catch(Exception e){
                        sendErrorMsg(event, "Du musst eine **Zahl**, kein **Buchstaben**, angeben!");
                }
                break;
            case "pause":
            case "resume":
                if (getPlayer(guild).isPaused()) {
                    getPlayer(guild).setPaused(false);
                    sendSuccsesMsg(event, "Musik gestartet.");
                } else {
                    getPlayer(guild).setPaused(true);
                    sendSuccsesMsg(event, "Musik pausiert.");
                }
                break;
            case "pn":
            case "playnext":
                String input2 = String.join(" ", Arrays.copyOfRange(args, 1, args.length));

                if (args.length <= 1) {
                   sendErrorMsg(event,"Dieses Modul kann Bugs beinhalten, dies war wohl einer qwq");
                } else {
                    sendErrorMsg(event,"Dieses Modul kann Bugs beinhalten, dies war wohl einer qwq");

//                    new Timer().schedule(
//                            new java.util.TimerTask() {
//                                @Override
//                                public void run() {
//                                    AudioTrack track = getPlayer(guild).getPlayingTrack();
//                                    if (track == null){
//                                        sendErrorMsg(event, "Dieser Command wurde auf Grund eines **Bugs** deaktiviert.");
//
//                                    } else {
//                                        int tracks = getManager(guild).getQueuedTracks().size();
//                                        AudioTrackInfo info = track.getInfo();
//                                        sendSuccsesMsg(event,"**`" + info.title + "`** von **`" + info.author + "`** zur Warteschleife hinzugefügt. \n Nun sind/ist `" + tracks + "` Track(s) in der Warteschleife.");
//                                    }
//                                }
//                            },
//                            5000
//                    );
                }
                break;
            case "endless":

                getManager(guild).getQueuedTracks().stream().skip(1).forEach(t -> endlessList.add(t.getTrack()));
                boolean endlessMode = true;
                Member endlessAuthor = event.getMember();

                sendErrorMsg(event,"Dieses Modul kann Bugs beinhalten, dies war wohl einer qwq");

                break;
            case "vol":
                if(args.length == 1) {
                    sendSuccsesMsg(event,"Die aktuelle Lautstärke ist **" + getPlayer(guild).getVolume() + "%**");
                } else {


                    if (args[1].length() > 2) {
                        event.getTextChannel().sendMessage(
                                new EmbedBuilder().setColor(Color.ORANGE)
                                        .setDescription("<:confirm:509070945948925962> | **AUA**, das tut mir (und dir?) weh :( Aber ich höhre auf dich, egal wie viel!")
                                        .build()
                        ).queue();
                    }
                    try {
                        int vol = Integer.parseInt(args[1]);
                        getPlayer(guild).setVolume(vol);
                        sendSuccsesMsg(event, "Neue Lautstärke: **" + vol + "%**");
                    } catch (Exception e) {
                        sendErrorMsg(event, "Entweder musst du eine **Zahl**, keinen **Buchstabe**, angeben oder deine **angebene Zahl**, war **zu laut** für mich und meine Ohren wären tot.");
                    }
                }
                break;
            case "mute":
                getPlayer(guild).setVolume(0);
                sendSuccsesMsg(event, "Der Bot ist nun **gemutet**");
            break;
            case "unmute":
                getPlayer(guild).setVolume(50);
                sendSuccsesMsg(event, "Der Bot wurde **entmutet**");
            break;
            case "status":
                int guilds = event.getJDA().getGuilds().size();
                if (event.getJDA().getPing() < 300)
                sendSuccsesMsg(event, "Da mein **Ping " + event.getJDA().getPing() + "ms** ist, sollte der MusikBot **ohne Probleme** funktionieren. \n \n Weitere Infos: \n Aktuelle Lautstärke: **" + getPlayer(guild).getVolume() + "** \n Ich spiele derzeit, auf **" + guilds + " Servern**, mit **" + event.getJDA().getUsers().size() + " Usern**, tolle Spiele.");
                else {
                event.getTextChannel().sendMessage(new EmbedBuilder().setColor(Color.ORANGE)
                .setDescription(":warning: | Da mein **Ping " + event.getJDA().getPing() + "ms** ist, könnte es zu ** Problemen** kommen!")
                .build()).queue();

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
