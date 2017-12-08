package media;

public class CD extends Disc {
    private String interpret;

    public CD(String title, Content contentType, String interpret) {
        super(title, contentType);
        this.interpret = interpret;
    }
    
    @Override
    public String toString() {
        return "Class: " + this.getClass() + ", Title: " + title + ", Content Type: " + contentType + ", Interpret: " + interpret;
    }
    
//    @Override
//    public boolean equals(Object obj) {
//        CD objcet = (CD) obj;
//        if (this.title == objcet.title && this.contentType == objcet.contentType && this.interpret == objcet.interpret) {
//            return true;
//        } else {
//            return false;
//        }
//    }
}

