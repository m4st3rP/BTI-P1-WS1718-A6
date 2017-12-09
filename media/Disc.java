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
        return String.format("[<%s>: Title=%s, MediaType=%s]", Disc.class.getSimpleName(), title, contentType);
}
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Disc other = (Disc) obj;
        if (title == null) {
            if (other.title != null)
                return false;
        } else if (!title.equals(other.title))
            return false;
        if (contentType != other.contentType)
            return false;
        return true;
    }
    
    @Override
    public int hashCode() {
        return 1;
    }
}
