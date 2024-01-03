package com.example.quizapp;

import java.util.Random;

public class MathSubject {

    public static String[] getMathQuestions() {
        return mathQuestions;
    }

    public static String[][] getMathChoices() {
        return mathChoices;
    }

    public static String[] getMathCorrectAnswers() {
        return mathCorrectAnswers;
    }

    // Mathematics questions and choices
    public static String mathQuestions[] = {
            "What is the result of 15 + 7?",
            "If a rectangle has a length of 8 units and a width of 5 units, what is its area?",
            "Solve: 48 ÷ 6.",
            "If a bag contains 24 candies and you take 6 candies, how many candies are left?",
            "What is the sum of 13 and 9?"
    };

    public static String mathChoices[][] = {
            {"22", "18", "20", "25"},
            {"40 square units", "13 square units", "30 square units", "48 square units"},
            {"8", "6", "7", "9"},
            {"18", "12", "16", "22"},
            {"22", "18", "20", "25"}
    };

    public static String mathCorrectAnswers[] = {"22", "40 square units", "8", "18", "22"};

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
        for (int i = 0; i < mathChoices.length; i++) {
            shuffleChoices(mathChoices[i]);
        }
    }

}

