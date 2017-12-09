package media;

public class CD extends Disc {
    private String interpret;

    public CD(String title, Content contentType, String interpret) {
        super(title, contentType);
        this.interpret = interpret;
    }
    
    @Override
    public String toString() {
        return String.format("[<%s>: Interpret=%s %s]", CD.class.getSimpleName(), interpret, super.toString());
}
    
    @Override
    public boolean equals(Object other) {
        boolean equal = super.equals(other);
        if (getClass() != other.getClass())
            return false;
        return ((CD) other).interpret.equals(this.interpret) && equal;
}
}

