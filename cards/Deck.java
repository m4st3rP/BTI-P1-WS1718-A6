package cards; 


import static cards.Card.*;  // only for JavaDoc ;-)

import java.lang.reflect.Constructor;
import java.util.*;


/**
 * Die Klasse Deck ist der Datentyp eines 52 Blatt Poker-Karten-<strong>Deck</strong>s.
 * Das 52 Blatt Poker-Karten-Deck besteht aus Spielkarten, die<br />
 * die R&auml;nge <em>(Rank)</em>:<br />
 * &nbsp; &nbsp;<code>2</code>,&nbsp;<code>3</code>,&nbsp; <code>4</code>,&nbsp;&hellip;,&nbsp;<code>ACE</code><br />
 * in den Farben <em>(Suit)</em>:<br />
 * &nbsp; &nbsp;<code>CLUB</code> (&clubs;), <code>DIAMOND</code> (<font color="red">&diams;</font>), <code>HEART</code> (<font color="red">&hearts;</font>) und <code>SPADES</code> (&spades;)<br />
 * aufweisen.<br />
 * <br />
 * Wichtige Attribute dieser Klasse sind:<br />
 * &bull;{@link #deal()} gibt die &quot;oberste&quot; Karte des Decks.<br />
 * &bull;{@link #removeTopCard()} zum Entfernen der &quot;obersten&quot; Karte des Decks.<br />
 * &bull;{@link #shuffleDeck()}} zum Mischen des Decks.<br />
 * <br />
 * VCS: git@bitbucket.org:schaefers/P1_Cards_BlueJ_Pilot
 */
public class Deck {
   
    
    //
    //
    // CONSTANTs & VARIABLEs----------------------------------------------------

    private final List<Card> pack1st;
    private final List<Card> pack2nd;
    
    
   
    //
    //
    // CONSTRUCTORs-------------------------------------------------------------
    
    /**
     * Der Konstruktor erzeugt ein Deck.
     * Es werden dabei interne Variablen gesetzt.
     */
    public Deck(){
        // "assert" that the world is as expected - SORRY, this mysterious comment is on purpose
        for( final Constructor<?> constructor : Card.class.getDeclaredConstructors() ){
            if( 0!=constructor.getModifiers() ){
                throw new IllegalStateException( String.format( "You have have done strange disturbing things - please call Michael Schaefers" ) );
            }//if
        }//for
                
        pack1st = new LinkedList<Card>();  // cards in game/pack
        pack2nd = new LinkedList<Card>();  // cards out of game/pack
        
        for( final Card.Suit suit : Card.Suit.values() ){
            for( final Card.Rank rank : Card.Rank.values() ){
               pack1st.add( new Card( suit, rank ) );
            }//for
        }//for
        
        Collections.shuffle( pack1st );
    }//constructor()
   
    
    
    //
    //
    // METHODs------------------------------------------------------------------

    /**
     * Die Methode shuffleDeck() mischt die noch verf&uuml;gbaren Karten im Deck.
     */
    public void shuffleDeck(){
        Collections.shuffle( pack1st );
    }//method()
   
    /**
     * Die Methode deal() liefert die &quot;oberste&quot; Karte des Decks.
     * Sollte keine Karte im Deck vorhanden sein, so wird die Meldung:<br />
     * &nbsp; &nbsp;<code>>>>> ERROR !!! : no more cards in deck - opening new pack</code><br />
     * auf dem Bildschirm ausgegeben
     * und eine neues Deck &quot;aufgemacht&quot; und diesem die oberste Karte entnommen.
     * @return &quot;oberste&quot; Karte des Decks.
     */
    public Card deal(){
        if( pack1st.isEmpty() ){
            System.out.printf("\n>>>> ERROR !!! : no more cards in deck - opening new pack\n");
            while( ! pack2nd.isEmpty() ){
                 pack1st.add( pack2nd.remove(0) );
            }//for
            shuffleDeck();
        }//if
        Card card = pack1st.remove( 0 );
        pack2nd.add( card );
        return card;
    }//method()
   
    /**
     * Die Methode removeTopCard() entfernt die &quot;oberste&quot; Karte des Decks.
     * Sollte keine Karte im Deck vorhanden sein, so wird die Meldung:<br />
     * &nbsp; &nbsp;<code>>>>> ERROR !!! : no more cards in deck</code><br />
     * auf dem Bildschirm ausgegeben.
     */
    public void removeTopCard(){
       if( pack1st.isEmpty() ){
           System.out.printf("\n>>>> ERROR !!! : no more cards in deck\n");
       }else{
           pack2nd.add( pack1st.remove( 0 ) );
       }//if
    }//method()
    
    @Override
    public String toString(){
        StringBuffer resu = new StringBuffer();        
        for( final Card c : pack1st )  resu.append( c );
        return resu.toString();
    }//method()
    
    
    @Override
    public boolean equals( final Object other ) {
        return getClass() == other.getClass()
            && null!=pack1st && null!=((Deck)other).pack1st && pack1st.equals( ((Deck)other).pack1st )
            && null!=pack2nd && null!=((Deck)other).pack2nd && pack2nd.equals( ((Deck)other).pack2nd );
    }//method()
    
    @Override
    public int hashCode() {
        final int prime = 31;
        return ((null==pack1st) ? 0 : pack1st.hashCode())  +  prime*((null==pack2nd) ? 0 : pack2nd.hashCode());
    }//method()
    
}//class
