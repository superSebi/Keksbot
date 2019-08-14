package commands;

import fr.bmartel.speedtest.SpeedTestReport;
import fr.bmartel.speedtest.SpeedTestSocket;
import fr.bmartel.speedtest.inter.ISpeedTestListener;
import fr.bmartel.speedtest.model.SpeedTestError;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import util.Emojis2;

import java.awt.*;
import java.io.IOException;
import java.text.ParseException;

public class cmdSpeedtest implements Command {
    @Override
    public boolean called(String[] args, MessageReceivedEvent event) {
        return false;
    }

    @Override
    public void action(String[] args, MessageReceivedEvent event) throws ParseException, IOException {
        SpeedTestSocket Dspeed = new SpeedTestSocket();
        SpeedTestSocket Uspeed = new SpeedTestSocket();
        StringBuilder sb = new StringBuilder();

        Message msg = event.getTextChannel().sendMessage(new EmbedBuilder().setColor(Color.ORANGE).setTitle(Emojis2.COOKIE + "" + Emojis2.WHITE_SMALL_SQUARE + "<a:discordload:525293213318250496>  Speedtest wird durchgeführt...").setDescription("Als Test downloadet der Bot eine 10 MB große Datei...").build()).complete();

        Dspeed.addSpeedTestListener(new ISpeedTestListener() {
            @Override
            public void onCompletion(SpeedTestReport report) {
                sb.append("Download:  " + (report.getTransferRateBit().floatValue() / 1024 / 1024) + " MBit/s\n");
                msg.editMessage(new EmbedBuilder().setColor(Color.ORANGE).setTitle(Emojis2.COOKIE+ "" + Emojis2.WHITE_SMALL_SQUARE + "<a:discordload:525293213318250496>  Speedtest wird durchgeführt...").setDescription("Als Test uploadet der Bot eine 1 MB große Datei...").build()).queue();
                Uspeed.startUpload("http://2.testdebit.info/", 1000000);
            }

            @Override
            public void onProgress(float percent, SpeedTestReport report) {
            }

            @Override
            public void onError(SpeedTestError speedTestError, String s) {
                System.out.println(speedTestError);
            }

        });

        Uspeed.addSpeedTestListener(new ISpeedTestListener() {
            @Override
            public void onCompletion(SpeedTestReport report) {
                sb.append("Upload:    " + (report.getTransferRateBit().floatValue() / 1024 / 1024) + " MBit/s");
                msg.editMessage(new EmbedBuilder().setColor(Color.ORANGE).setTitle(Emojis2.COOKIE + "" + Emojis2.WHITE_SMALL_SQUARE + "<:Erfolgreich:504672060501393418>  Speedtest ist abgeschlossen!").setDescription("```" + sb.toString() + "```").build()).queue();
            }

            @Override
            public void onProgress(float v, SpeedTestReport speedTestReport) {

            }

            @Override
            public void onError(SpeedTestError speedTestError, String s) {
                System.out.println(speedTestError);
            }

        });

        Dspeed.startDownload("http://2.testdebit.info/10M.iso");


    }


    @Override
    public void executed(boolean sucess, MessageReceivedEvent event) {

    }

    @Override
    public String help() {
        return null;
    }
}
