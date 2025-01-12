package src.Persistence;

import java.util.Random;

public class WordTrainer {
    private Statistic stats;
    private int index;
    private WordPair[] wordPairs;

    public WordTrainer(Statistic stats, WordPair[] wordPairs) {
        this.stats = stats;
        this.wordPairs = wordPairs;
        this.index = 0;
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
}
