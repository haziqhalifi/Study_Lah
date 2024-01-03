package com.example.quizapp;

import java.util.Random;

public class ScienceSubject {

    public static Question[] getScienceQuestions() {
        return scienceQuestions;
    }

    // Science questions and choices
    public static Question scienceQuestions[] = {
            new Question("What is the process of turning a liquid into a gas called?", new String[]{"Evaporation", "Condensation", "Melting", "Freezing"}, "Evaporation", "States of Matter"),
            new Question("Which planet is closest to the Sun?", new String[]{"Mercury", "Venus", "Earth", "Mars"}, "Mercury", "Astronomy"),
            // Add more questions with associated topics
    };

    // Function to shuffle choices for all questions
    public static void shuffleAllChoices() {
        for (int i = 0; i < scienceQuestions.length; i++) {
            shuffleChoices(scienceQuestions[i].getChoices());
        }
    }

    private static void shuffleChoices(String[] arr) {
        // Shuffle logic here
    }
}
