package media;

public class DVD extends Disc {
    private Format videoFormat;

    public DVD(String title, Content contentType, Format videoFormat) {
        super(title, contentType);
        this.videoFormat = videoFormat;
    }
}
