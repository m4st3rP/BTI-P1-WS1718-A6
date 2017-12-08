package media;

public class DVD extends Disc {
    private Format videoFormat;

    public DVD(String title, Content contentType, Format videoFormat) {
        super(title, contentType);
        this.videoFormat = videoFormat;
    }
    
    @Override
    public String toString() {
        return "Class: " + this.getClass() + ", Title: " + title + ", Content Type: " + contentType + ", Video Format: " + videoFormat;
    }
    
//    @Override
//    public boolean equals(Object obj) {
//        DVD objcet = (DVD) obj;
//        if (this.title == objcet.title && this.contentType == objcet.contentType && this.videoFormat == objcet.videoFormat) {
//            return true;
//        } else {
//            return false;
//        }
//    }
}
