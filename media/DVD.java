package media;

public class DVD extends Disc {
    private Format videoFormat;

    public DVD(String title, Content contentType, Format videoFormat) {
        super(title, contentType);
        this.videoFormat = videoFormat;
    }


    @Override
    public String toString() {
        return String.format("[<%s>: Format=%s %s]", DVD.class.getSimpleName(), videoFormat, super.toString());
    }

    @Override
    public boolean equals(Object other) {
        boolean equal = super.equals(other);
        if (getClass() != other.getClass())
            return false;
        return ((DVD) other).videoFormat.equals(this.videoFormat) && equal;
    }
}
