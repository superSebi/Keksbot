package util;

public enum  randomHate {
    ANSWERHATE1("BAN"),
    ANSWERHATE2("Wie du meinst, schade"),
    ANSWERHATE3("Ich dich auch! Du dummer Spaßt ey!"),
    ANSWERHATE4("/ban @ARSCH Beleidigungen dieser dumme Sohn");

    private final String text;

    randomHate(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }
}
