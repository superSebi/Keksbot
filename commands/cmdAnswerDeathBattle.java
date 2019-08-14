package commands;

import core.RandomInt;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import util.Emojis;

public class cmdAnswerDeathBattle implements Command {

    private class AnswerDeathBattleThread implements Runnable
    {
        private MessageReceivedEvent event;
        private String args[];

        public AnswerDeathBattleThread(MessageReceivedEvent event,String args[]) {
            this.event = event;
            this.args = args;
        }
        private String getAnswerText(String name1,String name2,int newDamage, int totalDamage)
        {
            String emoji = String.format("%s", Emojis.values()[RandomInt.random(Emojis.values().length)].toString().toLowerCase());
            return String.format("%s benutzt %s und macht %d Schaden bei %s (%d/100)", name1,emoji,newDamage,name2,100-totalDamage);

        }

        @Override
        public void run() {
            String authorName = args[1];
            String nickname = args[0];

            int authorDamages = 0;
            int nicknameDamages = 0;

            do {
                int newAuthorDamage = 1 + RandomInt.random(40);
                int newNickNameDamage = 1 + RandomInt.random(40);

                authorDamages = Math.min(100, authorDamages + newAuthorDamage);
                nicknameDamages = Math.min(100, nicknameDamages + newNickNameDamage);

                if (nicknameDamages < 100) {
                    event.getTextChannel().editMessageById(event.getMessageIdLong(), getAnswerText(authorName, nickname, newAuthorDamage, authorDamages)).queue();
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        //is wurscht
                    }
                }
                if (authorDamages < 100) {
                    event.getTextChannel().editMessageById(event.getMessageIdLong(), getAnswerText(nickname, authorName, newNickNameDamage, nicknameDamages)).queue();
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        //is wurscht
                    }
                }
            }
            while (authorDamages < 100 && nicknameDamages < 100);

            if (authorDamages == 100) {
                event.getTextChannel().sendMessage(authorName + " gewinnt!").queue();
            } else {
                event.getTextChannel().sendMessage(nickname + " gewinnt!").queue();
            }
        }
    }

    @Override
    public boolean called(String[] args, MessageReceivedEvent event) {
        return false;
    }


    @Override
    public void action(String[] args, MessageReceivedEvent event) {
        new Thread(new AnswerDeathBattleThread(event,args)).start();
    }

    @Override
    public void executed(boolean sucess, MessageReceivedEvent event) {
        System.out.println("[Info] Der Command answerdeathbattle wurde ausgeführt!");

    }

    @Override
    public String help() {

        return null;
    }
}