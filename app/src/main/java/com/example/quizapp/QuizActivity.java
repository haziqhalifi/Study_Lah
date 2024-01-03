package com.example.quizapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class QuizActivity extends AppCompatActivity implements View.OnClickListener {
    private String[] questionsArray;
    private String[][] choices;
    private String[] correctAnswers;

    private TextView questionTextView;
    private Button[] answerButtons;

    private int currentQuestionIndex = 0;
    private int score = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        // Initialize views and buttons
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
            loadQuestions(MathSubject.getMathQuestions(), MathSubject.getMathChoices(), MathSubject.getMathCorrectAnswers());
        } else if ("Science".equals(chosenSubject)) {
            loadQuestions(ScienceSubject.getScienceQuestions(), ScienceSubject.getScienceChoices(), ScienceSubject.getScienceCorrectAnswers());
        }

        // Display the first question
        displayQuestion();
    }




    private void loadQuestions(String[] questionsArray, String[][] choicesArray, String[] correctAnswersArray) {
        this.questionsArray = questionsArray;
        choices = choicesArray;
        correctAnswers = correctAnswersArray;
    }

    private void shuffleAllChoices() {
        for (int i = 0; i < choices.length; i++) {
            shuffleChoices(choices[i]);
        }
    }

    private void shuffleChoices(String[] arr) {
        // Your shuffle logic here
    }

    private void displayQuestion() {
        if (currentQuestionIndex < choices.length) {
            questionTextView.setText(questionsArray[currentQuestionIndex]);

            for (int i = 0; i < answerButtons.length; i++) {
                answerButtons[i].setText(choices[currentQuestionIndex][i]);
            }
        } else {
            // Quiz is complete, show score or perform any other action
            Toast.makeText(this, "Quiz completed! Your score: " + score, Toast.LENGTH_SHORT).show();
            finish();
        }
    }

    @Override
    public void onClick(View view) {
        Button selectedButton = (Button) view;
        String selectedAnswer = selectedButton.getText().toString();

        if (selectedAnswer.equals(correctAnswers[currentQuestionIndex])) {
            // Correct answer
            score++;
            Toast.makeText(this, "Correct!", Toast.LENGTH_SHORT).show();
        } else {
            // Incorrect answer
            Toast.makeText(this, "Incorrect. Correct answer: " + correctAnswers[currentQuestionIndex], Toast.LENGTH_SHORT).show();
        }

        currentQuestionIndex++;
        displayQuestion();
    }
}
