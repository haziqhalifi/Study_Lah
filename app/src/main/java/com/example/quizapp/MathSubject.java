package com.example.quizapp;

import java.util.Random;

public class MathSubject {

    public static Question[] getMathQuestions() {
        return mathQuestions;
    }

    // Mathematics questions and choices
    public static Question mathQuestions[] = {
            new Question("What is the result of 15 + 7?", new String[]{"22", "18", "20", "25"}, "22", "Addition"),
            new Question("If a rectangle has a length of 8 units and a width of 5 units, what is its area?", new String[]{"40 square units", "13 square units", "30 square units", "48 square units"}, "40 square units", "Geometry"),
            new Question("Solve: 48 ÷ 6.", new String[]{"8", "6", "7", "9"}, "8", "Division"),
            new Question("If a bag contains 24 candies and you take 6 candies, how many candies are left?", new String[]{"18", "12", "16", "22"}, "18", "Subtraction"),
            new Question("What is the sum of 13 and 9?", new String[]{"22", "18", "20", "25"}, "22", "Addition")
    };

    // Function to shuffle choices for all questions
    public static void shuffleAllChoices() {
        for (int i = 0; i < mathQuestions.length; i++) {
            shuffleChoices(mathQuestions[i].getChoices());
        }
    }

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
}
