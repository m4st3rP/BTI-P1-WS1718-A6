package tripleFinder;

import java.util.ArrayList;
import cards.Card;

public class CardProcessor implements CardProcessor_I {
    private ArrayList<Card> list;


    public CardProcessor() {
        list = new ArrayList<Card>();
    }



    /**
     * return Object First card that appears three times or if not existent null.
     */
    @Override
    public Object process(Card card) {
        int counter = 0;
        for (Card i : list) {
            if (card.equals(i))
                counter++;
        }
        if (counter < 2) {
            list.add(card);
            return null;
        } else {
            return card;
        }
    }

    /**
     * Resets the ArrayList of cards.
     */
    @Override
    public void reset() {
        list.clear();
    }
}
