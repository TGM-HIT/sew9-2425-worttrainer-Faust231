package src.Model;

import java.io.Serializable;

public class Statistic implements Serializable {
    private int total;
    private int correct;
    private int falseAnswers;

    public Statistic(int total, int correct, int falseAnswers) {
        this.total = total;
        this.correct = correct;
        this.falseAnswers = falseAnswers;
    }

    public Statistic(){
        this.total = 0;
        this.correct = 0;
        this.falseAnswers = 0;
    }

    public int getTotal() {
        return correct+falseAnswers;
    }

    public int getCorrect() {
        return correct;
    }

    public int getFalse() {
        return falseAnswers;
    }

    public boolean setCorrect(int correct) {
        if (correct >= 0) {
            this.correct = correct;
            return true;
        }
        return false;
    }

    public boolean setFalse(int falseAnswers) {
        if (falseAnswers >= 0) {
            this.falseAnswers = falseAnswers;
            return true;
        }
        return false;
    }

    public void incrementCorrect(){
        this.correct++;
    }

    public void incrementFalse(){
        this.falseAnswers++;
    }

    @Override
    public String toString(){
        if(correct+falseAnswers>0) {
            return "Correct: " + correct + "\nFalse: " + falseAnswers + "\nPercentage: " + correct / (correct + falseAnswers + 0.0);
        }
        return "Correct: " + correct + "\nFalse: " + falseAnswers;

    }
}
