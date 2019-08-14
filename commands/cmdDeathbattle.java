package commands;


import core.restHandler;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import core.PrefixHandler;


public class cmdDeathbattle implements Command {


    @Override
    public boolean called (String[] args, MessageReceivedEvent event) {
        return false;
    }

    @Override
    public void action (String[]  args, MessageReceivedEvent event) {
        restHandler.setCommands(event.getGuild(), restHandler.getCommands(event.getGuild()));
        if(args[0].contains("hui")) {
            event.getTextChannel().sendMessage("Hui gewinnt, denn er ist HUI und unbesiegbar!").queue();

        }
        String authorName = event.getAuthor().getName();
        if(args[0].contains(authorName)) {
            event.getTextChannel().sendMessage("Gegen sich selber zu kämpfen wird schwer...").queue();

        }
        if(args[0].contains("keks")) {
            event.getTextChannel().sendMessage("Der KeksGott wird dich finden und essen!").queue();

        }
//        event.getTextChannel().sendMessage(
//                new EmbedBuilder().setColor(Color.RED)
//                        .setTitle("<:X_:504673235212697601>"  + Emojis2.WHITE_SMALL_SQUARE + "Fehler")
//                        .setDescription("Dieses Tool wird derzeit überarbeitet.")
//                        .setFooter("Bug erfolgreich gesendet (von " + event.getAuthor().getName() + ")", event.getAuthor().getAvatarUrl())
//                        .build()).queue();


         if(args.length > 1) {
             if (!args[1].contains(authorName)) {
                 if (!args[1].contains("hui")) {
                     if (!args[1].contains("keks")) {
                         String out = "";
                         for (String s : args) {
                             out += s + " ";
                         }

                             event.getTextChannel().sendMessage(args[0] + ":crossed_swords:" + args[1]).queue();
                             event.getTextChannel().sendMessage("Ablauf:").queue();
                             event.getTextChannel().sendMessage(PrefixHandler.PREFIX + "answerdeathbattle " + out + " " + authorName).queue();

                    }
                 }
             }

         } else {
             event.getTextChannel().sendMessage("Du musst 2 Spieler angeben!").queue();
         }
     }

    @Override
    public void executed(boolean sucess, MessageReceivedEvent event) {
        System.out.println("[Info] Der Command deathbattle wurde ausgeführt!");

    }

    @Override
    public  String  help() {

        return null;
    }



}

