package media;

public class CD extends Disc {
    private String interpret;

    public CD(String title, Content contentType, String interpret) {
        super(title, contentType);
        this.interpret = interpret;
    }
    
    @Override
    public String toString() {
        return "Title: " + title + ", Content Type: " + contentType + ", Interpret: " + interpret;
    }
}
