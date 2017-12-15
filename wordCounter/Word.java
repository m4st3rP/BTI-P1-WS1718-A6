package wordCounter;

public class Word implements Comparable<Word> {
    private final String word;


    public Word(String word) {
        this.word = word.toLowerCase();
    }



    public String getWord() {
        return word;
    }

    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof Word)) {
            return false;            
        }
        
        Word otherWord = (Word) obj;
        return (this.word.equals(otherWord.word));
    }

    @Override
    public int hashCode() {
        return word.hashCode();
    }

    @Override
    public int compareTo(Word otherWord) {
        return this.word.compareTo(otherWord.word);
    }
}
