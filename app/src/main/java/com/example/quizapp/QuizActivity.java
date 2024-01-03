package com.example.quizapp;
// QuizActivity.java

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class QuizActivity extends AppCompatActivity implements View.OnClickListener {
    private Question[] questionsArray;
    private Map<String, Integer[]> topicScores = new HashMap<>(); // Map to store scores by topic

    private TextView questionNumberTextView;

    private int score = 0;

    private TextView questionTextView;
    private Button[] answerButtons;
    private Set<String> promptedTopics = new HashSet<>();


    private int currentQuestionIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        // Initialize views and buttons
        questionNumberTextView = findViewById(R.id.questionNumber);
        questionTextView = findViewById(R.id.question);
        int[] answerButtonIds = {R.id.ans_A, R.id.ans_B, R.id.ans_C, R.id.ans_D};
        answerButtons = new Button[answerButtonIds.length];

        for (int i = 0; i < answerButtons.length; i++) {
            answerButtons[i] = findViewById(answerButtonIds[i]);
            answerButtons[i].setOnClickListener(this);
        }

        // Let's assume you have a way to determine the user's subject choice (e.g., from a previous activity)
        String chosenSubject = getIntent().getStringExtra("Subject");

        // Load questions based on the chosen subject
        if ("Math".equals(chosenSubject)) {
            loadQuestions(MathSubject.getMathQuestions());
        } else if ("Science".equals(chosenSubject)) {
            loadQuestions(ScienceSubject.getScienceQuestions());
        }

        // Display the first question
        displayQuestion();
    }

    private void loadQuestions(Question[] questionsArray) {
        this.questionsArray = questionsArray;
    }

    private void displayQuestion() {
        if (currentQuestionIndex < questionsArray.length) {
            // Update question number
            int questionNumber = currentQuestionIndex + 1;
            questionNumberTextView.setText("Question " + questionNumber);

            // Update question text
            questionTextView.setText(questionsArray[currentQuestionIndex].getText());

            for (int i = 0; i < answerButtons.length; i++) {
                answerButtons[i].setText(questionsArray[currentQuestionIndex].getChoices()[i]);
            }
        } else {
            // Quiz is complete, show the result summary
            displayResultSummary();
        }
    }

    private void displayResultSummary() {
        // Build the overall result summary message
        String overallResultMessage = "Result!\nScore: " + score + "/" + questionsArray.length;

        // Build the scores by topics message
        StringBuilder scoresByTopicsMessage = new StringBuilder("Score by Topics:\n");

        // Collect topics that need further study
        List<String> topicsToStudy = new ArrayList<>();

        for (Map.Entry<String, Integer[]> entry : topicScores.entrySet()) {
            String topic = entry.getKey();
            Integer[] topicScore = entry.getValue();
            int totalQuestionsForTopic = getTotalQuestionsForTopic(topic);

            // Calculate the percentage of mastery
            double percentage = (double) topicScore[0] / totalQuestionsForTopic * 100;

            scoresByTopicsMessage.append(topic).append(": ")
                    .append(topicScore[0]).append("/").append(totalQuestionsForTopic)
                    .append(" (").append(String.format("%.2f", percentage)).append("%)\n");

            // Collect topics that need further study
            if (percentage < 80) {
                topicsToStudy.add(topic);
            }
        }

        // Include the advice message in the result summary
        String adviceMessage = generateAdviceMessage(topicsToStudy);
        scoresByTopicsMessage.append("\nAdvise:\n").append(adviceMessage);

        // Create an AlertDialog to display the result summary
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Quiz Result")
                .setMessage(overallResultMessage + "\n\n" + scoresByTopicsMessage.toString())
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Handle the OK button click if needed
                        // For example, you can navigate back to the AssessPage
                        finish();
                    }
                })
                .setCancelable(false) // Prevent dismissing dialog on outside touch or back press
                .show();
    }

    private String generateAdviceMessage(List<String> topicsToStudy) {
        StringBuilder adviceMessage = new StringBuilder();

        if (!topicsToStudy.isEmpty()) {
            adviceMessage.append("You need to study these topics:\n");
            for (String topic : topicsToStudy) {
                adviceMessage.append(topic).append(" (").append(getTopicWebsite(topic)).append(")\n");
            }
        } else {
            adviceMessage.append("Great job! You're doing well in all topics.");
        }

        return adviceMessage.toString();
    }

    private String getTopicWebsite(String topic) {
        // Implement logic to return the website URL based on the topic
        // For example, you can have a Map<String, String> to store topic-URL mappings
        // or use a switch statement to handle specific topics
        // Replace the return statement with your actual logic
        return "https://example.com";
    }


    @Override
    public void onClick(View view) {
        Button selectedButton = (Button) view;
        String selectedAnswer = selectedButton.getText().toString();

        // Get the topic associated with the current question
        String currentTopic = questionsArray[currentQuestionIndex].getTopic();

        // Update scores by topic
        if (!topicScores.containsKey(currentTopic)) {
            int totalQuestionsForTopic = getTotalQuestionsForTopic(currentTopic);
            topicScores.put(currentTopic, new Integer[]{0, totalQuestionsForTopic});
        }

        if (selectedAnswer.equals(questionsArray[currentQuestionIndex].getCorrectAnswer())) {
            // Correct answer
            score++;
            topicScores.get(currentTopic)[0]++; // Increment correct answers for the current topic
            Toast.makeText(this, "Correct!", Toast.LENGTH_SHORT).show();
        } else {
            // Incorrect answer
            Toast.makeText(this, "Incorrect. Correct answer: " + questionsArray[currentQuestionIndex].getCorrectAnswer(), Toast.LENGTH_SHORT).show();
        }

        currentQuestionIndex++;
        displayQuestion();
    }

    // Assuming you have a method in your Question class to get the total questions for a specific topic
    private int getTotalQuestionsForTopic(String topic) {
        // You need to implement this method based on your data model
        // For example, you could iterate through questionsArray and count questions for the given topic
        int count = 0;
        for (Question question : questionsArray) {
            if (topic.equals(question.getTopic())) {
                count++;
            }
        }
        return count;
    }


}
