package util;

public enum Glücksrad {
    PREMIUMCODE("Du gewinnst 1 Ticket für **das Glücksrad**"),
    NIETE("Pech gehabt! Dies ist eine Niete"),
    KEKSE("Du gewinnst 100 Kekse!"),
    RETRY("Neuversuch! /weihnachts-event glücksrad-restart"),
    HAUPTGEWINN("OMG! Dies ist der Hauptgewinn! Du gewinnst einen eigenen Command schreibe mit dem Server Sebl an!"),
    NIETE2("Pech gehabt! Dies ist eine Niete"),
    NIETE3("Pech gehabt! Dies ist eine Niete"),
    NIETE4("Pech gehabt! Dies ist eine Niete"),
    NIETE5("Pech gehabt! Dies ist eine Niete"),
    NIETE6("Pech gehabt! Dies ist eine Niete"),
    NIETE7("Pech gehabt! Dies ist eine Niete"),
    NIETE8("Pech gehabt! Dies ist eine Niete"),
    NIETE9("Pech gehabt! Dies ist eine Niete"),
    NIETE10("Pech gehabt! Dies ist eine Niete"),
    NIETE11("Pech gehabt! Dies ist eine Niete"),
    NIETE12("Pech gehabt! Dies ist eine Niete"),
    NIETE13("Pech gehabt! Dies ist eine Niete");


    //__________________________________________________________________________________________________


    private final String text;

    Glücksrad(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }

}
