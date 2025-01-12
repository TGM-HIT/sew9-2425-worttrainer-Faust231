package src.Persistence;

public class Statistic {
    private int total;
    private int correct;
    private int falseAnswers;

    public Statistic(int total, int correct, int falseAnswers) {
        this.total = total;
        this.correct = correct;
        this.falseAnswers = falseAnswers;
    }

    public int getTotal() {
        return total;
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
}
