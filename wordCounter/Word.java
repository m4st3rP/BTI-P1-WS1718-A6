package wordCounter;

public class Word implements Comparable<Object> {
    private final String word;


    public Word(String word) {
        this.word = word;
    }



    public String getWord() {
        return word;
    }

    @Override
    public boolean equals(Object obj) {
        Word otherWord = (Word) obj;
        return (this.word.toLowerCase().equals(otherWord.word.toLowerCase()));
    }

    @Override
    public int hashCode() {
        return 1;
    }

    @Override
    public int compareTo(Object obj) {
        Word otherWord = (Word) obj;
        return this.word.compareTo(otherWord.word);
    }
}
