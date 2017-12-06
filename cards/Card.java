/* ----------------------------------------------------------------------------
 * KNOWN PROBLEMS: 130114: JavaDoc has problem with inner classes : "bug_id=4464323" resp. "http://bugs.sun.com/bugdatabase/view_bug.do?bug_id=4464323"
 * 
 * NOTEs:          This class was decided to be implemented as class on purpose
 *                 An enum is avoided since the "natural order" of an enum is explicitly NOT wanted
 * 
 * OPEN POINTS:    Should all be removed in the final version, anyway they have been
 *                 marked with: _???_<YYMMDD>  (search/grep for _???_)
 *                 Currently there is only "bug_id=4464323" (see above)
 * -----------------------------------------------------------------------------
 */
package cards;
 
 
 /**
 * Die Klasse Card ist der Datentyp f&uuml;r eine Spiel<strong>karte</strong>.<br />
 * <br />
 * Wichtige Attribute dieser Klasse sind:<br />
 * &bull;{@link #getRank()} liefert den Rang zur (jeweiligen) Karte.<br />
 * &bull;{@link #getSuit()} liefert die Farbe zur (jeweiligen) Karte.<br />
 * <br />
 * VCS: git@bitbucket.org:schaefers/P1_Cards_BlueJ_Pilot
 */
public class Card implements Comparable<Card> {
    
    //
    //
    // TYPEs--------------------------------------------------------------------
    
    /**
     * Der enum Suit beschreibt die m&ouml;glichen Farbwerte einer Karte: 
     * <code>CLUB</code>    (&clubs;),
     * <code>DIAMOND</code> (<font color="red">&diams;</font>),
     * <code>HEART</code>   (<font color="red">&hearts;</font>),
     * <code>SPADES</code>  (&spades;)
     */
    public enum Suit {
        
        CLUB,     // Kreuz
        DIAMOND,  // Karo
        HEART,    // Herz
        SPADES;   // Pik
       
        
        @Override
        public String toString(){
            String resu;
            
            switch( this ){
                case CLUB    : resu = "C"; break;  // \u2663
                case DIAMOND : resu = "D"; break;  // \u2666
                case HEART   : resu = "H"; break;  // \u2665
                case SPADES  : resu = "S"; break;  // \u2660
                default      : resu = "?";
            }//switch
            
            return resu;
        }//method()

    }//enum
    
    
    
    /**
     * Der enum Rank beschreibt die m&ouml;glichen R&auml;nge einer Karte:
     * <code>TWO</code>, <code>THREE</code>, <code>FOUR</code>, <code>FIVE</code>, <code>SIX</code>, <code>SEVEN</code>, <code>EIGHT</code>, <code>NINE</code>, <code>TEN</code>, <code>JACK</code>, <code>QUEEN</code>, <code>KING</code>, <code>ACE</code>
     */
    public enum Rank {
        
        TWO,    //  2
        THREE,  //  3
        FOUR,   //  4
        FIVE,   //  5
        SIX,    //  6
        SEVEN,  //  7
        EIGHT,  //  8
        NINE,   //  9
        TEN,    //  T bzw. 10
        JACK,   //  J bzw. Bube
        QUEEN,  //  Q bzw. Dame
        KING,   //  K bzw. Koenig
        ACE;    //  A bzw. Ass
        
        
        /**
         * Die Methode value() berechnet den (&quot;&uuml;blichen&quot;) numerischen Wert eines Karten-Ranges (<code>Rank</code>).
         * Hierbei ist das Ass (<code>ACE</code>) die Karte mit dem h&ouml;chsten Wert.
         * Ein Ass weist also den Wert 14 auf.
         * (Achtung! Sonderf&auml;lle wie z.B. Ass als Wheel bei &quot;TEXAS HOLD'EM&quot; werden von value() nicht ber&uuml;cksichtigt.
         * @return numerischer Wert des Karten-Ranges.
         */
        public int value(){
           return ordinal() + 2;
        }//method()

        
        @Override
        public String toString(){
            String resu;
            
            switch( this ){
                case TEN   : resu = "T"; break;
                case JACK  : resu = "J"; break;
                case QUEEN : resu = "Q"; break;
                case KING  : resu = "K"; break;
                case ACE   : resu = "A"; break;
                default    : resu = "" + value();
            }//switch
            
            return resu;
        }//method()

    }//enum

    

