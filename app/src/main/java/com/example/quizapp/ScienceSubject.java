package com.example.quizapp;

import java.util.Random;

public class ScienceSubject {

    public static String[] getScienceQuestions() {
        return scienceQuestions;
    }

    public static String[][] getScienceChoices() {
        return scienceChoices;
    }

    public static String[] getScienceCorrectAnswers() {
        return scienceCorrectAnswers;
    }

    // Science questions and choices
    public static String scienceQuestions[] = {
            "What is the process of turning a liquid into a gas called?",
            "Which planet is closest to the Sun?",
            "What is the main function of the heart?",
            "What gas do plants absorb from the air and release oxygen during photosynthesis?",
            "Who is known as the father of modern physics?",
            "What force keeps planets in orbit around the Sun?",
            "What is the largest planet in our solar system?",
            "Which bone protects the brain?",
            "What is the process of changing from a liquid to a solid called?",
            "What is the chemical symbol for gold?"
    };

    public static String scienceChoices[][] = {
            {"Evaporation", "Condensation", "Melting", "Freezing"},
            {"Mercury", "Venus", "Earth", "Mars"},
            {"Pumping blood", "Digesting food", "Filtering air", "Producing energy"},
            {"Carbon dioxide", "Oxygen", "Nitrogen", "Hydrogen"},
            {"Isaac Newton", "Albert Einstein", "Galileo Galilei", "Stephen Hawking"},
            {"Gravity", "Magnetism", "Friction", "Inertia"},
            {"Jupiter", "Saturn", "Neptune", "Mars"},
            {"Skull", "Ribs", "Spine", "Femur"},
            {"Condensation", "Evaporation", "Sublimation", "Solidification"},
            {"Au", "Ag", "Cu", "Fe"}
    };

    public static String scienceCorrectAnswers[] = {"Evaporation", "Mercury", "Pumping blood", "Carbon dioxide", "Albert Einstein",
            "Gravity", "Jupiter", "Skull", "Solidification", "Au"};

    // Function to shuffle choices for all questions
    private static void shuffleChoices(String[] arr) {
        Random rand = new Random();
        int correctIndex = rand.nextInt(arr.length);
        String correctAnswer = arr[correctIndex];

        // Fisher-Yates shuffle
        for (int i = arr.length - 1; i > 0; i--) {
            int j = rand.nextInt(i + 1);
            // Swap arr[i] and arr[j]
            String temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }

        // Set the correct answer at a random position
        arr[correctIndex] = correctAnswer;
    }

    // Function to shuffle choices for all questions
    public static void shuffleAllChoices() {
        for (int i = 0; i < scienceChoices.length; i++) {
            shuffleChoices(scienceChoices[i]);
        }
    }
}
