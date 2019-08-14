package listeners;

import core.restHandler;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

public class gbListener extends ListenerAdapter {
    public void onMessageReceived(MessageReceivedEvent event) {
        restHandler.setMsgs(event.getGuild(), restHandler.getMsgs(event.getGuild()));
//        if (!event.getAuthor().getId().equals(event.getJDA().getSelfUser().getId())) {
//            if (event.getMessage().getContentDisplay().contains("gb")) {
//                if (event.getTextChannel().getName().contains("geburtstag")) {
//                    if (DateHandler.getUserDate1(event.getGuild().getId()) != 0) {
//                        event.getTextChannel().sendMessage(GBHandler.getUserName(event.getGuild().getId()).getName() + " : " + DateHandler.getUserDate1(event.getGuild().getId()) + "." + DateHandler.getUserDate2(event.getGuild().getId()) + "." + DateHandler.getUserDate3(event.getGuild().getId())).queue();
//
//                    } else {
//                        event.getTextChannel().sendMessage("Es ist leider kein User gesetzt! -> /setgb help").queue();
//                    }
//
//                } else {
//                    event.getTextChannel().sendMessage("Dies ist der falsche Channel! \n Bitte gehe in #geburstag oder erstelle diesen!").queue();
//                }
//            }
//        }
//    }
    }
}
