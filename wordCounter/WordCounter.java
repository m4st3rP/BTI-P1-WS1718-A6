package wordCounter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class WordCounter {

    public void printStatistic(String fileName) {
        HashMap<Word, Counter> map = new HashMap<Word, Counter>();
        WordGrabber wg = new WordGrabber(fileName);

        while (wg.hasNext()) {
            Word word = new Word(wg.next());

            if (!map.containsKey(word)) {
                Counter counter = new Counter();
                map.put(word, counter);
            } else {
                map.get(word).inc();
            }
        }
        
        final ArrayList<Word> words = new ArrayList<>(map.keySet());
        Collections.sort(words);
        for (Word i : words) {
            System.out.println("[ " + i.getWord() + " ] : " + map.get(i).getCount());
        }
    }
}
