package src.Model;

import java.util.Random;
import src.Persistence.*;

public class WordTrainer {
    private Statistic stats;
    private int index;
    private WordPair[] wordPairs;

    public WordTrainer(Statistic stats, WordPair[] wordPairs) {
        this.stats = stats;
        this.wordPairs = wordPairs;
        this.index = 0;
    }

    public WordTrainer(){
    }

    public void randomIndex() {
        Random random = new Random();
        this.index = random.nextInt(wordPairs.length);
    }

    public boolean setIndex(int index) {
        if (index >= 0 && index < wordPairs.length) {
            this.index = index;
            return true;
        }
        return false;
    }

    public int getIndex() {
        return index;
    }

    public WordPair[] getWordPairs() {
        return wordPairs;
    }

    public WordPair getSingleWordPair() {
        return wordPairs[index];
    }

    public Statistic getStatistic() {
        return stats;
    }

    public boolean saveData() {
        return Persistence.saveData(this);
    }

    public boolean loadData() throws Exception {
        try{
            WordTrainer temp = new WordTrainer();
            temp = Persistence.loadData();
            this.stats = temp.getStatistic();
            this.index = temp.getIndex();
            this.wordPairs = temp.getWordPairs();
        }
        catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