    //
    //
    // CONSTANTs----------------------------------------------------------------
    
    
    public static class Constant {
        
        /** &clubs; */  public static final Suit CLUB    = Suit.CLUB;
        /** &diams; */  public static final Suit DIAMOND = Suit.DIAMOND;
        /** &hearts; */ public static final Suit HEART   = Suit.HEART;
        /** &spades; */ public static final Suit SPADES  = Suit.SPADES;
        
        /** 2 */        public static final Rank TWO     = Rank.TWO;
        /** 3 */        public static final Rank THREE   = Rank.THREE;
        /** 4 */        public static final Rank FOUR    = Rank.FOUR;
        /** 5 */        public static final Rank FIVE    = Rank.FIVE;
        /** 6 */        public static final Rank SIX     = Rank.SIX;
        /** 7 */        public static final Rank SEVEN   = Rank.SEVEN;
        /** 8 */        public static final Rank EIGHT   = Rank.EIGHT;
        /** 9 */        public static final Rank NINE    = Rank.NINE;
        /** 10 */       public static final Rank TEN     = Rank.TEN;
        /** Bube */     public static final Rank JACK    = Rank.JACK;
        /** Dame */     public static final Rank QUEEN   = Rank.QUEEN;
        /** K&ouml;nig*/public static final Rank KING    = Rank.KING;
        /** Ass */      public static final Rank ACE     = Rank.ACE;
        
        
        
        
        /** Die Konstante C2 steht f&uuml;r die Spielkarte <code>CLUB-TWO</code> bzw &clubs;2. */
        public static final Card C2 = new Card( CLUB, TWO );
        /** Die Konstante C3 steht f&uuml;r die Spielkarte <code>CLUB-THREE</code> bzw &clubs;3. */
        public static final Card C3 = new Card( CLUB, THREE );
        /** Die Konstante C4 steht f&uuml;r die Spielkarte <code>CLUB-FOUR</code> bzw &clubs;4. */
        public static final Card C4 = new Card( CLUB, FOUR );
        /** Die Konstante C5 steht f&uuml;r die Spielkarte <code>CLUB-FIVE</code> bzw &clubs;5. */
        public static final Card C5 = new Card( CLUB, FIVE );
        /** Die Konstante C6 steht f&uuml;r die Spielkarte <code>CLUB-SIX</code> bzw &clubs;6. */
        public static final Card C6 = new Card( CLUB, SIX );
        /** Die Konstante C7 steht f&uuml;r die Spielkarte <code>CLUB-SEVEN</code> bzw &clubs;7. */
        public static final Card C7 = new Card( CLUB, SEVEN );
        /** Die Konstante C8 steht f&uuml;r die Spielkarte <code>CLUB-EIGHT</code> bzw &clubs;8. */
        public static final Card C8 = new Card( CLUB, EIGHT );
        /** Die Konstante C9 steht f&uuml;r die Spielkarte <code>CLUB-NINE</code> bzw &clubs;9. */
        public static final Card C9 = new Card( CLUB, NINE );
        /** Die Konstante CT steht f&uuml;r die Spielkarte <code>CLUB-TEN</code> bzw &clubs;10. */
        public static final Card CT = new Card( CLUB, TEN );
        /** Die Konstante CJ steht f&uuml;r die Spielkarte <code>CLUB-JACK</code> bzw &clubs;Bube. */
        public static final Card CJ = new Card( CLUB, JACK );
        /** Die Konstante CQ steht f&uuml;r die Spielkarte <code>CLUB-QUEEN</code> bzw &clubs;Dame. */
        public static final Card CQ = new Card( CLUB, QUEEN );
        /** Die Konstante CK steht f&uuml;r die Spielkarte <code>CLUB-KING</code> bzw &clubs;K&ouml;nig. */
        public static final Card CK = new Card( CLUB, KING );
        /** Die Konstante CA steht f&uuml;r die Spielkarte <code>CLUB-ACE</code> bzw &clubs;Ass. */
        public static final Card CA = new Card( CLUB, ACE );
        
        
        /** Die Konstante D2 steht f&uuml;r die Spielkarte <code>DIAMOND-TWO</strong></code> bzw <font color="red">&diams;2</font>. */
        public static final Card D2 = new Card( DIAMOND, TWO );
        /** Die Konstante D3 steht f&uuml;r die Spielkarte <code>DIAMOND-THREE</strong></code> bzw <font color="red">&diams;3</font>. */
        public static final Card D3 = new Card( DIAMOND, THREE );
        /** Die Konstante D4 steht f&uuml;r die Spielkarte <code>DIAMOND-FOUR</strong></code> bzw <font color="red">&diams;4</font>. */
        public static final Card D4 = new Card( DIAMOND, FOUR );
        /** Die Konstante D5 steht f&uuml;r die Spielkarte <code>DIAMOND-FIVE</strong></code> bzw <font color="red">&diams;5</font>. */
        public static final Card D5 = new Card( DIAMOND, FIVE );
        /** Die Konstante D6 steht f&uuml;r die Spielkarte <code>DIAMOND-SIX</strong></code> bzw <font color="red">&diams;6</font>. */
        public static final Card D6 = new Card( DIAMOND, SIX );
        /** Die Konstante D7 steht f&uuml;r die Spielkarte <code>DIAMOND-SEVEN</strong></code> bzw <font color="red">&diams;7</font>. */
        public static final Card D7 = new Card( DIAMOND, SEVEN );
        /** Die Konstante D8 steht f&uuml;r die Spielkarte <code>DIAMOND-EIGHT</strong></code> bzw <font color="red">&diams;8</font>. */
        public static final Card D8 = new Card( DIAMOND, EIGHT );
        /** Die Konstante D9 steht f&uuml;r die Spielkarte <code>DIAMOND-NINE</strong></code> bzw <font color="red">&diams;9</font>. */
        public static final Card D9 = new Card( DIAMOND, NINE );
        /** Die Konstante DT steht f&uuml;r die Spielkarte <code>DIAMOND-TEN</strong></code> bzw <font color="red">&diams;10</font>. */
        public static final Card DT = new Card( DIAMOND, TEN );
        /** Die Konstante DJ steht f&uuml;r die Spielkarte <code>DIAMOND-JACK</strong></code> bzw <font color="red">&diams;JACK</font>. */
        public static final Card DJ = new Card( DIAMOND, JACK );
        /** Die Konstante DQ steht f&uuml;r die Spielkarte <code>DIAMOND-QUEEN</strong></code> bzw <font color="red">&diams;QUEEN</font>. */
        public static final Card DQ = new Card( DIAMOND, QUEEN );
        /** Die Konstante DK steht f&uuml;r die Spielkarte <code>DIAMOND-KING</strong></code> bzw <font color="red">&diams;KING</font>. */
        public static final Card DK = new Card( DIAMOND, KING );
        /** Die Konstante DA steht f&uuml;r die Spielkarte <code>DIAMOND-ACE</strong></code> bzw <font color="red">&diams;ACE</font>. */
        public static final Card DA = new Card( DIAMOND, ACE );
        
        
        /** Die Konstante H2 steht f&uuml;r die Spielkarte <code>HEART-TWO</strong></code> bzw <font color="red">&hearts;2</font>. */
        public static final Card H2 = new Card( HEART, TWO );
        /** Die Konstante H3 steht f&uuml;r die Spielkarte <code>HEART-THREE</strong></code> bzw <font color="red">&hearts;3</font>. */
        public static final Card H3 = new Card( HEART, THREE );
        /** Die Konstante H4 steht f&uuml;r die Spielkarte <code>HEART-FOUR</strong></code> bzw <font color="red">&hearts;4</font>. */
        public static final Card H4 = new Card( HEART, FOUR );
        /** Die Konstante H5 steht f&uuml;r die Spielkarte <code>HEART-FIVE</strong></code> bzw <font color="red">&hearts;5</font>. */
        public static final Card H5 = new Card( HEART, FIVE );
        /** Die Konstante H6 steht f&uuml;r die Spielkarte <code>HEART-SIX</strong></code> bzw <font color="red">&hearts;6</font>. */
        public static final Card H6 = new Card( HEART, SIX );
        /** Die Konstante H7 steht f&uuml;r die Spielkarte <code>HEART-SEVEN</strong></code> bzw <font color="red">&hearts;7</font>. */
        public static final Card H7 = new Card( HEART, SEVEN );
        /** Die Konstante H8 steht f&uuml;r die Spielkarte <code>HEART-EIGHT</strong></code> bzw <font color="red">&hearts;8</font>. */
        public static final Card H8 = new Card( HEART, EIGHT );
        /** Die Konstante H9 steht f&uuml;r die Spielkarte <code>HEART-NINE</strong></code> bzw <font color="red">&hearts;9</font>. */
        public static final Card H9 = new Card( HEART, NINE );
        /** Die Konstante HT steht f&uuml;r die Spielkarte <code>HEART-TEN</strong></code> bzw <font color="red">&hearts;10</font>. */
        public static final Card HT = new Card( HEART, TEN );
        /** Die Konstante HJ steht f&uuml;r die Spielkarte <code>HEART-JACK</strong></code> bzw <font color="red">&hearts;JACK</font>. */
        public static final Card HJ = new Card( HEART, JACK );
        /** Die Konstante HQ steht f&uuml;r die Spielkarte <code>HEART-QUEEN</strong></code> bzw <font color="red">&hearts;QUEEN</font>. */
        public static final Card HQ = new Card( HEART, QUEEN );
        /** Die Konstante HK steht f&uuml;r die Spielkarte <code>HEART-KING</strong></code> bzw <font color="red">&hearts;KING</font>. */
        public static final Card HK = new Card( HEART, KING );
        /** Die Konstante HA steht f&uuml;r die Spielkarte <code>HEART-ACE</strong></code> bzw <font color="red">&hearts;ACE</font>. */
        public static final Card HA = new Card( HEART, ACE );
        
        
        /** Die Konstante S2 steht f&uuml;r die Spielkarte <code>SPADES-TWO</code> bzw &spades;2. */
        public static final Card S2 = new Card( SPADES, TWO );
        /** Die Konstante S3 steht f&uuml;r die Spielkarte <code>SPADES-THREE</code> bzw &spades;3. */
        public static final Card S3 = new Card( SPADES, THREE );
        /** Die Konstante S4 steht f&uuml;r die Spielkarte <code>SPADES-FOUR</code> bzw &spades;4. */
        public static final Card S4 = new Card( SPADES, FOUR );
        /** Die Konstante S5 steht f&uuml;r die Spielkarte <code>SPADES-FIVE</code> bzw &spades;5. */
        public static final Card S5 = new Card( SPADES, FIVE );
        /** Die Konstante S6 steht f&uuml;r die Spielkarte <code>SPADES-SIX</code> bzw &spades;6. */
        public static final Card S6 = new Card( SPADES, SIX );
        /** Die Konstante S7 steht f&uuml;r die Spielkarte <code>SPADES-SEVEN</code> bzw &spades;7. */
        public static final Card S7 = new Card( SPADES, SEVEN );
        /** Die Konstante S8 steht f&uuml;r die Spielkarte <code>SPADES-EIGHT</code> bzw &spades;8. */
        public static final Card S8 = new Card( SPADES, EIGHT );
        /** Die Konstante S9 steht f&uuml;r die Spielkarte <code>SPADES-NINE</code> bzw &spades;9. */
        public static final Card S9 = new Card( SPADES, NINE );
        /** Die Konstante ST steht f&uuml;r die Spielkarte <code>SPADES-TEN</code> bzw &spades;10. */
        public static final Card ST = new Card( SPADES, TEN );
        /** Die Konstante SJ steht f&uuml;r die Spielkarte <code>SPADES-JACK</code> bzw &spades;JACK. */
        public static final Card SJ = new Card( SPADES, JACK );
        /** Die Konstante SQ steht f&uuml;r die Spielkarte <code>SPADES-QUEEN</code> bzw &spades;QUEEN. */
        public static final Card SQ = new Card( SPADES, QUEEN );
        /** Die Konstante SK steht f&uuml;r die Spielkarte <code>SPADES-KING</code> bzw &spades;KING. */
        public static final Card SK = new Card( SPADES, KING );
        /** Die Konstante SA steht f&uuml;r die Spielkarte <code>SPADES-ACE</code> bzw &spades;ACE. */
        public static final Card SA = new Card( SPADES, ACE );
        
    }//class
    
    
    
