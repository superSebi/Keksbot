package util;

public enum  Pictures {

    BILD_1("https://upload.wikimedia.org/wikipedia/commons/thumb/4/4c/Butterkeks.jpg/1024px-Butterkeks.jpg"),
    BILD_2("https://i2.wp.com/www.sprichwoerter-redewendungen.de/wp-content/uploads/auf-den-keks-gehen.jpg?resize=300%2C240"),
    BILD_3("http://dioten.net/wp-content/uploads/2016/11/keks.jpg"),
    BILD_4("https://previews.123rf.com/images/kongvector/kongvector1707/kongvector170700878/82062470-unschuldiges-gesicht-s%C3%BC%C3%9Fe-kekse-charakter-cartoon.jpg"),
    BILD_5("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcT668d7C2EuVCLKR0ViayW98wrgsQgIqhFFF163ufEPrJByPkEE"),
    BILD_6("https://www.shop-rikiki.de/out/pictures/generated/product/1/1200_1200_100/macon-et-lesquoy-brosche-keks-1.jpg");

    private final String text;

    Pictures(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }
}
