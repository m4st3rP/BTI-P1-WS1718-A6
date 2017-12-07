package media;

public abstract class Disc {
    protected String title;
    protected Content contentType;


    public Disc(String title, Content contentType) {
        this.title = title;
        this.contentType = contentType;
    }
    
    @Override
    public String toString() {
        return "Title: " + title + ", Content Type: " + contentType;
    }
    
}
