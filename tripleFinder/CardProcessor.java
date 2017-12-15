package tripleFinder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cards.Card;
import cards.Card.Rank;

public class CardProcessor implements CardProcessor_I {

	final Map<Rank, List<Card>> cardMap = new HashMap<Rank, List<Card>>();
	final int DRILLINGCOUNT = 3;

	@Override
	public Object process(Card card) {
		final Rank rank = card.getRank();
		List<Card> rankList = cardMap.get(rank);
		if (null == rankList) {
			rankList = new ArrayList<Card>();
			rankList.add(card);
			cardMap.put(rank, rankList);
		} else
			rankList.add(card);

		if (rankList.size() == DRILLINGCOUNT)
			return rankList;

		return null;
	}

	@Override
	public void reset() {
		cardMap.clear();
	}

}
