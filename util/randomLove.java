package util;

public enum  randomLove {

    ANSWERLOVE1("Ich dich auch <3"),
    ANSWERLOVE2("Dein Ernst, Mit einer KI..."),
    ANSWERLOVE3("Ich nicht"),
    ANSWERLOVE4("Du lügst eh :("),
    ANSWERLOVE5("Und jetzt?");

    private final String text;

    randomLove(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }
}
