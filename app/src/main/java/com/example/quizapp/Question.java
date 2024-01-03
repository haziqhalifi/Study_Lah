// Question.java
package com.example.quizapp;

// Question.java
public class Question {
    private String text;
    private String[] choices;
    private String correctAnswer;
    private String topic;  // New field to store the topic

    public Question(String text, String[] choices, String correctAnswer, String topic) {
        this.text = text;
        this.choices = choices;
        this.correctAnswer = correctAnswer;
        this.topic = topic;
    }

    public String getText() {
        return text;
    }

    public String[] getChoices() {
        return choices;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public String getTopic() {
        return topic;
    }
}
