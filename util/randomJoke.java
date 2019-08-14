package util;

public enum  randomJoke {

    JOKE1("Was sitzt auf einem Baum und schreit Aha?\n" +
            "\n" +
            "Ein Uhu mit Sprachfehler"),
    JOKE2("Was machen zwei wütende Schafe?\n" +
            "\n" +
            "Sie kriegen sich in die Wolle."),
    JOKE3("Ein Schneemann zum anderen: Lustig, ich rieche auch Karotten"),
    JOKE4("Was ist grau und kann nicht fliegen?\n" +
            "\n" +
            "Eine zu fette Taube."),
    JOKE5("Wo leben die meisten Gespenster?\n" +
            "-\n" +
            "In BUHdapest"),
    JOKE6("Wie war die Stimmung in der DDR? \n" +
            "Sie hielt sich so ziemlich in Grenzen."),
    JOKE7("Hab heute schon wieder einen Brief von dem Anwalt bekommen. Dort steht: „Letzte Mahnung“. Gut, dass das endlich aufhört."),
    JOKE8("Wann gehen U-Boote unter?\n" +
            "\n" +
            "Am Tag der offenen Tür.");

    private final String text;

    randomJoke(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }
}
