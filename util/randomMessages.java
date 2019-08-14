package util;

public enum randomMessages {


    ANSWERRANDOM1("Verstehe ich"),
    ANSWERRANDOM2("Ok"),
    ANSWERRANDOM3("Kenne ich"),
    ANSWERRANDOM4("Schade"),
    ANSWERRANDOM5("Geil"),
    ANSWERRANDOM6("Wirklich"),
    ANSWERRANDOM7("Naw... <3"),
    ANSWERRANDOM8("Schön"),
    ANSWERRANDOM9("tschüss."),
    ANSWERRANDOM10("Immer diese Kinder..."),
    ANSWERRANDOM11("Fappen ist toll!"),
    ANSWERRANDOM("Abboniert KeksOfs!");

    private final String text;

    randomMessages(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }

}
