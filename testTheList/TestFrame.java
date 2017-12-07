package testTheList;

import static media.Content.*;
import static media.Format.*;
import list.MyList;
import media.*;

public class TestFrame {

    public static void main(String[] args) {
        test1();

    }
    
    public static void test1() {
        MyList list = new MyList();
        DVD dvd1 = new DVD("Taxi", MOVIE, PAL);
        DVD dvd2 = new DVD("Shrek", MOVIE, NTSC);
        CD cd1 = new CD("Foxtrot", AUDIO, "Genesis");
        
       list.putF(dvd1);
       list.putL(dvd2);
       list.putF(cd1);
       list.extractF();
       list.printElemF2L();
    }

}