    //
    //
    // ATTRIBUTE DATA-----------------------------------------------------------

    private final Suit suit;
    private final Rank rank;

    
    
    //
    //
    // CONSTRUCTORs-------------------------------------------------------------

    /**
     * Der Konstruktor erzeugt eine Karte.
     * Dieser Konstrukor ist nur f&uuml;r interne Zwecke und setzt interne Variablen.
     * Studenten d&uuml;rfen diesen Konstruktor <strong><u>nicht</u></strong> zur L&ouml;sung der Poker-Aufgabe nutzen.
     * @param suit bestimmt die Farbe der zu erzeugenden Karte.
     * @param rank bestimmt den Karten-Wert der zu erzeugenden Karte.
     */
    Card( final Suit suit, final Rank rank ){                                  // friendly on purpose - do NOT(!) change this
        this.suit = suit;
        this.rank = rank;
    }//constructor()
    
    
    
    //
    //
    // METHODs------------------------------------------------------------------
    
    /**
     * Die Methode printCardArray() druckt alle Karten eines Arrays &uuml;ber Karten.<br />
     * Die Methode basiert auf einer Idee bzw. einer 1.Version von Mark-Andr&eacute; Kloesters (aus dem Jahr 2009).
     * @param   cards Das Array &uuml;ber Karten, das ausgedruckt werden soll.
     */
    public static void printCardArray( final Card... cards ){
        
        for( int i=0;  i < cards.length;  i++ ){
            if( null != cards[i] ){
                System.out.printf( "%15s", " ________ " );
            }else{
                System.out.printf( "%15s", " ..NULL.. " );
            }//if
        }//for
        System.out.println();
        
        for( int i=0;  i < cards.length;  i++ ){
            if( null != cards[i] ){
                switch( cards[i].getSuit() ){
                    case HEART   : System.out.printf( "%15s", String.format( "|%s _  _  |", cards[i].getRank())); break;
                    case DIAMOND : System.out.printf( "%15s", String.format( "|%s       |", cards[i].getRank())); break;
                    case CLUB    : System.out.printf( "%15s", String.format( "|%s  __   |", cards[i].getRank())); break;
                    case SPADES  : System.out.printf( "%15s", String.format( "|%s  __   |", cards[i].getRank())); break;
                }//switch
            }else{
                System.out.printf( "%15s", String.format( "00      00" ));
            }//if
        }//for
        System.out.println();
        
        for( int i=0;  i < cards.length;  i++ ){
            if( null != cards[i] ){
                switch( cards[i].getSuit() ){
                    case HEART   : System.out.printf( "%15s", String.format( "| / \\/ \\ |" )); break;
                    case DIAMOND : System.out.printf( "%15s", String.format( "|   /\\   |"  )); break;
                    case CLUB    : System.out.printf( "%15s", String.format( "| _|  |_ |"   )); break;
                    case SPADES  : System.out.printf( "%15s", String.format( "|  /  \\  |"  )); break;
                }//switch
            }else{
                System.out.printf( "%15s", String.format( ". 00  00 ." ));
            }//if
        }//for
        System.out.println();
        
        for( int i=0;  i < cards.length;  i++ ){
            if( null != cards[i] ){
                switch( cards[i].getSuit() ){
                    case HEART   : System.out.printf( "%15s", String.format( "| \\    / |" )); break;
                    case DIAMOND : System.out.printf( "%15s", String.format( "|  /  \\  |" )); break;
                    case CLUB    : System.out.printf( "%15s", String.format( "||      ||"  )); break;
                    case SPADES  : System.out.printf( "%15s", String.format( "| /    \\ |" )); break;
                }//switch
            }else{
                System.out.printf( "%15s", String.format( ".   00   ." ));
            }//if
        }//for
        System.out.println();
        
        for( int i=0;  i < cards.length;  i++ ){
            if( null != cards[i] ){
                switch( cards[i].getSuit() ){
                    case HEART   : System.out.printf( "%15s", String.format( "|  \\  /  |" )); break;
                    case DIAMOND : System.out.printf( "%15s", String.format( "|  \\  /  |" )); break;
                    case CLUB    : System.out.printf( "%15s", String.format( "||__  __||"  )); break;
                    case SPADES  : System.out.printf( "%15s", String.format( "|/      \\|" )); break;
                }//switch
            }else{
                System.out.printf( "%15s", String.format( ". 00  00 ." ));
            }//if
        }//for
        System.out.println();
        
        for( int i=0;  i < cards.length;  i++ ){
            if( null != cards[i] ){
                switch( cards[i].getSuit() ){
                    case HEART   : System.out.printf( "%15s", String.format( "|   \\/   |"  )); break;
                    case DIAMOND : System.out.printf( "%15s", String.format( "|   \\/   |"  )); break;
                    case CLUB    : System.out.printf( "%15s", String.format( "|  /__\\  |"  )); break;
                    case SPADES  : System.out.printf( "%15s", String.format( "|\\_/||\\_/|" )); break;
                }//switch
            }else{
                System.out.printf( "%15s", String.format( "00      00" ));
            }//if
        }//for
        System.out.println();
        
        for( int i=0;  i < cards.length;  i++ ){
            if( null != cards[i] ){
                System.out.printf( "%15s", "|________|" );
            }else{
                System.out.printf( "%15s", String.format( "'''NULL'''" ));
            }//if
        }//for
        System.out.println();
        
    }//method()
    
    
    
    /**
     * Die Methode getRank() liefert den Rang zur aktuellen Karte.
     * @return Rang der aktuellen Karte.
     */
    public Rank getRank(){
        return rank;     
    }//method()
    
    
    /**
     * Die Methode getSuit() liefert die Farbe zur aktuellen Karte.
     * @return Farbe der aktuellen Karte.
     */
    public Suit getSuit(){
        return suit;
    }//method()
    
    
    
    @Override
    public int compareTo( final Card other ){
        return rank.compareTo( other.rank );
    }//method()
    
    @Override
    public boolean equals( final Object other ){
        return ( getClass()==other.getClass() )  ?  ( suit==((Card)other).getSuit()) && (rank==((Card)other).getRank())  :  false;    
    }//method()
    
    @Override
    public int hashCode(){
        final int prime = 31;
        return  ((null==rank) ? 0 : rank.hashCode())  +  prime*((null==suit) ? 0 : suit.hashCode());
    }//method()
    
    
    
    @Override
    public String toString(){
        return " [" + suit + rank + "] ";
    }//method()
    
}//class
