package main.java.Model;

import java.io.Serializable;
import java.util.Random;
import main.java.Persistence.Persistence;

public class WordTrainer implements Serializable {
    private Statistic stats;
    private int index;
    private WordPair[] wordPairs;

    public WordTrainer(Statistic stats, WordPair[] wordPairs) {
        this.stats = stats;
        this.wordPairs = wordPairs;
        this.index = 0;
    }

    public WordTrainer(){
        this.stats = new Statistic();
        WordPair[] wordPairs = new WordPair[4];
        wordPairs[0] = new WordPair("apple", "https://www.collinsdictionary.com/images/full/apple_158989157.jpg");
        wordPairs[1] = new WordPair("banana", "https://i5.walmartimages.com/seo/Fresh-Banana-Fruit-Each_5939a6fa-a0d6-431c-88c6-b4f21608e4be.f7cd0cc487761d74c69b7731493c1581.jpeg");
        wordPairs[2] = new WordPair("orange", "https://eis-machen.de/wp-content/uploads/2014/05/Eis_Blog_Orange-1.jpg");
        wordPairs[3] = new WordPair("kiwi", "https://upload.wikimedia.org/wikipedia/commons/d/d3/Kiwi_aka.jpg");

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
            throw new Exception("Wordtrainer can not be loaded");
        }
        return true;
    }

    public void setStatistic(Statistic s){
        this.stats = s;
    }

    public void setWordPairs(WordPair[] wp){
        this.wordPairs = wp;
    }


}
