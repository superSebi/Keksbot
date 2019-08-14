package core;

import java.util.HashMap;

public class CustomCommandsHandler {
    private static final String BEFEHL = "command.";
    private static final String ANTOWRT = "answer.";
    private static HashMap<String, String> commandMap= new HashMap<>();
    private static HashMap<String, String> answerMap = new HashMap<>();

    public static String getServerCommand(String server) {
        return commandMap.get(server);
    }

    public static void setServerCommand (String server, String command){
        commandMap.put(server, command);
    }

    public static String getCommandAnswer (String server) {
        return answerMap.get(server);
    }

    public static void setAnswer (String server, String command){
        answerMap.put(server, command);
    }

}
