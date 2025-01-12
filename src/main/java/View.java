package main.java;

import main.java.Model.WordTrainer;
import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class View {
    private WordTrainer wt;
    private boolean running;
    private Boolean lastGuess;

    public View() {
        this.wt = new WordTrainer();
        this.running = true;
    }

    public void start() {
        try{
            wt.loadData();
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, "Unable to load wordtrainer data. Continuing with default values");
        }
        while (running) {
            wt.randomIndex();
            String correctWord = wt.getSingleWordPair().getWord();

            boolean x = true;
            while (x) {
                //System.out.println(correctWord); // testing
                String userAnswer = displayWordPair();

                //System.out.println(userAnswer); // testing
                if (userAnswer != null && !userAnswer.isEmpty()) {
                    if (userAnswer.equalsIgnoreCase(correctWord)) {
                        wt.getStatistic().incrementCorrect();
                        //JOptionPane.showMessageDialog(null, "Correct!");
                        lastGuess = true;
                        x = false;
                    } else {
                        wt.getStatistic().incrementFalse();
                        lastGuess = false;
                        //JOptionPane.showMessageDialog(null, "Incorrect! Please try again");
                    }
                } else {
                    int option = JOptionPane.showConfirmDialog(null, "Do you want to exit?", "Exit Confirmation", JOptionPane.OK_CANCEL_OPTION);

                    if (option == JOptionPane.OK_OPTION) {
                        x = false;
                        running = false;
                    } else {
                        x = true;
                    }
                }
            }
        }
        JOptionPane.showMessageDialog(null, wt.getStatistic().toString());
        wt.saveData();
        JOptionPane.showMessageDialog(null, "Data has been saved");
    }

    public String displayWordPair() {
        try {
            URL url = new URL(wt.getSingleWordPair().getUrl());
            ImageIcon image = new ImageIcon(url);

            Image img = image.getImage().getScaledInstance(500, 500, Image.SCALE_SMOOTH);
            ImageIcon resizedImageIcon = new ImageIcon(img);

            // Create a JTextField for user input
            JTextField answerField = new JTextField();

            String lastGuessMessage;
            if (lastGuess == null) {
                lastGuessMessage = "You haven't made a guess yet.";
            } else if (lastGuess) {
                lastGuessMessage = "Your last guess was correct!";
            } else {
                lastGuessMessage = "Your last guess was incorrect!";
            }
            // Create an array for the components to be used in the dialog
            Object[] message = {
                    "What is the following picture?", // Prompt text
                    resizedImageIcon, // Image
                    lastGuessMessage,
                    "Your Answer (leave empty to exit):", // Label for input
                    answerField // Input field for the user to type their answer
            };

            // Display the dialog with the image and input field
            int option = JOptionPane.showConfirmDialog(null, message, "Word Pair", JOptionPane.OK_CANCEL_OPTION);

            // If user clicked OK, get the text from the answer field
            if (option == JOptionPane.OK_OPTION) {
                return answerField.getText().trim(); // Return sanitized user input
            } else {
                return null; // Return null if the user clicked Cancel or closed the dialog
            }
        } catch (Exception e) {
            // Show error message if the URL is invalid or other exceptions occur
            JOptionPane.showMessageDialog(null, "Invalid URL");
            return null;
        }
    }

    public static void main(String[] args) {
        View view = new View();
        view.start();
    }
}

