package tripleFinder;


import cards.*;
import cards.Card.*;
import static cards.Card.*;
import static cards.Card.Constant.*;


/**
 * A6x1: ... - see task
 *
 * Das Interface <br />
 * o) beschreibt eine CardProcessor und <br />
 * o) definiert die Funktionalitaet moeglicher Implementierungen und fordert die entsprechenden Methoden ein. <br />
 *
 * Die von Ihnen zu implementierende Klasse CardProcessor muss einen parameterloseb Konstruktor aufweisenen.
 * Eine genaue Auflistung der Anforderungen an die zu implemntierende Klasse findet sich auf dem Aufgabenzettel.
 * 
 * @author   Michael Schaefers 
 * @version  2015/11/25
 */
public interface CardProcessor_I {
    
    
    /**
     * verarbeitet eine (Spiel-)Karte.
     * Die als Parameter uebergebene(n) Karte(n) wird(werden) zunaechst intern gespeichert.
     * Sobald ein Drilling (3 Karten vom gleichen Rang) vorliegt,
     * soll dieser Drilling (also die entsprechenden 3 Karten) als Rueckgaberwert der Methode zurueckgegeben werden.
     *
     * @param card bestimmt die (neue) Karte, die zu verarbeiten ist.
     * @return  der erste Drilling unmittebar nachdem er aufgetreten ist<br />
     *          und sonst null.
     */
    Object process( final Card card );
    
    
    /**
     * loescht alle (internen) gespeicherten Karten.
     */
    void reset();
    
}//interface
