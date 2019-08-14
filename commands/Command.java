package commands;

import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

import java.io.IOException;
import java.text.ParseException;

public interface Command {

        //By KeksOfs | Only for insparation, not for copy paste

        boolean called(String[] args, MessageReceivedEvent event);
        void action(String[] args, MessageReceivedEvent event) throws ParseException, IOException;
        void executed(boolean sucess, MessageReceivedEvent event);




    String help();


    }

