package util;

public enum  randomQuestion {

    ANSWERQUESTION1("Dein Ernst!"),
    ANSWERQUESTION2("Ja!"),
    ANSWERQUESTION3("Niemals!"),
    ANSWERQUESTION4("Nö"),
    ANSWERQUESTION5("Nein!"),
    ANSWERQUESTION6("Klaro"),
    ANSWERQUESTION7("Jep du depp lol"),
    ANSWERQUESTION8("Auf keinen Fall");

    private final String text;

    randomQuestion(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }
}
