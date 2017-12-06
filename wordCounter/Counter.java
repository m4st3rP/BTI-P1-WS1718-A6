package wordCounter;

public class Counter {
    private int count;


    public Counter() {
        count = 1;
    }



    public void inc() {
        this.count++;
    }
    
    public int getCount() {
        return count;
    }

}
