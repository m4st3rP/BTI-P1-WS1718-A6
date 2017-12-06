package wordCounter;


/**
 * A6x2: Woerter zaehlen - see task
 * 
 * @author   Michael Schäfers ; P1@Hamburg-UAS.eu 
 * @version  2016/11/30
 */
public class TestFrame {
    
    //
    // ACHTUNG!:
    // =========
    // Die Datei "PrincessOfMars.txt" muss im Projekt-Folder liegen
    // also parallel zum "src"-Folder und zum "bin"-Folder.
    // Andernfalss muss der Path zur Datei hin mit angegeben werden.
    //
    public static void main( final String... unused ) {
        System.out.printf( "TI1-P1 (WS17/18):\n" );
        System.out.printf( "=================\n" );
        System.out.printf( "\n\n" );
        
        
        final String text = "PrincessOfMars.txt";
        
        // falls printStatistic() NICHT als static deklariert wurde
        final WordCounter wc = new WordCounter();
        wc.printStatistic( text );
        
        // alternativ falls printStatistic() als static deklariert wurde
        //WordCounter.printStatistic( text );
    }//method()
    
}//class
