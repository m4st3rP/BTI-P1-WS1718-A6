package media;

public abstract class Disc {
    private String title;
    private Content contentType;


    public Disc(String title, Content contentType) {
        this.title = title;
        this.contentType = contentType;
    }
}
