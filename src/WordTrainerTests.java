

import main.java.Persistence.Persistence;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import main.java.Model.*;

import java.io.File;

public class WordTrainerTests {

    @Test
    public void testStatisticInitialization() {
        Statistic stats = new Statistic(10, 5, 5);
        assertEquals(10, stats.getTotal());
        assertEquals(5, stats.getCorrect());
        assertEquals(5, stats.getFalse());
    }

    @Test
    public void testStatisticSetCorrect() {
        Statistic stats = new Statistic(10, 5, 5);
        assertTrue(stats.setCorrect(7));
        assertEquals(7, stats.getCorrect());
        assertFalse(stats.setCorrect(-1));
    }

    @Test
    public void testStatisticSetFalse() {
        Statistic stats = new Statistic(10, 5, 5);
        assertTrue(stats.setFalse(6));
        assertEquals(6, stats.getFalse());
        assertFalse(stats.setFalse(-2));
    }

    @Test
    public void testWordPairInitialization() {
        WordPair pair = new WordPair("apple", "https://example.com/apple");
        assertEquals("apple", pair.getWord());
        assertEquals("https://example.com/apple", pair.getUrl());
    }

    @Test
    public void testWordPairSetWord() {
        WordPair pair = new WordPair("apple", "https://example.com/apple");
        assertTrue(pair.setWord("banana"));
        assertEquals("banana", pair.getWord());
        assertFalse(pair.setWord(null));
        assertFalse(pair.setWord(""));
    }

    @Test
    public void testWordPairSetUrl() {
        WordPair pair = new WordPair("apple", "https://example.com/apple");
        assertTrue(pair.setUrl("https://example.com/banana"));
        assertEquals("https://example.com/banana", pair.getUrl());
        assertFalse(pair.setUrl("invalid-url"));
        assertFalse(pair.setUrl(null));
    }

    @Test
    public void testWordTrainerInitialization() {
        WordPair[] pairs = {
                new WordPair("apple", "https://example.com/apple"),
                new WordPair("banana", "https://example.com/banana")
        };
        Statistic stats = new Statistic(10, 5, 5);
        WordTrainer trainer = new WordTrainer(stats, pairs);

        assertEquals(stats, trainer.getStatistic());
        assertEquals(2, trainer.getWordPairs().length);
    }

    @Test
    public void testWordTrainerRandomIndex() {
        WordPair[] pairs = {
                new WordPair("apple", "https://example.com/apple"),
                new WordPair("banana", "https://example.com/banana")
        };
        WordTrainer trainer = new WordTrainer(new Statistic(10, 5, 5), pairs);
        trainer.randomIndex();
        assertTrue(trainer.getIndex() >= 0 && trainer.getIndex() < pairs.length);
    }

    @Test
    public void testWordTrainerSetIndex() {
        WordPair[] pairs = {
                new WordPair("apple", "https://example.com/apple"),
                new WordPair("banana", "https://example.com/banana")
        };
        WordTrainer trainer = new WordTrainer(new Statistic(10, 5, 5), pairs);
        assertTrue(trainer.setIndex(1));
        assertEquals(1, trainer.getIndex());
        assertFalse(trainer.setIndex(-1));
        assertFalse(trainer.setIndex(5));
    }

    @Test
    public void testPersistenceSaveAndLoad() {
        WordPair[] pairs = {
                new WordPair("apple", "https://example.com/apple"),
                new WordPair("banana", "https://example.com/banana")
        };
        Statistic stats = new Statistic(10, 5, 5);
        WordTrainer trainer = new WordTrainer(stats, pairs);

        // Save the trainer
        assertTrue(Persistence.saveData(trainer));

        // Load the trainer
        WordTrainer loadedTrainer = Persistence.loadData();
        assertNotNull(loadedTrainer);
        assertEquals(2, loadedTrainer.getWordPairs().length);
        assertEquals("apple", loadedTrainer.getWordPairs()[0].getWord());

        // Cleanup
        new File("wordTrainerData.ser").delete();
    }
}
