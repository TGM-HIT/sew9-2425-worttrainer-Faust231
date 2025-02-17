package main.java.Model;

import java.io.Serializable;
import java.net.MalformedURLException;
import java.net.URL;

public class WordPair implements Serializable {
    private String word;
    private String url;

    public WordPair(String word, String url) {
        this.word = word;
        this.url = url;
    }

    public String getWord() {
        return word;
    }

    public boolean setWord(String word) {
        if (word != null && !word.isEmpty()) {
            this.word = word;
            return true;
        }
        return false;
    }

    public String getUrl() {
        return url;
    }

    public boolean setUrl(String url) {
        if (url != null && !url.isEmpty()) {
            try {
                new URL(url);
                this.url = url;
                return true;
            } catch (MalformedURLException e) {
                System.err.println("Invalid URL: " + url);
            }
        }
        return false;
    }
}
