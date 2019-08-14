package commands;

import core.restHandler;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;


public class cmdRandomKeks implements Command {

    @Override
    public boolean called (String[] args, MessageReceivedEvent event) {
        return false;
    }

    @Override
    public void action (String[]  args, MessageReceivedEvent event) {
        restHandler.setCommands(event.getGuild(), restHandler.getCommands(event.getGuild()));
        String[] images = new String[]{"https://www.leibniz.de/fileadmin/user_upload/Startseite/Slider/keks.png", "https://i2.wp.com/www.sprichwoerter-redewendungen.de/wp-content/uploads/auf-den-keks-gehen.jpg?resize=300%2C240", "https://www.hannover.de/var/storage/images/media/01-data-neu/bilder/redaktion-hannover.de/2016/2016_04/leibniz-butterkeks/13439944-1-ger-DE/Leibniz-Butterkeks_alias_300xVariabel.jpg", "https://media.discordapp.net/attachments/505019342946304024/505019754394943488/unknown.png?width=300&height=300"};


        event.getTextChannel().sendMessage("/answerkeks").queue();

    }
    @Override
    public void executed(boolean sucess, MessageReceivedEvent event) {
        System.out.println("[Info] Der Command randomKeks wurde ausgeführt!");

    }

    @Override
    public  String  help() {

        return null;
    }
}
