package media;

public class Disc {
    protected String title;
    protected Content contentType;


    public Disc(String title, Content contentType) {
        this.title = title;
        this.contentType = contentType;
    }
    
    @Override
    public String toString() {
        return "Class: " + this.getClass() + ", Title: " + title + ", Content Type: " + contentType;
    }
    
    @Override
    public boolean equals(Object obj) {
        Disc object = (Disc) obj;
        if (this.title == object.title && this.contentType == object.contentType) {
            return true;
        } else {
            return false;
        }
    }
    
    @Override
    public int hashCode() {
        return 1;
    }
}
