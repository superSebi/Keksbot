package util;

public enum Gifts {
    GIFT1(Emojis2.COOKIE + ""),
    GIFT2("BETRUG :( Das Geschenk war leer"),
    GIFT3("Ein Gutschein für **das Glücksrad**");

    private final String text;

    Gifts(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }
}
