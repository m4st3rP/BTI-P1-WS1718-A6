package wordCounter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class WordCounter {

    public void printStatistic(String file) {
        HashMap<Word, Counter> map = new HashMap<Word, Counter>();
        WordGrabber wg = new WordGrabber("PrincessOfMars.txt");

        while (wg.hasNext()) {
            Word word = new Word(wg.next());

            if (!map.containsKey(word)) {
                Counter counter = new Counter();
                map.put(word, counter);
            } else {
                map.get(word).inc();
            }
        }

        map = sortMap(map);

        
        for (HashMap.Entry<Word, Counter> entry : map.entrySet()) {
            System.out.println("[ " + entry.getKey().getWord() + " ] : " + entry.getValue().getCount());
        }
    }

    public HashMap<Word, Counter> sortMap(HashMap<Word, Counter> map) {
        final HashMap<Word, Counter> newMap = new HashMap<Word, Counter>();
        final ArrayList<Word> words = new ArrayList<>(map.keySet());
        Collections.sort(words);
        for (Word i : words) {
            newMap.put(i, map.get(i));
        }
        return newMap;
    }
}
