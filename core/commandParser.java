package core;


import net.dv8tion.jda.core.events.message.MessageReceivedEvent;


import java.util.ArrayList;
import java.util.regex.Pattern;

public class commandParser {

    private static Pattern SPECIAL_REGEX_CHARS = Pattern.compile("[{}()\\[\\].+*?^$\\\\|]");

    private static String escapeSpecialRegexChars(String str) {

        return SPECIAL_REGEX_CHARS.matcher(str).replaceAll("\\\\$0");
    }

    public static commandContainer parser (String raw, MessageReceivedEvent event) {
        String beheaded = raw.replaceFirst(escapeSpecialRegexChars(PrefixHandler.getUserPrefix(event.getAuthor().getId(),event.getGuild().getId())), "");
        String[] splitBeheaded = beheaded.split(" ");
        String invoke = splitBeheaded[0];
        ArrayList<String> split= new ArrayList<>();
        for (String s : splitBeheaded) {
            split.add(s);
        }
        String[] args = new String[split.size() - 1];
        split.subList(1, split.size()).toArray(args);

        return new commandContainer(raw, beheaded, splitBeheaded, invoke, args, event);
    }

    public static class commandContainer {

        public String raw;
        public String beheaded;
        public String[] splitBeheaded;
        public String invoke;
        public String[] args;
        public MessageReceivedEvent event;

        public commandContainer(String rw, String beheaded, String[] splitBeheaded, String invoke, String[] args, MessageReceivedEvent event) {
            this.raw = rw;
            this.beheaded = beheaded;
            this.splitBeheaded = splitBeheaded;
            this.invoke = invoke;
            this.args = args;
            this.event = event;

        }
    }

}
