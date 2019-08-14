package commands;

import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class cmdBild implements Command{


    @Override
    public boolean called(String[] args, MessageReceivedEvent event) {
        return false;
    }

    @Override
    public void action(String[] args, MessageReceivedEvent event) {
        BufferedImage image = null;
        File file = new File("C:\\Users\\sebik\\IdeaProjects\\Bester_Bot\\src\\main\\java\\pictures\\toad.jpg");

        try {
            image = ImageIO.read(file);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        assert image != null;
        int imageBreite = image.getWidth();
        int imageHoehe = image.getHeight();
        Graphics g = image.getGraphics();

        g.drawString("test", imageBreite,imageHoehe);

        event.getTextChannel().sendMessage(new EmbedBuilder().setImage(image.toString()).build()).queue();
        System.out.println("lol fertig");
    }

    @Override
    public void executed(boolean sucess, MessageReceivedEvent event) {

    }

    @Override
    public String help() {
        return null;
    }
}
